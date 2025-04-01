# Hi there! <img src="https://github.com/TheDudeThatCode/TheDudeThatCode/blob/master/Assets/Hi.gif" width="35" />

<p align="center">
  <img src="https://github.com/Kristov-Judilla/MO-IT101-Group1/raw/main/assets/banner.png" alt="Payroll Hub Banner" width="100%">
</p>

<h1 align="center">🌟 MO-IT101-Group1 🌟</h1>

<p align="center">
 🚀 Explore the Incredible Creations of MO-IT101 Group 1! 🎉 We are a group of enthusiastic beginners diving into the world of programming. Our goal is to learn, collaborate, and build awesome projects together!
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License">
  <img src="https://img.shields.io/badge/Status-Completed-green?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/github/contributors/Kristov-Judilla/MO-IT101-Group1?style=for-the-badge" alt="Contributors">
</p>

---

## 🗺️ Navigation Guide

- 🌟 [Explore Payroll Hub: MotorPH Payroll System](#explore-payroll-hub-motorph-payroll-system)  
- 📺 [See It in Action: Sample Output](#see-it-in-action-sample-output)  
- 🛠️ [Discover the Features](#discover-the-features)  
- 📁 [Understand File Handling](#understand-file-handling)  
- 🏛️ [Project File Layout](#project-file-layout)  
- 📈 [Current Project Progress](#current-project-progress)  
- 💡 [Ideas for Enhancement](#ideas-for-enhancement)  
- 👥 [Meet the Team](#meet-the-team)  
- 🧪 [Review Test Cases](#test-cases)  
- 🚀 [Get Started: Usage Instructions](#get-started-usage-instructions)  
- 🌐 [Join the Community](#join-the-community)

---

## 🌟 Explore Payroll Hub: MotorPH Payroll System

Payroll Hub is a sleek and efficient Java-based payroll system designed for MotorPH employees. It simplifies payroll processing with the following capabilities:

- ⏱️ Calculates work hours with a 15-minute grace period for tardiness
- 💰 Computes gross pay based on worked hours, overtime, and holiday pay
- 📊 Applies government deductions (SSS, PhilHealth, Pag-IBIG, withholding tax)
- 💸 Determines net pay after deductions
- 📂 Reads employee data from CSV files for processing
- 📜 Saves payroll data to a CSV file for record-keeping

---

## 📺 See It in Action: Sample Output

Here’s what you’ll see when you run Payroll Hub for Employee ID 10001, with a pay period from 2024-06-03 to 2024-06-18, 15 minutes of tardiness (within the grace period), and 8 hours worked on Independence Day (2024-06-12):

<p align="center"> <img src="https://github.com/Kristov-Judilla/MO-IT101-Group1/raw/main/assets/screenshot.gif" alt="Payroll Hub Output Screenshot" width="80%"> </p>

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

---

## 🛠️ Discover the Features

Here’s what makes Payroll Hub awesome:

- ⏱️ **Worked Hours Calculation**: Tracks hours per pay period, including overtime at 1.25x rate   
- 💰 **Gross Pay Computation**: Includes worked hours, overtime, and holiday pay  
- 🎉 **Holiday Support**: Covers all Philippine holidays (regular and special non-working) for 2023-2025  
- 📅 **Flexible Pay Periods**: Supports pay periods spanning across years  
- ⏰ **Tardiness Grace Period**: No deduction for tardiness ≤15 minutes  
- 📊 **Government Deductions**: Applies SSS, PhilHealth, Pag-IBIG, and withholding tax  
- 💸 **Net Pay Calculation**: Determines final pay after deductions   
- 📜 **CSV Output**: Saves payroll data to `MotorPHPayslip.csv`, including days late    
- ✅ **Input Validation**: Ensures valid dates, hours, tardiness, and days late  
- 📋 **Formatted Output**: Aligns columns for readability  

---

## 📁 Understand File Handling

The program handles files as follows:

- **Input**:  
  📌 `Employee.csv` (Employee data including hourly rate and allowances)  

- **Output**:  
  📌 `MotorPHPayslip.csv` (Payroll data in CSV format)  

---

## 🏛️ Project File Layout

```
/MO-IT101-Group1
├── MotorPH-Payroll-System/
│   ├── src/
│   │   └── MotorPH.java
│   ├── data/
│   │   └── Employee.csv
│   ├── assets/
│   │   ├── banner.png
│   │   └── screenshot.png
│   └── MotorPHPayslip.csv
├── README.md
├── .gitignore
└── LICENSE
```
## 🧪 Test Cases

Below are the test cases for the MotorPH Payroll System, covering various scenarios to ensure the application works as expected. These test cases include launching the application, processing payroll with valid and invalid inputs, and saving payroll data to a CSV file. Columns for actual results and pass/fail status will be filled out after testing.

| Test Scenario | Test Case ID | Test Case Description               | Test Data                                                                 | Test Steps                                                                                                                    | Expected Result                                                                               |
|---------------|--------------|-------------------------------------|---------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| Scenario 1    | 1            | Launch the payroll application     | Path to Employee.csv: `C:\Users\Kristov\Documents\COMPROG1\Prog 1 MotorPH\Employee.csv` | 1. User opens NetBeans.<br>2. User opens the MotorPH project.<br>3. User runs `MOTORPH.java`. | User sees the welcome message: "Welcome to MotorPH Payroll System".    
| Scenario 1    | 2            | Process payroll with valid inputs  | Employee ID: 10001<br>Start Date: 2024-06-19<br>End Date: 2024-06-30<br>Total Hours: 80<br>Overtime Hours: 0<br>Tardiness Minutes: 15<br>Days Late: 1 | 1. User enters Employee ID.<br>2. User enters start and end dates.<br>3. User enters total hours, overtime  | User sees the welcome message: "Welcome to MotorPH Payroll System".  hours, tardiness minutes, and days late.<br>4. No holidays in this period. | User sees payroll slip with Net Pay: P40,819.29 (calculated: 80 * 535.71 + allowances - deductions, no holiday pay). |
| Scenario 1    | 3            | Save payroll to CSV                | Output CSV: `C:\Users\Kristov\Documents\COMPROG1\Prog 1 MotorPH\MOTORPHPAYSLIP.csv` | 1. User completes payroll processing for Employee ID 10001.<br>2. Program saves data to CSV.                             
| Scenario 2    | 4            | Launch the payroll application     | Path to Employee.csv: `C:\Users\Kristov\Documents\COMPROG1\Prog 1 MotorPH\Employee.csv` | 1. User opens NetBeans.<br>2. User opens the MotorPH project.<br>3. User runs `MOTORPH.java`. | User sees the welcome message: "Welcome to MotorPH Payroll System".     
| Scenario 2    | 5            | Process payroll with a holiday     | Employee ID: 10001<br>Start Date: 2024-06-03<br>End Date: 2024-06-18<br>Total Hours: 80<br>Overtime Hours: 0<br>Tardiness Minutes: 15<br>Days Late: 1<br>Worked on 2024-06-12: Yes<br>Hours on 2024-06-12: 8 | 1. User enters Employee ID.<br>2. User enters start and | User sees the welcome message: "Welcome to MotorPH Payroll System".   end dates.<br>3. User enters total hours, overtime hours, tardiness minutes, and days late.<br>4. User confirms working 8 hours on Independence Day (2024-06-12). | User sees payroll slip with Net Pay: P47,390.72 (matches the example output with holiday pay). |
| Scenario 2    | 6            | Save payroll to CSV                | Output CSV: `C:\Users\Kristov\Documents\COMPROG1\Prog 1 MotorPH\MOTORPHPAYSLIP.csv` | 1. User completes payroll processing for Employee ID 10001.<br>2. Program saves data to CSV.                             | User sees message: "PAYROLL data saved to [path]".   | User sees the welcome message: "Welcome to MotorPH Payroll System".                                        |
| Scenario 3    | 7            | Launch the payroll application     | Path to Employee.csv: `C:\Users\Kristov\Documents\COMPROG1\Prog 1 MotorPH\Employee.csv` | 1. User opens NetBeans.<br>2. User opens the MotorPH project.<br>3. User runs `MOTORPH.java`. | User sees the welcome message: "Welcome to MotorPH Payroll System".      | User sees the welcome message: "Welcome to MotorPH Payroll System".                    |
| Scenario 3    | 8            | Process payroll with invalid Employee ID | Employee ID: 99999<br>Start Date: 2024-06-03<br>End Date: 2024-06-18 | 1. User enters an invalid Employee ID.<br>2. User enters start and end dates.                                            | User sees error message: "Employee ID not found".    | User sees the welcome message: "Welcome to MotorPH Payroll System".                                        |
| Scenario 3    | 9            | Process payroll with invalid date format | Employee ID: 10001<br>Start Date: 2024/06/03<br>End Date: 2024-06-18 | 1. User enters Employee ID.<br>2. User enters start date in wrong format (YYYY/MM/DD).<br>3. User enters end date.      | User sees error message: "Error: Invalid date format. Please use  | User sees the welcome message: "Welcome to MotorPH Payroll System".  YYYY-MM-DD."             |

---

## 📈 Current Project Progress

🔧 **Completed**  

---

## 💡 Ideas for Enhancement

Hi po Ms Kim, feel free to edit what improvements should we add. 🚀

---

## 👥 Meet the Team

Meet the amazing team behind Payroll Hub:

| Name                    | GitHub Handle                                      | Avatar                                      |
|-------------------------|----------------------------------------------------|---------------------------------------------|
| Kristofer Judilla       | [@Kristov-Judilla](https://github.com/Kristov-Judilla) | <img src="https://github.com/Kristov-Judilla.png" width="50" height="50"> |
| Abigail Ann Sarmiento   | [@aasarmiento](https://github.com/aasarmiento)     | <img src="https://github.com/aasarmiento.png" width="50" height="50"> |

---

## 🚀 Get Started: Usage Instructions

Get started with Payroll Hub in just a few steps:

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

---

## 🌐 Thank you!

We’d love to hear from you! Connect with the MO-IT101 Group 1 team:

<p align="center">
  <a href="https://github.com/Kristov-Judilla"><img src="https://img.shields.io/badge/GitHub-Kristov--Judilla-181717?style=social&logo=github" alt="Kristov Judilla GitHub"></a>
  <!-- Add more team members' GitHub links here -->
</p>

<p align="center">
  Made with ❤️ by MO-IT101 Group 1
</p>
