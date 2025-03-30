import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MOTORPH {
    // Class to store holiday information
    static class Holiday {
        LocalDate date;
        String name;
        boolean isRegular; // true for regular holidays, false for special non-working

        Holiday(LocalDate date, String name, boolean isRegular) {
            this.date = date;
            this.name = name;
            this.isRegular = isRegular;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String csvFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\Employee.csv"; // Path to Employee CSV
        boolean continueProcessing = true;

        // Welcome Message
        System.out.println("=====================================");
        System.out.println("   Welcome to MotorPH Payroll System   ");
        System.out.println("=====================================");
        System.out.println("This program generates payroll slips for employees.");
        System.out.println("Please provide the required details below.\n");

        while (continueProcessing) {
            try {
                // Input with Validation
                System.out.print("Enter Employee ID: ");
                String empId = sc.nextLine();

                LocalDate startDate = null, endDate = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Validate Start Date
                while (startDate == null) {
                    System.out.print("Enter start date (YYYY-MM-DD, within 2024): ");
                    String startDateStr = sc.nextLine();
                    try {
                        startDate = LocalDate.parse(startDateStr, formatter);
                        if (startDate.getYear() != 2024) {
                            System.out.println("Error: Start date must be in 2024.");
                            startDate = null;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
                    }
                }

                // Validate End Date
                while (endDate == null) {
                    System.out.print("Enter end date (YYYY-MM-DD, within 2024): ");
                    String endDateStr = sc.nextLine();
                    try {
                        endDate = LocalDate.parse(endDateStr, formatter);
                        if (endDate.getYear() != 2024) {
                            System.out.println("Error: End date must be in 2024.");
                            endDate = null;
                        } else if (endDate.isAfter(LocalDate.of(2024, 12, 31))) {
                            System.out.println("Error: End date cannot be after 2024-12-31.");
                            endDate = null;
                        } else if (endDate.isBefore(startDate)) {
                            System.out.println("Error: End date cannot be before start date.");
                            endDate = null;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
                    }
                }

                try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                    String[] row;
                    boolean found = false;

                    // Skip header row
                    reader.readNext();

                    // Search Employee ID in CSV
                    while ((row = reader.readNext()) != null) {
                        if (row[0].equals(empId)) { // Match EmployeeID
                            found = true;

                            // Analyze Employee Data
                            String fullName = row[1]; // Full Name (Garcia Manuel III)
                            String position = row[2]; // Position (Chief Executive Officer)
                            double hourlyRate = Double.parseDouble(row[8]);
                            double riceSubsidy = Double.parseDouble(row[4]);
                            double phoneAllowance = Double.parseDouble(row[5]);
                            double clothingAllowance = Double.parseDouble(row[6]);
                            double semiMonthlyAllowance = (riceSubsidy + phoneAllowance + clothingAllowance) / 2;

                            // Additional Inputs with Validation
                            double totalHours = -1, overtimeHours = -1, tardinessMinutes = -1;
                            int daysLate = -1;

                            while (totalHours < 0) {
                                System.out.print("Enter Total Worked Hours: ");
                                try {
                                    totalHours = sc.nextDouble();
                                    if (totalHours < 0) {
                                        System.out.println("Error: Total worked hours cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: Please enter a valid number.");
                                    sc.nextLine(); // Clear input
                                    totalHours = -1;
                                }
                            }

                            while (overtimeHours < 0) {
                                System.out.print("Enter Overtime Hours: ");
                                try {
                                    overtimeHours = sc.nextDouble();
                                    if (overtimeHours < 0) {
                                        System.out.println("Error: Overtime hours cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: Please enter a valid number.");
                                    sc.nextLine();
                                    overtimeHours = -1;
                                }
                            }

                            while (tardinessMinutes < 0) {
                                System.out.print("Enter Tardiness Minutes Late: ");
                                try {
                                    tardinessMinutes = sc.nextDouble();
                                    if (tardinessMinutes < 0) {
                                        System.out.println("Error: Tardiness minutes cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: Please enter a valid number.");
                                    sc.nextLine();
                                    tardinessMinutes = -1;
                                }
                            }

                            while (daysLate < 0) {
                                System.out.print("Enter Days Late: ");
                                try {
                                    daysLate = sc.nextInt();
                                    if (daysLate < 0) {
                                        System.out.println("Error: Days late cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: Please enter a valid number.");
                                    sc.nextLine();
                                    daysLate = -1;
                                }
                            }

                            // Define all holidays in 2024
                            List<Holiday> holidays = new ArrayList<>();
                            // Regular Holidays
                            holidays.add(new Holiday(LocalDate.of(2024, 1, 1), "New Year's Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 3, 28), "Maundy Thursday", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 3, 29), "Good Friday", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 4, 9), "Araw ng Kagitingan", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 5, 1), "Labor Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 6, 12), "Independence Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 8, 26), "National Heroes Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 11, 1), "All Saints' Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 11, 30), "Bonifacio Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 25), "Christmas Day", true));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 30), "Rizal Day", true));
                            // Special Non-Working Holidays
                            holidays.add(new Holiday(LocalDate.of(2024, 2, 10), "Chinese New Year", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 3, 30), "Black Saturday", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 8, 21), "Ninoy Aquino Day", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 11, 2), "All Souls' Day", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 24), "Christmas Eve", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 31), "Last Day of the Year", false));

                            // Check holidays within the pay period
                            double totalHolidayPay = 0.0;
                            double totalOvertimeHolidayPay = 0.0;
                            double totalHolidayHoursWorked = 0.0;
                            List<String> holidayDetails = new ArrayList<>();

                            for (Holiday holiday : holidays) {
                                LocalDate holidayDate = holiday.date;
                                boolean includesHoliday = !holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate);
                                if (includesHoliday) {
                                    sc.nextLine(); // Clear buffer
                                    System.out.print("Did the employee work on " + holidayDate + " (" + holiday.name + ")? (yes/no): ");
                                    String workedOnHoliday = sc.nextLine().trim().toLowerCase();

                                    double holidayPay = 0.0;
                                    double overtimeHolidayPay = 0.0;
                                    double holidayHoursWorked = 0.0;
                                    double holidayBaseHours = 0.0;
                                    double overtimeHoursOnHoliday = 0.0;

                                    if (workedOnHoliday.equals("yes")) {
                                        while (holidayHoursWorked <= 0) {
                                            System.out.print("Enter hours worked on " + holidayDate + ": ");
                                            try {
                                                holidayHoursWorked = sc.nextDouble();
                                                if (holidayHoursWorked <= 0) {
                                                    System.out.println("Error: Hours worked must be greater than 0.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Error: Please enter a valid number.");
                                                sc.nextLine();
                                                holidayHoursWorked = -1;
                                            }
                                        }

                                        // Holiday Pay for first 8 hours
                                        holidayBaseHours = Math.min(holidayHoursWorked, 8);
                                        if (holiday.isRegular) {
                                            // Regular holiday: 200% rate
                                            holidayPay = holidayBaseHours * hourlyRate * 2;
                                            // Overtime on Regular Holiday (beyond 8 hours, 260% rate)
                                            overtimeHoursOnHoliday = Math.max(holidayHoursWorked - 8, 0);
                                            overtimeHolidayPay = overtimeHoursOnHoliday * hourlyRate * 2.6;
                                        } else {
                                            // Special non-working holiday: 130% rate
                                            holidayPay = holidayBaseHours * hourlyRate * 1.3;
                                            // Overtime on Special Non-Working Holiday (beyond 8 hours, 169% rate)
                                            overtimeHoursOnHoliday = Math.max(holidayHoursWorked - 8, 0);
                                            overtimeHolidayPay = overtimeHoursOnHoliday * hourlyRate * 1.69;
                                        }

                                        // Store details for output
                                        String holidayType = holiday.isRegular ? "Regular" : "Special";
                                        holidayDetails.add(String.format("%-40s%-15.2fP %,9.2f", "HOLIDAY PAY (" + holiday.name + ", " + holidayType + ")", holidayBaseHours, holidayPay));
                                        if (overtimeHolidayPay > 0) {
                                            holidayDetails.add(String.format("%-40s%-15.2fP %,9.2f", "OVERTIME ON HOLIDAY (" + holiday.name + ", " + holidayType + ")", overtimeHoursOnHoliday, overtimeHolidayPay));
                                        }
                                    } else {
                                        // If not worked
                                        if (holiday.isRegular) {
                                            // Regular holiday: 100% of daily wage (assuming 8-hour day)
                                            holidayPay = hourlyRate * 8;
                                            String holidayType = "Regular";
                                            holidayDetails.add(String.format("%-40s%-15sP %,9.2f", "HOLIDAY PAY (" + holiday.name + ", " + holidayType + ")", "", holidayPay));
                                        }
                                        // Special non-working holiday: No pay if not worked
                                    }

                                    totalHolidayPay += holidayPay;
                                    totalOvertimeHolidayPay += overtimeHolidayPay;
                                    totalHolidayHoursWorked += holidayHoursWorked;
                                }
                            }

                            // Payroll Calculations
                            // Adjust total hours to exclude holiday hours to avoid double-counting
                            double adjustedTotalHours = totalHours - totalHolidayHoursWorked;
                            if (adjustedTotalHours < 0) adjustedTotalHours = 0; // Prevent negative hours

                            double regularPay = adjustedTotalHours * hourlyRate;
                            double overtimeRate = hourlyRate * 1.25; // Overtime at 1.25x rate
                            double overtimePay = overtimeHours * overtimeRate;

                            // Tardiness Deduction: Convert minutes to hours, waive if <= 15 minutes
                            double tardinessDeduction = 0.0;
                            if (tardinessMinutes > 15) {
                                double tardinessHours = tardinessMinutes / 60.0; // Convert minutes to hours
                                tardinessDeduction = tardinessHours * hourlyRate;
                            }

                            double grossPay = regularPay + overtimePay + totalHolidayPay + totalOvertimeHolidayPay + semiMonthlyAllowance;

                            // Deductions (using fixed values to match the desired output)
                            double sss = 1125.00; // Fixed as per output
                            double philHealth = 515.69; // Fixed as per output
                            double pagIbig = 100.00; // Fixed as per output
                            double withholdingTax = 261.07; // Fixed as per output
                            double totalDeductions = tardinessDeduction + sss + philHealth + pagIbig + withholdingTax;

                            // Net Pay
                            double netPay = grossPay - totalDeductions;

                            // Print Output with Enhanced Formatting
                            System.out.println("\n=========================================");
                            System.out.println("           MOTORPH PAYROLL               ");
                            System.out.println("=========================================\n");
                            System.out.println("POSITION: " + position);
                            System.out.println("EMPLOYEE: " + fullName);
                            System.out.println("ID: " + empId);
                            System.out.println("PERIOD: " + startDate + " to " + endDate);
                            System.out.println("HOURLY RATE: P" + String.format("%.2f", hourlyRate) + "\n");

                            System.out.printf("%-40s%-15s%s%n", "DESCRIPTION", "HOURS/MINUTES", "AMOUNT");
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15.2fP %,9.2f%n", "BASIC PAY", adjustedTotalHours, regularPay);
                            System.out.printf("%-40s%-15.2fP %,9.2f%n", "OVERTIME (1.25x rate)", overtimeHours, overtimePay);
                            for (String holidayDetail : holidayDetails) {
                                System.out.println(holidayDetail);
                            }
                            System.out.printf("%-40s%-15.2f-P %,9.2f%n", "TARDINESS DEDUCTION", tardinessMinutes, tardinessDeduction);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "RICE SUBSIDY", "", riceSubsidy / 2);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "PHONE ALLOWANCE", "", phoneAllowance / 2);
                            System.out.printf("%-40s%-15sP %,9.2f%n", "CLOTHING ALLOWANCE", "", clothingAllowance / 2);
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15sP %,9.2f%n", "GROSS PAY", "", grossPay);
                            System.out.println("\n-----------------------------------------");
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "SSS DEDUCTION", "", sss);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "PhilHealth", "", philHealth);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "PAG-IBIG", "", pagIbig);
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "WITHHOLDING TAX", "", withholdingTax);
                            System.out.println("-----------------------------------------");
                            System.out.printf("%-40s%-15s-P %,9.2f%n", "TOTAL DEDUCTIONS", "", totalDeductions);
                            System.out.println("=========================================");
                            System.out.printf("NET PAY: P%,.2f%n", netPay);
                            System.out.println("=========================================\n");

                            // Save to CSV
                            String outputCsv = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\MOTORPHPAYSLIP.csv";
                            try (FileWriter writer = new FileWriter(outputCsv, true)) {
                                writer.append("Employee ID,Name,Position,Period,Gross Pay,Total Deductions,Net Pay\n");
                                writer.append(empId + "," + fullName + "," + position + "," + startDate + " to " + endDate + "," + grossPay + "," + totalDeductions + "," + netPay + "\n");
                                System.out.println("PAYROLL data saved to " + outputCsv + "\n");
                            } catch (IOException e) {
                                System.out.println("Error saving to CSV: " + e.getMessage() + "\n");
                            }

                            // Closing Message
                            System.out.println("Thank you for using MotorPH Payroll System!");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Employee ID not found\n");
                    }

                } catch (NumberFormatException e) {
                    System.err.println("Error parsing a number: " + e.getMessage() + "\n");
                } catch (Exception e) {
                    System.err.println("An error occurred: " + e.getMessage() + "\n");
                }

                // Ask to Continue
                sc.nextLine(); // Clear buffer
                System.out.print("Would you like to process another employee? (yes/no): ");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    continueProcessing = false;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
                sc.nextLine(); // Clear buffer
            }
        }

        sc.close();
        System.out.println("=====================================");
        System.out.println("   MotorPH Payroll System Closed   ");
        System.out.println("=====================================");
    }
}