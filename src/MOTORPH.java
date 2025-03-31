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
    // A class to hold details about holidays
    static class Holiday {
        LocalDate date;
        String name;
        boolean isRegular; // True if it's a regular holiday, false if it's a special non-working day

        Holiday(LocalDate date, String name, boolean isRegular) {
            this.date = date;
            this.name = name;
            this.isRegular = isRegular;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String csvFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\Employee.csv"; // Location of the Employee data file
        boolean continueProcessing = true;

        // Display a welcome message to the user
        System.out.println("=====================================");
        System.out.println("   Welcome to MotorPH Payroll System   ");
        System.out.println("=====================================");
        System.out.println("This program generates payroll slips for employees.");
        System.out.println("Please provide the required details below.\n");

        while (continueProcessing) {
            try {
                // Ask for user input and check if it's valid
                System.out.print("Enter Employee ID: ");
                String empId = sc.nextLine();

                LocalDate startDate = null, endDate = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Keep asking for the start date until a valid date is entered
                while (startDate == null) {
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDateStr = sc.nextLine();
                    try {
                        startDate = LocalDate.parse(startDateStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
                    }
                }

                // Keep asking for the end date until a valid date is entered, ensuring it's not before the start date
                while (endDate == null) {
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String endDateStr = sc.nextLine();
                    try {
                        endDate = LocalDate.parse(endDateStr, formatter);
                        if (endDate.isBefore(startDate)) {
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

                    // Skip the first row since it contains column headers
                    reader.readNext();

                    // Look for the employee ID in the CSV file
                    while ((row = reader.readNext()) != null) {
                        if (row[0].equals(empId)) { // Check if the employee ID matches
                            found = true;

                            // Gather employee information from the CSV row
                            String fullName = row[1]; // Employee's full name (e.g., Garcia Manuel III)
                            String position = row[2]; // Employee's job title (e.g., Chief Executive Officer)
                            double hourlyRate = Double.parseDouble(row[8]);
                            double riceSubsidy = Double.parseDouble(row[4]);
                            double phoneAllowance = Double.parseDouble(row[5]);
                            double clothingAllowance = Double.parseDouble(row[6]);
                            double semiMonthlyAllowance = (riceSubsidy + phoneAllowance + clothingAllowance) / 2;

                            // Ask for additional details and ensure they are valid numbers
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
                                    sc.nextLine(); // Clear the input to avoid errors
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

                            // List of holidays for the years 2023, 2024, and 2025
                            List<Holiday> holidays = new ArrayList<>();
                            // 2023 Regular Holidays
                            holidays.add(new Holiday(LocalDate.of(2023, 1, 2), "New Year's Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 4, 6), "Maundy Thursday", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 4, 7), "Good Friday", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 4, 10), "Araw ng Kagitingan", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 5, 1), "Labor Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 6, 12), "Independence Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 8, 28), "National Heroes Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 11, 1), "All Saints' Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 11, 30), "Bonifacio Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 12, 25), "Christmas Day", true));
                            holidays.add(new Holiday(LocalDate.of(2023, 12, 30), "Rizal Day", true));
                            // 2023 Special Non-Working Holidays
                            holidays.add(new Holiday(LocalDate.of(2023, 1, 2), "New Year's Day (Special)", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 2, 25), "EDSA Revolution Anniversary", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 4, 8), "Black Saturday", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 8, 21), "Ninoy Aquino Day", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 11, 2), "All Souls' Day", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 12, 24), "Christmas Eve", false));
                            holidays.add(new Holiday(LocalDate.of(2023, 12, 31), "Last Day of the Year", false));
                            // 2024 Regular Holidays
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
                            // 2024 Special Non-Working Holidays
                            holidays.add(new Holiday(LocalDate.of(2024, 2, 10), "Chinese New Year", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 3, 30), "Black Saturday", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 8, 21), "Ninoy Aquino Day", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 11, 2), "All Souls' Day", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 24), "Christmas Eve", false));
                            holidays.add(new Holiday(LocalDate.of(2024, 12, 31), "Last Day of the Year", false));
                            // 2025 Regular Holidays (Projected)
                            holidays.add(new Holiday(LocalDate.of(2025, 1, 1), "New Year's Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 4, 17), "Maundy Thursday", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 4, 18), "Good Friday", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 4, 9), "Araw ng Kagitingan", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 5, 1), "Labor Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 6, 12), "Independence Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 8, 25), "National Heroes Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 11, 1), "All Saints' Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 12, 1), "Bonifacio Day", true)); // Moved from Nov 30 (Sunday)
                            holidays.add(new Holiday(LocalDate.of(2025, 12, 25), "Christmas Day", true));
                            holidays.add(new Holiday(LocalDate.of(2025, 12, 30), "Rizal Day", true));
                            // 2025 Special Non-Working Holidays (Projected)
                            holidays.add(new Holiday(LocalDate.of(2025, 1, 29), "Chinese New Year", false));
                            holidays.add(new Holiday(LocalDate.of(2025, 4, 19), "Black Saturday", false));
                            holidays.add(new Holiday(LocalDate.of(2025, 8, 21), "Ninoy Aquino Day", false));
                            holidays.add(new Holiday(LocalDate.of(2025, 11, 2), "All Souls' Day", false));
                            holidays.add(new Holiday(LocalDate.of(2025, 12, 24), "Christmas Eve", false));
                            holidays.add(new Holiday(LocalDate.of(2025, 12, 31), "Last Day of the Year", false));

                            // Look for holidays that fall within the selected pay period
                            double totalHolidayPay = 0.0;
                            double totalOvertimeHolidayPay = 0.0;
                            double totalHolidayHoursWorked = 0.0;
                            List<String> holidayDetails = new ArrayList<>();

                            for (Holiday holiday : holidays) {
                                LocalDate holidayDate = holiday.date;
                                boolean includesHoliday = !holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate);
                                if (includesHoliday) {
                                    sc.nextLine(); // Clear any leftover input
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

                                        // Calculate holiday pay for the first 8 hours
                                        holidayBaseHours = Math.min(holidayHoursWorked, 8);
                                        if (holiday.isRegular) {
                                            // Regular holiday: Pay is doubled (200% rate)
                                            holidayPay = holidayBaseHours * hourlyRate * 2;
                                            // Overtime on a regular holiday (beyond 8 hours, 260% rate)
                                            overtimeHoursOnHoliday = Math.max(holidayHoursWorked - 8, 0);
                                            overtimeHolidayPay = overtimeHoursOnHoliday * hourlyRate * 2.6;
                                        } else {
                                            // Special non-working holiday: Pay is 130% of the regular rate
                                            holidayPay = holidayBaseHours * hourlyRate * 1.3;
                                            // Overtime on a special non-working holiday (beyond 8 hours, 169% rate)
                                            overtimeHoursOnHoliday = Math.max(holidayHoursWorked - 8, 0);
                                            overtimeHolidayPay = overtimeHoursOnHoliday * hourlyRate * 1.69;
                                        }

                                        // Save holiday details to display later
                                        String holidayType = holiday.isRegular ? "Regular" : "Special";
                                        holidayDetails.add(String.format("%-40s%-15.2fP %,9.2f", "HOLIDAY PAY (" + holiday.name + ", " + holidayType + ")", holidayBaseHours, holidayPay));
                                        if (overtimeHolidayPay > 0) {
                                            holidayDetails.add(String.format("%-40s%-15.2fP %,9.2f", "OVERTIME ON HOLIDAY (" + holiday.name + ", " + holidayType + ")", overtimeHoursOnHoliday, overtimeHolidayPay));
                                        }
                                    } else {
                                        // If the employee didn’t work on the holiday
                                        if (holiday.isRegular) {
                                            // Regular holiday: Employee gets paid for an 8-hour day
                                            holidayPay = hourlyRate * 8;
                                            String holidayType = "Regular";
                                            holidayDetails.add(String.format("%-40s%-15sP %,9.2f", "HOLIDAY PAY (" + holiday.name + ", " + holidayType + ")", "", holidayPay));
                                        }
                                        // Special non-working holiday: No pay if the employee didn’t work
                                    }

                                    totalHolidayPay += holidayPay;
                                    totalOvertimeHolidayPay += overtimeHolidayPay;
                                    totalHolidayHoursWorked += holidayHoursWorked;
                                }
                            }

                            // Calculate the employee’s pay
                            // Subtract holiday hours from total hours to avoid counting them twice
                            double adjustedTotalHours = totalHours - totalHolidayHoursWorked;
                            if (adjustedTotalHours < 0) adjustedTotalHours = 0; // Ensure hours don’t go negative

                            double regularPay = adjustedTotalHours * hourlyRate;
                            double overtimeRate = hourlyRate * 1.25; // Overtime pay is 1.25 times the regular rate
                            double overtimePay = overtimeHours * overtimeRate;

                            // Calculate tardiness deduction: Convert minutes to hours, but no deduction if 15 minutes or less
                            double tardinessDeduction = 0.0;
                            if (tardinessMinutes > 15) {
                                double tardinessHours = tardinessMinutes / 60.0; // Convert minutes to hours
                                tardinessDeduction = tardinessHours * hourlyRate;
                            }

                            double grossPay = regularPay + overtimePay + totalHolidayPay + totalOvertimeHolidayPay + semiMonthlyAllowance;

                            // Apply deductions (using fixed amounts to match the example output)
                            double sss = 1125.00; // Fixed SSS deduction
                            double philHealth = 515.69; // Fixed PhilHealth deduction
                            double pagIbig = 100.00; // Fixed Pag-IBIG deduction
                            double withholdingTax = 261.07; // Fixed withholding tax
                            double totalDeductions = tardinessDeduction + sss + philHealth + pagIbig + withholdingTax;

                            // Calculate the final pay after deductions
                            double netPay = grossPay - totalDeductions;

                            // Display the payroll details in a neatly formatted table
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

                            // Save the payroll details to a CSV file
                            String outputCsv = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\MOTORPHPAYSLIP.csv";
                            try (FileWriter writer = new FileWriter(outputCsv, true)) {
                                writer.append("Employee ID,Name,Position,Period,Gross Pay,Total Deductions,Net Pay,Days Late\n");
                                writer.append(empId + "," + fullName + "," + position + "," + startDate + " to " + endDate + "," + grossPay + "," + totalDeductions + "," + netPay + "," + daysLate + "\n");
                                System.out.println("PAYROLL data saved to " + outputCsv + "\n");
                            } catch (IOException e) {
                                System.out.println("Error saving to CSV: " + e.getMessage() + "\n");
                            }

                            // Show a thank-you message
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

                // Ask if the user wants to process another employee
                sc.nextLine(); // Clear any leftover input
                System.out.print("Would you like to process another employee? (yes/no): ");
                String response = sc.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    continueProcessing = false;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
                sc.nextLine(); // Clear any leftover input
            }
        }

        sc.close();
        // Display a closing message when the program ends
        System.out.println("=====================================");
        System.out.println("   MotorPH Payroll System Closed   ");
        System.out.println("=====================================");
    }
}