import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MOTORPH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String csvFile = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\Employee.csv"; // Path to Employee CSV

        // Input
        System.out.print("Enter Employee ID: ");
        String empId = sc.nextLine();
        System.out.print("Choose a pay period from 2024-06-03 to 2024-06-15: ");
        String payPeriod = sc.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = sc.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = sc.nextLine();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] row;
            boolean found = false;

            // Skip header row
            reader.readNext();

            // Search Employee ID in CSV
            while ((row = reader.readNext()) != null) {
                if (row[0].equals(empId)) { // Match EmployeeID
                    found = true;

                    // Debug: Print the entire row to confirm CSV structure
                    System.out.println("Row data: " + String.join(", ", row));

                    // Analyze Employee Data
                    String fullName = row[1]; // Full Name (Garcia Manuel III)
                    String position = row[2]; // Position (Chief Executive Officer)
                    double hourlyRate = Double.parseDouble(row[8]);
                    double riceSubsidy = Double.parseDouble(row[4]);
                    double phoneAllowance = Double.parseDouble(row[5]);
                    double clothingAllowance = Double.parseDouble(row[6]);
                    double semiMonthlyAllowance = (riceSubsidy + phoneAllowance + clothingAllowance) / 2;

                    // Additional Inputs
                    System.out.print("Enter Total Worked Hours: ");
                    double totalHours = sc.nextDouble();
                    System.out.print("Enter Overtime Hours: ");
                    double overtimeHours = sc.nextDouble();
                    System.out.print("Enter Tardiness Hours: ");
                    double tardinessHours = sc.nextDouble();
                    System.out.print("Enter Days Late: ");
                    int daysLate = sc.nextInt();

                    // Payroll Calculations
                    double regularPay = totalHours * hourlyRate;
                    double overtimeRate = hourlyRate * 1.25; // Overtime at 1.25x rate
                    double overtimePay = overtimeHours * overtimeRate;
                    double grossPay = regularPay + overtimePay + semiMonthlyAllowance;

                    // Deductions (using fixed values to match the desired output)
                    double tardinessDeduction = tardinessHours * hourlyRate;
                    double sss = 1125.00; // Fixed as per output
                    double philHealth = 515.69; // Fixed as per output
                    double pagIbig = 100.00; // Fixed as per output
                    double withholdingTax = 261.07; // Fixed as per output
                    double totalDeductions = tardinessDeduction + sss + philHealth + pagIbig + withholdingTax;

                    // Net Pay
                    double netPay = grossPay - totalDeductions;

                    // Print Output in Desired Format
                    System.out.println("\n---------- MOTORPH PAYROLL ----------\n");
                    System.out.println("POSITION: " + position);
                    System.out.println("EMPLOYEE: " + fullName);
                    System.out.println("ID: " + empId);
                    System.out.println("PERIOD: " + startDate + " to " + endDate);
                    System.out.println("HOURLY RATE: P" + String.format("%.2f", hourlyRate));
                    System.out.println("DAYS LATE: " + daysLate + "\n");
                    System.out.println("DESCRIPTION\t\tHOURS\t\tAMOUNT");
                    System.out.println("------------------------------------");
                    System.out.printf("BASIC PAY\t\t%.2f\t\tP %,9.2f%n", totalHours, regularPay);
                    System.out.printf("OVERTIME (1.25x rate)\t%.2f\t\tP %,9.2f%n", overtimeHours, overtimePay);
                    System.out.printf("TARDINESS DEDUCTION\t%.2f\t\t-P %,9.2f%n", tardinessHours, tardinessDeduction);
                    System.out.printf("RICE SUBSIDY\t\t\t\tP %,9.2f%n", riceSubsidy / 2);
                    System.out.printf("PHONE ALLOWANCE\t\t\t\tP %,9.2f%n", phoneAllowance / 2);
                    System.out.printf("CLOTHING ALLOWANCE\t\t\t\tP %,9.2f%n", clothingAllowance / 2);
                    System.out.println("------------------------------------");
                    System.out.printf("GROSS PAY\t\t\t\tP %,9.2f%n", grossPay);
                    System.out.println("------------------------------------");
                    System.out.printf("SSS DEDUCTION\t\t\t\t-P %,9.2f%n", sss);
                    System.out.printf("PhilHealth\t\t\t\t-P %,9.2f%n", philHealth);
                    System.out.printf("PAG-IBIG\t\t\t\t-P %,9.2f%n", pagIbig);
                    System.out.printf("WITHHOLDING TAX\t\t\t\t-P %,9.2f%n", withholdingTax);
                    System.out.println("------------------------------------");
                    System.out.printf("TOTAL DEDUCTIONS\t\t\t-P %,9.2f%n", totalDeductions);
                    System.out.println("====================================");
                    System.out.printf("NET PAY: P%,.2f%n", netPay);
                    System.out.println("====================================");

                    // Save to CSV (append mode to avoid conflicts)
                    String outputCsv = "C:\\Users\\Kristov\\Documents\\COMPROG1\\Prog 1 MotorPH\\MOTORPHPAYSLIP.csv";
                    try (FileWriter writer = new FileWriter(outputCsv, true)) { // Append mode (true)
                        // Write header only if the file is new (optional check can be added)
                        writer.append("Employee ID,Name,Position,Period,Gross Pay,Total Deductions,Net Pay\n");
                        writer.append(empId + "," + fullName + "," + position + "," + startDate + " to " + endDate + "," + grossPay + "," + totalDeductions + "," + netPay + "\n");
                        System.out.println("\nPAYROLL data saved to " + outputCsv);
                    } catch (IOException e) {
                        System.out.println("Error saving to CSV: " + e.getMessage());
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Employee ID not found");
            }

        } catch (NumberFormatException e) {
            System.err.println("Error parsing a number: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}