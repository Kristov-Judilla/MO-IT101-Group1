# MO-IT101-Group1

A collection of projects for the MO-IT101 Group 1 team.

## Payroll Hub (MotorPH Payroll System)

Payroll Hub is a simple Java-based payroll system for MotorPH employees that:

- Calculates work hours with a 15-minute grace period for tardiness
- Computes gross pay based on worked hours, overtime, and holiday pay
- Applies government deductions (SSS, PhilHealth, Pag-IBIG, withholding tax)
- Determines net pay after deductions
- Reads employee data from CSV files for processing
- Saves payroll data to a CSV file for record-keeping

📊 **Expected Output**

Below is an example output for Employee ID 10001 with a pay period from 2024-06-03 to 2024-06-18, with 15 minutes of tardiness (within the grace period) and 8 hours worked on Independence Day (2024-06-12):

```
=====================================
   Welcome to MotorPH Payroll System   
=====================================
This program generates payroll slips for employees.
Please provide the required details below.

Enter Employee ID: 10001
Enter start date (YYYY-MM-DD): 2024-06-03
Enter end date (YYYY-MM-DD): 2024-06-18
Enter Total Worked Hours: 80
Enter Overtime Hours: 0
Enter Tardiness Minutes Late: 15
Enter Days Late: 1
Did the employee work on 2024-06-12 (Independence Day)? (yes/no): yes
Enter hours worked on 2024-06-12: 8

=========================================
           MOTORPH PAYROLL               
=========================================

POSITION: Chief Executive Officer
EMPLOYEE: Garcia Manuel III
ID: 10001
PERIOD: 2024-06-03 to 2024-06-18
HOURLY RATE: P535.71

DESCRIPTION                             HOURS/MINUTES  AMOUNT
-----------------------------------------
BASIC PAY                               72.00          P 38,571.12
OVERTIME (1.25x rate)                   0.00           P      0.00
HOLIDAY PAY (Independence Day, Regular) 8.00           P  8,571.36
TARDINESS DEDUCTION                     15.00          -P      0.00
RICE SUBSIDY                                           P    750.00
PHONE ALLOWANCE                                        P  1,000.00
CLOTHING ALLOWANCE                                     P    500.00
-----------------------------------------
GROSS PAY                                              P 49,392.48

-----------------------------------------
SSS DEDUCTION                                          -P  1,125.00
PhilHealth                                             -P    515.69
PAG-IBIG                                               -P    100.00
WITHHOLDING TAX                                        -P    261.07
-----------------------------------------
TOTAL DEDUCTIONS                                       -P  2,001.76
=========================================
NET PAY: P47,390.72
=========================================

Thank you for using MotorPH Payroll System!
Would you like to process another employee? (yes/no): 
```

⚙️ **Features**

✅ Calculates worked hours per pay period, including overtime (1.25x rate)  
✅ Computes gross pay based on worked hours, overtime, and holiday pay  
✅ Supports all Philippine holidays (regular and special non-working) for 2023, 2024, and 2025  
✅ Allows flexible pay periods that can span across years  
✅ Applies a 15-minute grace period for tardiness (no deduction if ≤15 minutes)  
✅ Applies government deductions (SSS, PhilHealth, Pag-IBIG, withholding tax)  
✅ Determines net pay after deductions  
✅ Saves payroll data to `MotorPHPayslip.csv`, including days late  
✅ Includes input validation for dates, hours, tardiness, and days late  
✅ Formats output for readability with aligned columns  

📂 **File Handling**

The program reads input from:  
📌 `Employee.csv` (Employee data including hourly rate and allowances)  

The program writes output to:  
📌 `MotorPHPayslip.csv` (Payroll data in CSV format)  

🏗 **File Structure**

```
/MO-IT101-Group1
├── MotorPH-Payroll-System/
│   ├── src/
│   │   └── MotorPH.java
│   ├── data/
│   │   └── Employee.csv
│   └── MotorPHPayslip.csv
├── README.md
├── .gitignore
└── LICENSE
```

🚧 **Project Status**

🔧 Completed

🔍 **Things to Improve**

👥 **Contributors**

| Name                    | GitHub Handle                                      |
|-------------------------|----------------------------------------------------|
| Kristov Judilla         | [@Kristov-Judilla](https://github.com/Kristov-Judilla) |
| Abigail Ann Sarmiento| [@aasarmiento](https://github.com/aasarmiento)              |



🎯 **How to Use**

1️⃣ **Clone this repository**

```bash
git clone https://github.com/Kristov-Judilla/MO-IT101-Group1.git
```

2️⃣ **Open in NetBeans**

- Open NetBeans and select `File > Open Project`.
- Navigate to the cloned repository directory (`MO-IT101-Group1/MotorPH-Payroll-System`) and open the project.
- Ensure the `opencsv.jar` library is added to the project’s libraries (if not already included).

3️⃣ **Run Payroll Hub**

- Ensure `Employee.csv` is in the `data` directory with the required employee data.
- Right-click `MotorPH.java` in the `Projects` tab and select `Run File`.
- Follow the prompts to enter the employee ID, pay period, hours worked, overtime, tardiness, days late, and holiday work details.
- View the payroll statement in the console and check `MotorPHPayslip.csv` for saved data.
- **Note**: Ensure `MotorPHPayslip.csv` is not open in another application (e.g., Excel) while running the program, as this may cause a "file in use" error when saving the payroll data.
