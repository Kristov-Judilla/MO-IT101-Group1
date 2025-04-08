import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MOTORPH {
    // Class to hold login/logout records and calculate hours
    static class AttendanceRecord {
        LocalDate date;
        LocalTime loginTime;
        LocalTime logoutTime;
        static final LocalTime SHIFT_START = LocalTime.of(8, 0); // 8:00 AM
        static final LocalTime SHIFT_END = LocalTime.of(17, 0); // 5:00 PM
        static final LocalTime GRACE_PERIOD_END = SHIFT_START.plusMinutes(15); // 8:15 AM
        static final double REGULAR_HOURS_PER_DAY = 8.0; // 8 hours per day

        AttendanceRecord(LocalDate date, LocalTime loginTime, LocalTime logoutTime) {
            this.date = date;
            this.loginTime = loginTime;
            this.logoutTime = logoutTime;
        }

        // Calculate regular hours worked for this record (8 AM to 5 PM, minus 1-hour lunch)
        double calculateRegularHoursWorked() {
            if (loginTime == null || logoutTime == null) return 0.0;
            LocalTime effectiveStart = loginTime.isBefore(SHIFT_START) ? SHIFT_START : loginTime;
            LocalTime effectiveEnd = logoutTime.isAfter(SHIFT_END) ? SHIFT_END : logoutTime;
            if (effectiveStart.isAfter(effectiveEnd)) return 0.0;
            long minutesWorked = ChronoUnit.MINUTES.between(effectiveStart, effectiveEnd);
            double hoursWorked = minutesWorked / 60.0;
            if (hoursWorked > 1) {
                hoursWorked -= 1.0; // Subtract 1-hour lunch break
            }
            // Cap regular hours at 8 hours per day
            hoursWorked = Math.min(hoursWorked, REGULAR_HOURS_PER_DAY);
            return Math.max(hoursWorked, 0.0);
        }

        // Calculate overtime hours (beyond 8 hours, only if not late)
        double calculateOvertimeHours() {
            if (isLate() || loginTime == null || logoutTime == null) return 0.0;
            LocalTime effectiveStart = loginTime.isBefore(SHIFT_START) ? SHIFT_START : loginTime;
            LocalTime effectiveEnd = logoutTime.isAfter(SHIFT_END) ? SHIFT_END : logoutTime;
            if (effectiveStart.isAfter(effectiveEnd)) return 0.0;
            long minutesWorked = ChronoUnit.MINUTES.between(effectiveStart, effectiveEnd);
            double totalHours = minutesWorked / 60.0;
            if (totalHours > 1) {
                totalHours -= 1.0; // Subtract 1-hour lunch break
            }
            double overtimeHours = totalHours > REGULAR_HOURS_PER_DAY ? totalHours - REGULAR_HOURS_PER_DAY : 0.0;
            return Math.max(overtimeHours, 0.0);
        }

        // Calculate tardiness hours (minutes late beyond 8:15 AM)
        double calculateTardinessHours() {
            if (loginTime == null || loginTime.isBefore(GRACE_PERIOD_END)) return 0.0;
            long minutesLate = ChronoUnit.MINUTES.between(GRACE_PERIOD_END, loginTime);
            return minutesLate / 60.0;
        }

        // Check if the employee is late (after 8:15 AM)
        boolean isLate() {
            return loginTime != null && loginTime.isAfter(GRACE_PERIOD_END);
        }
    }

    // Class to hold weekly hours summary
    static class PeriodSummary {
        double regularHoursWorked;
        double overtimeHours;
        double tardinessHours;
        boolean hasLateArrival;

        PeriodSummary(double regularHoursWorked, double overtimeHours, double tardinessHours, boolean hasLateArrival) {
            this.regularHoursWorked = regularHoursWorked;
            this.overtimeHours = overtimeHours;
            this.tardinessHours = tardinessHours;
            this.hasLateArrival = hasLateArrival;
        }
    }

    // Class to hold a period (start and end date)
    static class Period {
        LocalDate startDate;
        LocalDate endDate;

        Period(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return startDate + " to " + endDate;
        }
    }

    // Logger setup
    private static final Logger LOGGER = Logger.getLogger(MOTORPH.class.getName());
    static {
        try {
            // Remove the default ConsoleHandler
            for (Handler handler : LOGGER.getHandlers()) {
                LOGGER.removeHandler(handler);
            }
            LOGGER.setUseParentHandlers(false); // Prevent the logger from using the parent handlers (which include ConsoleHandler)

            // Add the FileHandler to write logs to payroll.log
            FileHandler fileHandler = new FileHandler("C:\\Users\\Kristov\\Documents\\COMPROG1\\JavaApplication3\\src\\payroll.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Error setting up logger: " + e.getMessage());
        }
    }

    // Calculate SSS deduction based on salary brackets (simplified table)
    static double calculateSSSDeduction(double monthlySalary) {
        if (monthlySalary < 3250) return 135.00;
        else if (monthlySalary < 3750) return 157.50;
        else if (monthlySalary < 4250) return 180.00;
        else if (monthlySalary < 4750) return 202.50;
        else if (monthlySalary < 5250) return 225.00;
        else if (monthlySalary < 5750) return 247.50;
        else if (monthlySalary < 6250) return 270.00;
        else if (monthlySalary < 6750) return 292.50;
        else if (monthlySalary < 7250) return 315.00;
        else if (monthlySalary < 7750) return 337.50;
        else if (monthlySalary < 8250) return 360.00;
        else if (monthlySalary < 8750) return 382.50;
        else if (monthlySalary < 9250) return 405.00;
        else if (monthlySalary < 9750) return 427.50;
        else if (monthlySalary < 10250) return 450.00;
        else if (monthlySalary < 10750) return 472.50;
        else if (monthlySalary < 11250) return 495.00;
        else if (monthlySalary < 11750) return 517.50;
        else if (monthlySalary < 12250) return 540.00;
        else if (monthlySalary < 12750) return 562.50;
        else if (monthlySalary < 13250) return 585.00;
        else if (monthlySalary < 13750) return 607.50;
        else if (monthlySalary < 14250) return 630.00;
        else if (monthlySalary < 14750) return 652.50;
        else if (monthlySalary < 15250) return 675.00;
        else if (monthlySalary < 15750) return 697.50;
        else if (monthlySalary < 16250) return 720.00;
        else if (monthlySalary < 16750) return 742.50;
        else if (monthlySalary < 17250) return 765.00;
        else if (monthlySalary < 17750) return 787.50;
        else if (monthlySalary < 18250) return 810.00;
        else if (monthlySalary < 18750) return 832.50;
        else if (monthlySalary < 19250) return 855.00;
        else if (monthlySalary < 19750) return 877.50;
        else if (monthlySalary < 20250) return 900.00;
        else if (monthlySalary < 20750) return 922.50;
        else if (monthlySalary < 21250) return 945.00;
        else if (monthlySalary < 21750) return 967.50;
        else if (monthlySalary < 22250) return 990.00;
        else if (monthlySalary < 22750) return 1012.50;
        else if (monthlySalary < 23250) return 1035.00;
        else if (monthlySalary < 23750) return 1057.50;
        else if (monthlySalary < 24250) return 1080.00;
        else if (monthlySalary < 24750) return 1102.50;
        else return 1125.00; // Max contribution for ₱24,750 and above
    }

    // Calculate PhilHealth deduction (1.5% employee share)
    static double calculatePhilHealthDeduction(double monthlySalary) {
        return (monthlySalary * 0.03) / 2; // 3% total, employee pays half
    }

    // Calculate Pag-IBIG deduction (2%, max ₱100)
    static double calculatePagIBIGDeduction(double monthlySalary) {
        double pagIbig = monthlySalary * 0.02;
        return Math.min(pagIbig, 100.00); // Cap at ₱100
    }

    // Calculate withholding tax based on Philippine tax brackets (simplified for monthly salary)
    static double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20833) return 0.0;
        else if (taxableIncome <= 33333) return (taxableIncome - 20833) * 0.20;
        else if (taxableIncome <= 66667) return 2500 + (taxableIncome - 33333) * 0.25;
        else if (taxableIncome <= 166667) return 10833 + (taxableIncome - 66667) * 0.30;
        else if (taxableIncome <= 666667) return 40833 + (taxableIncome - 166667) * 0.32;
        else return 200833 + (taxableIncome - 666667) * 0.35;
    }

    // Get available weekly periods for an employee from weeklyhours.csv
    static List<Period> getWeeklyPeriods(String empId, String weeklyHoursFile) {
        List<Period> periods = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LOGGER.info("Fetching weekly periods for Employee ID " + empId + " from weeklyhours.csv");

        try (CSVReader reader = new CSVReader(new FileReader(weeklyHoursFile))) {
            String[] row;
            boolean foundRecords = false;
            while ((row = reader.readNext()) != null) {
                if (row.length < 3) { // Expect at least empId, startDate, hours
                    LOGGER.warning("Skipping malformed row in weeklyhours.csv: " + String.join(",", row));
                    continue;
                }
                if (!row[0].trim().equals(empId)) { // Trim to handle potential spaces
                    continue;
                }

                foundRecords = true;
                try {
                    LocalDate startDate = LocalDate.parse(row[1], dateFormatter); // Start date in second column
                    LocalDate endDate = startDate.plusDays(6); // Assume a 7-day week (startDate to startDate + 6)
                    periods.add(new Period(startDate, endDate));
                    LOGGER.info("Found weekly period: " + startDate + " to " + endDate);
                } catch (DateTimeParseException e) {
                    LOGGER.severe("Error parsing date in row: " + String.join(",", row) + ". Error: " + e.getMessage());
                    continue;
                }
            }
            if (!foundRecords) {
                LOGGER.warning("No records found in weeklyhours.csv for Employee ID " + empId);
            }
        } catch (IOException e) {
            LOGGER.severe("Unable to read weeklyhours.csv at " + weeklyHoursFile + ". Error: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error reading weeklyhours.csv: " + e.getMessage());
        }

        // Sort periods by start date to ensure chronological order
        periods.sort((p1, p2) -> p1.startDate.compareTo(p2.startDate));
        return periods;
    }

    // Get available bi-weekly periods by grouping consecutive weeks
    static List<Period> getBiWeeklyPeriods(String empId, String weeklyHoursFile) {
        List<Period> weeklyPeriods = getWeeklyPeriods(empId, weeklyHoursFile);
        List<Period> biWeeklyPeriods = new ArrayList<>();

        LOGGER.info("Grouping weekly periods into bi-weekly periods for Employee ID " + empId);

        for (int i = 0; i < weeklyPeriods.size(); i++) {
            Period week1 = weeklyPeriods.get(i);
            // If this is the last week and it hasn't been paired, add it as a partial bi-weekly period
            if (i == weeklyPeriods.size() - 1) {
                biWeeklyPeriods.add(new Period(week1.startDate, week1.endDate));
                LOGGER.info("Found partial bi-weekly period (single week): " + week1.startDate + " to " + week1.endDate);
                break;
            }

            Period week2 = weeklyPeriods.get(i + 1);
            // Check if the weeks are close enough to be considered a bi-weekly period (within 3 days)
            long daysBetween = ChronoUnit.DAYS.between(week1.endDate, week2.startDate);
            if (daysBetween >= 0 && daysBetween <= 3) { // Allow up to a 3-day gap
                biWeeklyPeriods.add(new Period(week1.startDate, week2.endDate));
                LOGGER.info("Found bi-weekly period: " + week1.startDate + " to " + week2.endDate);
                i++; // Skip the next week since it’s paired
            } else {
                // If not close enough, treat week1 as a partial bi-weekly period
                biWeeklyPeriods.add(new Period(week1.startDate, week1.endDate));
                LOGGER.info("Found partial bi-weekly period (single week): " + week1.startDate + " to " + week1.endDate);
            }
        }

        return biWeeklyPeriods;
    }

    // Get available monthly periods by grouping weeks within the same month
    static List<Period> getMonthlyPeriods(String empId, String weeklyHoursFile) {
        List<Period> weeklyPeriods = getWeeklyPeriods(empId, weeklyHoursFile);
        List<Period> monthlyPeriods = new ArrayList<>();

        LOGGER.info("Grouping weekly periods into monthly periods for Employee ID " + empId);

        LocalDate currentMonthStart = null;
        LocalDate currentMonthEnd = null;

        for (Period week : weeklyPeriods) {
            LocalDate weekStart = week.startDate;
            LocalDate weekEnd = week.endDate;

            // Determine the month of the week (use start date's month)
            LocalDate monthStart = weekStart.withDayOfMonth(1);
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            if (currentMonthStart == null || 
                weekStart.getMonthValue() != currentMonthStart.getMonthValue() || 
                weekStart.getYear() != currentMonthStart.getYear()) {
                // New month detected, add the previous month to the list
                if (currentMonthStart != null) {
                    monthlyPeriods.add(new Period(currentMonthStart, currentMonthEnd));
                    LOGGER.info("Found monthly period: " + currentMonthStart + " to " + currentMonthEnd);
                }
                // Start a new monthly period
                currentMonthStart = weekStart;
                currentMonthEnd = weekEnd;
            } else {
                // Extend the current monthly period if the weeks are consecutive
                if (weekStart.minusDays(1).equals(currentMonthEnd)) {
                    currentMonthEnd = weekEnd;
                } else {
                    // Non-consecutive weeks: end the current monthly period and start a new one
                    monthlyPeriods.add(new Period(currentMonthStart, currentMonthEnd));
                    LOGGER.info("Found monthly period: " + currentMonthStart + " to " + currentMonthEnd);
                    currentMonthStart = weekStart;
                    currentMonthEnd = weekEnd;
                }
            }
        }

        // Add the last month
        if (currentMonthStart != null) {
            monthlyPeriods.add(new Period(currentMonthStart, currentMonthEnd));
            LOGGER.info("Found monthly period: " + currentMonthStart + " to " + currentMonthEnd);
        }

        return monthlyPeriods;
    }

    // Calculate period summary for a given period using Attendance.csv, excluding weekends
    static PeriodSummary calculatePeriodSummary(String empId, LocalDate periodStart, LocalDate periodEnd, String attendanceFile) {
        List<AttendanceRecord> records = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("h:mm:ss")
            .optionalStart()
            .appendPattern(" ")
            .optionalEnd()
            .appendPattern("a")
            .toFormatter(Locale.ENGLISH);

        LOGGER.info("Looking for attendance records for Employee ID " + empId + " from " + periodStart + " to " + periodEnd);

        try (CSVReader reader = new CSVReader(new FileReader(attendanceFile))) {
            String[] header = reader.readNext();
            if (header == null) {
                LOGGER.warning("Attendance.csv is empty");
                return new PeriodSummary(0.0, 0.0, 0.0, false);
            }

            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length < 6) {
                    LOGGER.warning("Skipping malformed row: " + String.join(",", row));
                    continue;
                }
                if (!row[0].equals(empId)) {
                    LOGGER.info("Skipping record for Employee ID " + row[0]);
                    continue;
                }

                try {
                    LocalDate recordDate = LocalDate.parse(row[3], dateFormatter);
                    if (!recordDate.isBefore(periodStart) && !recordDate.isAfter(periodEnd)) {
                        // Skip weekends (Saturday and Sunday)
                        DayOfWeek dayOfWeek = recordDate.getDayOfWeek();
                        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                            LOGGER.info("Skipping weekend record on " + recordDate);
                            continue;
                        }
                        String loginTimeStr = row[4].replaceAll("\\s+", " ").trim();
                        String logoutTimeStr = row[5].replaceAll("\\s+", " ").trim();
                        LOGGER.info("Raw login time: '" + loginTimeStr + "', Bytes: " + Arrays.toString(loginTimeStr.getBytes()));
                        LOGGER.info("Raw logout time: '" + logoutTimeStr + "', Bytes: " + Arrays.toString(logoutTimeStr.getBytes()));
                        LocalTime loginTime = loginTimeStr.isEmpty() ? null : LocalTime.parse(loginTimeStr, timeFormatter);
                        LocalTime logoutTime = logoutTimeStr.isEmpty() ? null : LocalTime.parse(logoutTimeStr, timeFormatter);
                        records.add(new AttendanceRecord(recordDate, loginTime, logoutTime));
                        LOGGER.info("Found record on " + recordDate + ": Login " + loginTime + ", Logout " + logoutTime);
                    }
                } catch (DateTimeParseException e) {
                    LOGGER.severe("Failed to parse date or time in row: " + String.join(",", row) + ". Error: " + e.getMessage());
                    continue;
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Unable to read Attendance.csv at " + attendanceFile + ". Error: " + e.getMessage());
            return new PeriodSummary(0.0, 0.0, 0.0, false);
        } catch (Exception e) {
            LOGGER.severe("Error reading attendance file: " + e.getMessage());
            return new PeriodSummary(0.0, 0.0, 0.0, false);
        }

        double totalRegularHours = 0.0;
        double totalOvertimeHours = 0.0;
        double totalTardinessHours = 0.0;
        boolean hasLateArrival = false;
        for (AttendanceRecord record : records) {
            double regularHours = record.calculateRegularHoursWorked();
            double overtimeHours = record.calculateOvertimeHours();
            double tardinessHours = record.calculateTardinessHours();
            totalRegularHours += regularHours;
            totalOvertimeHours += overtimeHours;
            totalTardinessHours += tardinessHours;
            if (record.isLate()) {
                hasLateArrival = true;
            }
            LOGGER.info("Record on " + record.date + ": Regular Hours: " + regularHours + ", Overtime Hours: " + overtimeHours + ", Tardiness Hours: " + tardinessHours);
        }
        LOGGER.info("Total regular hours: " + totalRegularHours + ", Total overtime hours: " + totalOvertimeHours + ", Total tardiness hours: " + totalTardinessHours + ", Has late arrival: " + hasLateArrival);
        return new PeriodSummary(totalRegularHours, totalOvertimeHours, totalTardinessHours, hasLateArrival);
    }

    // Export payslip data to MOTORPHPAYSLIP.csv
    static void exportPayslipToCSV(String empId, String fullName, String position, LocalDate startDate, LocalDate endDate, 
                                  double grossPay, double totalDeductions, double netPay, double tardinessHours, String payslipFile) {
        boolean fileExists = new java.io.File(payslipFile).exists();
        int maxRetries = 3;
        int retryDelayMs = 1000; // 1 second delay between retries

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(payslipFile, true))) {
                // Write header if the file is new
                if (!fileExists) {
                    String[] header = {"Employee ID", "Name", "Position", "Period", "Gross Pay", "Total Deduction", "Net Pay", "Days Late"};
                    writer.writeNext(header);
                }
                // Calculate days late (assuming 8 hours per day for simplicity)
                int daysLate = (int) Math.ceil(tardinessHours / 8.0);
                String period = startDate + " to " + endDate;
                String[] row = {
                    empId,
                    fullName,
                    position,
                    period,
                    String.format("%.2f", grossPay),
                    String.format("%.2f", totalDeductions),
                    String.format("%.2f", netPay),
                    String.valueOf(daysLate)
                };
                writer.writeNext(row);
                return; // Success, exit the method
            } catch (IOException e) {
                if (attempt == maxRetries) {
                    LOGGER.severe("Error writing to MOTORPHPAYSLIP.csv after " + maxRetries + " attempts: " + e.getMessage());
                    return; // Give up after max retries
                }
                LOGGER.warning("Attempt " + attempt + " failed to write to MOTORPHPAYSLIP.csv: " + e.getMessage() + ". Retrying in " + retryDelayMs + "ms...");
                try {
                    Thread.sleep(retryDelayMs); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    LOGGER.severe("Interrupted while waiting to retry writing to MOTORPHPAYSLIP.csv: " + ie.getMessage());
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Updated file paths to the src directory
        String employeeFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\JavaApplication3\\src\\Employee.csv";
        String attendanceFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\JavaApplication3\\src\\Attendance.csv";
        String weeklyHoursFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\JavaApplication3\\src\\weeklyhours.csv";
        String payslipFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\JavaApplication3\\src\\MOTORPHPAYSLIP.csv";
        boolean continueProcessing = true;

        System.out.println("=====================================");
        System.out.println("   Welcome to MotorPH Payroll System   ");
        System.out.println("=====================================");
        System.out.println("This program generates payroll slips for employees.\n");

        while (continueProcessing) {
            try {
                // Prompt for payroll type
                System.out.println("Select payroll computation type:");
                System.out.println("1. Weekly");
                System.out.println("2. Bi-Weekly");
                System.out.println("3. Monthly");
                System.out.print("Enter your choice (1-3): ");
                int payrollType;
                try {
                    payrollType = Integer.parseInt(sc.nextLine());
                    if (payrollType < 1 || payrollType > 3) {
                        throw new NumberFormatException("Choice out of range");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.\n");
                    LOGGER.warning("Invalid payroll type selection: " + e.getMessage());
                    continue;
                }

                String payrollLabel;
                switch (payrollType) {
                    case 1:
                        payrollLabel = "WEEKLY";
                        break;
                    case 2:
                        payrollLabel = "BI-WEEKLY";
                        break;
                    case 3:
                        payrollLabel = "MONTHLY";
                        break;
                    default:
                        payrollLabel = "WEEKLY"; // Fallback
                }

                System.out.print("Enter Employee ID: ");
                String empId = sc.nextLine();

                // Get available periods based on payroll type
                List<Period> periods;
                if (payrollType == 1) {
                    periods = getWeeklyPeriods(empId, weeklyHoursFile);
                } else if (payrollType == 2) {
                    periods = getBiWeeklyPeriods(empId, weeklyHoursFile);
                } else {
                    periods = getMonthlyPeriods(empId, weeklyHoursFile);
                }

                if (periods.isEmpty()) {
                    System.out.println("No " + payrollLabel.toLowerCase() + " payroll periods found for Employee ID " + empId + " in weeklyhours.csv.\n");
                    LOGGER.warning("No " + payrollLabel.toLowerCase() + " periods found for Employee ID " + empId + " in weeklyhours.csv");
                    System.out.print("Would you like to process another employee? (yes/no): ");
                    String response = sc.nextLine().trim().toLowerCase();
                    if (!response.equals("yes")) {
                        continueProcessing = false;
                    }
                    System.out.println();
                    continue;
                }

                // Display available periods and let user select
                System.out.println("\nAvailable " + payrollLabel.toLowerCase() + " payroll periods for Employee ID " + empId + ":");
                for (int i = 0; i < periods.size(); i++) {
                    System.out.println(i + 1 + ". " + periods.get(i));
                }
                System.out.print("Select a period by entering the number (1-" + periods.size() + "): ");
                int selection;
                try {
                    selection = Integer.parseInt(sc.nextLine()) - 1;
                    if (selection < 0 || selection >= periods.size()) {
                        throw new NumberFormatException("Selection out of range");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + periods.size() + ".\n");
                    LOGGER.warning("Invalid period selection for Employee ID " + empId + ": " + e.getMessage());
                    System.out.print("Would you like to process another employee? (yes/no): ");
                    String response = sc.nextLine().trim().toLowerCase();
                    if (!response.equals("yes")) {
                        continueProcessing = false;
                    }
                    System.out.println();
                    continue;
                }

                Period selectedPeriod = periods.get(selection);
                LocalDate startDate = selectedPeriod.startDate;
                LocalDate endDate = selectedPeriod.endDate;

                // Determine the period length in days for gross pay calculation
                long periodDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                double periodFractionOfMonth = periodDays / 30.0; // Approximate month as 30 days

                try (CSVReader reader = new CSVReader(new FileReader(employeeFile))) {
                    String[] row;
                    boolean found = false;

                    reader.readNext(); // Skip header

                    while ((row = reader.readNext()) != null) {
                        if (row[0].equals(empId)) {
                            found = true;

                            String fullName = row[1];
                            String position = row[2];
                            double hourlyRate = Double.parseDouble(row[8]);
                            double riceSubsidy = Double.parseDouble(row[4]);
                            double phoneAllowance = Double.parseDouble(row[5]);
                            double clothingAllowance = Double.parseDouble(row[6]);
                            double monthlyAllowance = riceSubsidy + phoneAllowance + clothingAllowance;
                            double periodAllowance = monthlyAllowance * periodFractionOfMonth;
                            double overtimeRate = hourlyRate * 1.25; // Overtime rate is 1.25x regular rate

                            LOGGER.info("Processing " + payrollLabel.toLowerCase() + " payroll for Employee ID: " + empId + ", Name: " + fullName + " for period " + startDate + " to " + endDate);

                            // Calculate period hours using Attendance.csv
                            PeriodSummary summary = calculatePeriodSummary(empId, startDate, endDate, attendanceFile);
                            double regularHours = summary.regularHoursWorked;
                            double overtimeHours = summary.hasLateArrival ? 0.0 : summary.overtimeHours; // No overtime if late
                            double tardinessHours = summary.tardinessHours;
                            boolean hasLateArrival = summary.hasLateArrival;

                            // Calculate gross pay
                            double basicPay = regularHours * hourlyRate;
                            double tardinessDeduction = tardinessHours * hourlyRate;
                            double overtimePay = overtimeHours * overtimeRate;
                            double adjustedBasicPay = basicPay - tardinessDeduction;
                            double grossPay = adjustedBasicPay + overtimePay + periodAllowance;

                            if (hasLateArrival) {
                                LOGGER.info("Employee arrived late during the period; overtime excluded.");
                            }

                            // Estimate monthly salary for deductions
                            double monthlySalary = (grossPay / periodDays) * 30; // Approximate monthly salary

                            // Calculate deductions based on period
                            double sssDeduction = calculateSSSDeduction(monthlySalary) * periodFractionOfMonth;
                            double philHealthDeduction = calculatePhilHealthDeduction(monthlySalary) * periodFractionOfMonth;
                            double pagIbigDeduction = calculatePagIBIGDeduction(monthlySalary) * periodFractionOfMonth;
                            double totalGovDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction;

                            // Calculate taxable income and withholding tax
                            double taxableIncome = grossPay - totalGovDeductions;
                            double monthlyTaxableIncome = (taxableIncome / periodDays) * 30;
                            double withholdingTax = calculateWithholdingTax(monthlyTaxableIncome) * periodFractionOfMonth;

                            double totalDeductions = totalGovDeductions + withholdingTax;
                            double netPay = grossPay - totalDeductions;

                            // Display payroll details
                            System.out.println("\n=========================================");
                            System.out.println("        MOTORPH " + payrollLabel + " PAYROLL        ");
                            System.out.println("=========================================\n");
                            System.out.println("POSITION: " + position);
                            System.out.println("EMPLOYEE: " + fullName);
                            System.out.println("ID: " + empId);
                            System.out.println("PERIOD: " + startDate + " to " + endDate);
                            System.out.println("HOURLY RATE: P" + String.format("%.2f", hourlyRate) + "\n");

                            System.out.printf("%-40s%-15s%s%n", "DESCRIPTION", "HOURS/AMOUNT", "AMOUNT");
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15.2fP %,9.2f%n", "BASIC PAY", regularHours, basicPay);
                            System.out.printf("%-40s%-15.2f-P %,9.2f%n", "TARDINESS", tardinessHours, tardinessDeduction);
                            System.out.printf("%-40s%-15.2fP %,9.2f%n", "OVERTIME", overtimeHours, overtimePay);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "RICE SUBSIDY", "", riceSubsidy * periodFractionOfMonth);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "PHONE ALLOWANCE", "", phoneAllowance * periodFractionOfMonth);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "CLOTHING ALLOWANCE", "", clothingAllowance * periodFractionOfMonth);
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15sP %,9.2f%n", "GROSS PAY", "", grossPay);
                            System.out.println("\n-----------------------------------------");
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "SSS DEDUCTION", "", sssDeduction);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "PhilHealth", "", philHealthDeduction);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "PAG-IBIG", "", pagIbigDeduction);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "WITHHOLDING TAX", "", withholdingTax);
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "TOTAL DEDUCTIONS", "", totalDeductions);
                            System.out.println("=========================================");
                            System.out.printf("NET PAY: P%,.2f%n", netPay);
                            System.out.println("=========================================\n");

                            // Export payslip to MOTORPHPAYSLIP.csv
                            exportPayslipToCSV(empId, fullName, position, startDate, endDate, grossPay, totalDeductions, netPay, tardinessHours, payslipFile);

                            // Check if the export was successful
                            boolean exportSuccessful = false;
                            try (CSVReader reader2 = new CSVReader(new FileReader(payslipFile))) {
                                String[] lastLine = null;
                                String[] line;
                                while ((line = reader2.readNext()) != null) {
                                    lastLine = line;
                                }
                                if (lastLine != null && lastLine[0].equals(empId) && lastLine[3].equals(startDate + " to " + endDate)) {
                                    exportSuccessful = true;
                                }
                            } catch (IOException e) {
                                LOGGER.severe("Error verifying export to MOTORPHPAYSLIP.csv: " + e.getMessage());
                            }

                            if (exportSuccessful) {
                                System.out.println("Payslip successfully exported to " + payslipFile);
                            } else {
                                System.out.println("WARNING: Failed to export payslip to " + payslipFile + ". The file might be in use by another program (e.g., Excel).");
                                System.out.println("Export skipped. Please check the log file for details.");
                            }

                            System.out.println("Thank you for using MotorPH Payroll System!");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Employee ID not found\n");
                        LOGGER.warning("Employee ID " + empId + " not found in Employee.csv");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing a number: " + e.getMessage() + "\n");
                    LOGGER.severe("Error parsing a number: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("An error occurred: " + e.getMessage() + "\n");
                    LOGGER.severe("An error occurred: " + e.getMessage());
                }

                System.out.print("Would you like to process another employee? (yes/no): ");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    continueProcessing = false;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
                LOGGER.severe("Unexpected error: " + e.getMessage());
                sc.nextLine();
            }
        }

        sc.close();
        System.out.println("=====================================");
        System.out.println("   MotorPH Payroll System Closed   ");
        System.out.println("=====================================");
        LOGGER.info("Payroll system closed.");
    }
}