<p align="center">
  <img src="https://github.com/Kristov-Judilla/MO-IT101-Group1/raw/main/assets/banner.png" alt="Payroll Hub Banner" width="100%">
</p>

<h1 align="center">🌟 MO-IT101-Group1 🌟</h1>

<p align="center">
  A collection of awesome projects by the MO-IT101 Group 1 team! 🚀
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License">
  <img src="https://img.shields.io/badge/Status-Completed-green?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/github/contributors/Kristov-Judilla/MO-IT101-Group1?style=for-the-badge" alt="Contributors">
</p>

---

## 📋 Table of Contents

- [Payroll Hub (MotorPH Payroll System)](#payroll-hub-motorph-payroll-system)
- [Expected Output](#expected-output)
- [Features](#features)
- [File Handling](#file-handling)
- [File Structure](#file-structure)
- [Project Status](#project-status)
- [Things to Improve](#things-to-improve)
- [Contributors](#contributors)
- [How to Use](#how-to-use)
- [Connect with Us](#connect-with-us)

---

## 💼 Payroll Hub (MotorPH Payroll System)

Payroll Hub is a sleek and efficient Java-based payroll system designed for MotorPH employees. It simplifies payroll processing with the following capabilities:

- ⏱️ Calculates work hours with a 15-minute grace period for tardiness
- 💰 Computes gross pay based on worked hours, overtime, and holiday pay
- 📊 Applies government deductions (SSS, PhilHealth, Pag-IBIG, withholding tax)
- 💸 Determines net pay after deductions
- 📂 Reads employee data from CSV files for processing
- 📜 Saves payroll data to a CSV file for record-keeping

---

## 📸 Expected Output

Here’s what you’ll see when you run Payroll Hub for Employee ID 10001, with a pay period from 2024-06-03 to 2024-06-18, 15 minutes of tardiness (within the grace period), and 8 hours worked on Independence Day (2024-06-12):

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

## ⚙️ Features

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

## 📂 File Handling

The program handles files as follows:

- **Input**:  
  📌 `Employee.csv` (Employee data including hourly rate and allowances)  

- **Output**:  
  📌 `MotorPHPayslip.csv` (Payroll data in CSV format)  

---

## 🏗️ File Structure

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

---

## 🚧 Project Status

🔧 **Completed**  

## 🔍 Things to Improve

Hi po Ms Kim, feel free to edit what improvements should we add. 🚀

---

## 👥 Contributors

Meet the amazing team behind Payroll Hub:

| Name                    | GitHub Handle                                      | Avatar                                      |
|-------------------------|----------------------------------------------------|---------------------------------------------|
| Kristofer Judilla         | [@Kristov-Judilla](https://github.com/Kristov-Judilla) | <img src="https://github.com/Kristov-Judilla.png" width="50" height="50"> |
| Abigail Ann Sarmiento| [@aasarmiento]((https://github.com/aasarmiento/))               | <img src="https://github.com/handle.png" width="50" height="50"> |

---

## 🎯 How to Use

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

## 🌐 Connect with Us

We’d love to hear from you! Connect with the MO-IT101 Group 1 team:

<p align="center">
  <a href="https://github.com/Kristov-Judilla"><img src="https://img.shields.io/badge/GitHub-Kristov--Judilla-181717?style=social&logo=github" alt="Kristov Judilla GitHub"></a>
  <!-- Add more team members' GitHub links here -->
</p>

<p align="center">
  Made with ❤️ by MO-IT101 Group 1
</p>
