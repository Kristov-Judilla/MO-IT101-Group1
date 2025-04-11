# Hi there! <img src="https://github.com/TheDudeThatCode/TheDudeThatCode/blob/master/Assets/Hi.gif" width="35" />

<p align="center">
  <img src="https://github.com/Kristov-Judilla/MO-IT101-Group1/raw/main/assets/banner.png" alt="Payroll Hub Banner" width="100%">
</p>

### <img src="https://github.com/TheDudeThatCode/TheDudeThatCode/blob/master/Assets/Developer.gif" width="45" /> About Us:
<h1 align="center">🌟 MO-IT101-Group1 🌟</h1>

<p align="center">
 🚀 Explore the Incredible Creations of MO-IT101 Group 1! 🎉 We are a group of enthusiastic beginners diving into the world of programming. Our goal is to learn, collaborate, and build awesome projects together!
</p>   

## 👋 Who Are We?
We are a group of beginner programmers excited to learn Java as part of our **Computer Programming 1** journey! Our goal is to build strong fundamentals, work on fun projects, and support each other as we grow into confident developers. 💡



## 🎥 Coding in Action!
![Java Coding GIF](https://media.giphy.com/media/qgQUggAC3Pfv687qPC/giphy.gif)


📩 Contact us: 

Happy Coding! 💻🔥
<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License">
  <img src="https://img.shields.io/badge/Status-Completed-green?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/github/contributors/Kristov-Judilla/MO-IT101-Group1?style=for-the-badge" alt="Contributors">
</p>

---



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

Payroll Hub is a sleek and efficient Java-based payroll system designed for MotorPH employees. It simplifies payroll processing with the following capabilities:


- 📅 Payroll Period Selection: Employees can choose between weekly, bi-weekly, or monthly payroll periods
- ⏱️ Calculates work hours with a 15-minute grace period for tardiness
- 💰 Computes gross pay based on worked hours, overtime, and holiday pay
- 📊 Applies government deductions (SSS, PhilHealth, Pag-IBIG, withholding tax)
- 💸 Determines net pay after deductions
- 📂 Reads employee data from CSV files for processing
- 📜 Saves payroll data to a CSV file for record-keeping

---

## 📺 See It in Action: Sample Output

Here’s what you’ll see when you run Payroll Hub for Employee ID 10001, showcasing the employee’s ability to choose weekly (2024-06-03 to 2024-06-09), bi-weekly (2024-06-03 to 2024-06-16), and monthly (2024-06-03 to 2024-06-30) payroll periods, with the functionality demonstrated in the Payroll Hub output screenshot GIF as evidence:

# MotorPH Payroll System

Welcome to the MotorPH Payroll System, a Java-based application for processing employee payrolls efficiently.

## Payroll Hub Output Screenshot

<p align="center">
  <img src="https://github.com/Kristov-Judilla/MO-IT101-Group1/raw/main/assets/screenshots.gif" alt="Payroll Hub Output Screenshot" width="100%">
</p>

---
```
=========================================
        MOTORPH WEEKLY PAYROLL        
=========================================

POSITION: Chief Executive Officer
EMPLOYEE: Garcia Manuel III
ID: 10001
PERIOD: 2024-06-03 to 2024-06-09
HOURLY RATE: P535.71

DESCRIPTION                             HOURS/AMOUNT   AMOUNT
-----------------------------------------
BASIC PAY                               30.98          P 16,598.08
TARDINESS                               7.77           -P  4,160.68
OVERTIME                                0.00           P      0.00
RICE SUBSIDY                                           P    350.00
PHONE ALLOWANCE                                        P    466.67
CLOTHING ALLOWANCE                                     P    233.33
-----------------------------------------
GROSS PAY                                              P 13,487.40

-----------------------------------------
SSS DEDUCTION                                          -P    262.50
PhilHealth                                             -P    202.31
PAG-IBIG                                               -P     23.33
WITHHOLDING TAX                                        -P  1,888.72
-----------------------------------------
TOTAL DEDUCTIONS                                       -P  2,376.87
=========================================
NET PAY: P11,110.53
=========================================

=========================================
        MOTORPH BI-WEEKLY PAYROLL        
=========================================

POSITION: Chief Executive Officer
EMPLOYEE: Garcia Manuel III
ID: 10001
PERIOD: 2024-06-03 to 2024-06-16
HOURLY RATE: P535.71

DESCRIPTION                             HOURS/AMOUNT   AMOUNT
-----------------------------------------
BASIC PAY                               64.83          P 34,731.87
TARDINESS                               12.67          -P  6,785.66
OVERTIME                                0.00           P      0.00
RICE SUBSIDY                                           P    700.00
PHONE ALLOWANCE                                        P    933.33
CLOTHING ALLOWANCE                                     P    466.67
-----------------------------------------
GROSS PAY                                              P 30,046.21

-----------------------------------------
SSS DEDUCTION                                          -P    525.00
PhilHealth                                             -P    450.69
PAG-IBIG                                               -P     46.67
WITHHOLDING TAX                                        -P  4,533.78
-----------------------------------------
TOTAL DEDUCTIONS                                       -P  5,556.14
=========================================
NET PAY: P24,490.07
=========================================

Payslip successfully exported to C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\MOTORPHPAYSLIP.csv
Thank you for using MotorPH Payroll System!
Would you like to process another employee? (yes/no): yes

=========================================
        MOTORPH MONTHLY PAYROLL        
=========================================

POSITION: Chief Executive Officer
EMPLOYEE: Garcia Manuel III
ID: 10001
PERIOD: 2024-06-03 to 2024-06-30
HOURLY RATE: P535.71

DESCRIPTION                             HOURS/AMOUNT   AMOUNT
-----------------------------------------
BASIC PAY                               131.65         P 70,526.22
TARDINESS                               20.90          -P 11,196.34
OVERTIME                                0.00           P      0.00
RICE SUBSIDY                                           P  1,400.00
PHONE ALLOWANCE                                        P  1,866.67
CLOTHING ALLOWANCE                                     P    933.33
-----------------------------------------
GROSS PAY                                              P 63,529.88

-----------------------------------------
SSS DEDUCTION                                          -P  1,050.00
PhilHealth                                             -P    952.95
PAG-IBIG                                               -P     93.33
WITHHOLDING TAX                                        -P  9,914.03
-----------------------------------------
TOTAL DEDUCTIONS                                       -P 12,010.32
=========================================
NET PAY: P51,519.57
=========================================

Payslip successfully exported to C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\MOTORPHPAYSLIP.csv
Thank you for using MotorPH Payroll System!
Would you like to process another employee? (yes/no): 
```

---

## 🛠️ Discover the Features

Here’s what makes Payroll Hub awesome:

- 📅 Payroll Period Selection: Employees can choose between weekly, bi-weekly, or monthly payroll periods
- ⏱️ Worked Hours Calculation: Tracks hours per pay period, including overtime at 1.25x rate
- 💰 Gross Pay Computation: Includes worked hours, overtime, and allowances
- 📅 Flexible Pay Periods: Supports pay periods spanning across years
- ⏰ Tardiness Grace Period: No deduction for tardiness ≤15 minutes
- 📊 Government Deductions: Applies SSS, PhilHealth, Pag-IBIG, and withholding tax
- 💸 Net Pay Calculation: Determines final pay after deductions
- 📜 CSV Output: Saves payroll data to MotorPHPayslip.csv, including days late
- ✅ Input Validation: Ensures valid payroll type and period selection
- 📋 Formatted Output: Aligns columns for readability
- 🔍 Clean Console Output: Removes unnecessary log messages from the console, providing a user-friendly interface
- 📝 Logging System: Logs all operations, including errors, to payroll.log for debugging while keeping the console clean

---

## 📁 Understand File Handling

The program handles files as follows:

📁 Understand File Handling
The program handles files as follows:

Input:
- 📌 Employee.csv (Employee data including hourly rate and allowances)
- 📌 Attendance.csv (Employee login/logout records for hours calculation)
- 📌 weeklyhours.csv (Weekly hours data for determining available pay periods)
Output:
- 📌 MotorPHPayslip.csv (Payroll data in CSV format)
- 📌 payroll.log (Log file for debugging and operation tracking) 

---

## 🏛️ Project File Layout

```
/MO-IT101-Group1
├── MotorPH-Payroll-System/
│   ├── src/
│   │   ├── MotorPH.java
│   │   ├── Employee.csv
│   │   ├── Attendance.csv
│   │   ├── weeklyhours.csv
│   │   ├── payroll.log
│   │   └── MotorPHPayslip.csv
│   ├── assets/
│   │   ├── banner.png
│   │   └── screenshot.png
├── README.md
├── .gitignore
└── LICENSE
```

## 🧪 Test Cases

Below are the test cases for the MotorPH Payroll System, covering various scenarios to ensure the application works as expected. These test cases include launching the application, processing payroll with valid and invalid inputs, saving payroll data to a CSV file, and verifying the absence of log messages in the console.

# MotorPH Payroll System Test Cases

| Scenario   | Test Case ID | Test Case Description               | Test Data                                                                 | Test Steps                                                                 | Expected Result                                                                                   | Actual Result                                                                                   | Test Result |
|------------|--------------|-------------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|-------------|
| Scenario 1 | 1            | Launch the payroll application      | Path: `C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\`         | 1. Open NetBeans.<br>2. Open MotorPH project.<br>3. Run `MOTORPH.java`.  | User sees: "Welcome to MotorPH Payroll System".                                                  | User sees: "Welcome to MotorPH Payroll System".                                                  | PASS        |
| Scenario 1 | 2            | Process payroll with valid inputs   | Employee ID: 10001<br>Period: 2024-06-03 to 2024-06-16                  | 1. Select payroll type (bi-weekly).<br>2. Enter Employee ID.<br>3. Select period. | User sees payroll output:<br>NET PAY: P24,490.07                                                 | User sees payroll output:<br>NET PAY: P24,490.07                                                 | PASS        |
| Scenario 1 | 3            | Save payroll to CSV                 | Output: `C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\MOTORPHPAYSLIP.csv` | 1. Complete payroll for ID 10001.<br>2. Save to CSV.                     | User sees: "Payslip successfully exported to [path]".                                            | User sees: "Payslip successfully exported to [path]".                                            | PASS        |
| Scenario 2 | 4            | Launch the payroll application      | Path: `C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\`         | 1. Open NetBeans.<br>2. Open MotorPH project.<br>3. Run `MOTORPH.java`.  | User sees: "Welcome to MotorPH Payroll System".                                                  | User sees: "Welcome to MotorPH Payroll System".                                                  | PASS        |
| Scenario 2 | 5            | Save payroll to CSV                 | Output: `C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\MOTORPHPAYSLIP.csv` | 1. Complete payroll for ID 10001.<br>2. Save to CSV.                     | User sees: "Payslip successfully exported to [path]".                                            | User sees: "Payslip successfully exported to [path]".                                            | PASS        |
| Scenario 3 | 6            | Launch the payroll application      | Path: `C:\Users\Kristov\Documents\COMPROG1\JavaApplication3\src\`         | 1. Open NetBeans.<br>2. Open MotorPH project.<br>3. Run `MOTORPH.java`.  | User sees: "Welcome to MotorPH Payroll System".                                                  | User sees: "Welcome to MotorPH Payroll System".                                                  | PASS        |
| Scenario 3 | 7            | Process payroll with invalid Employee ID | Employee ID: 99999<br>Period: 2024-06-03 to 2024-06-16              | 1. Select payroll type.<br>2. Enter invalid Employee ID.<br>3. Select period. | User sees: "Employee ID not found".                                                              | User sees: "Employee ID not found".                                                              | PASS        |
| Scenario 3 | 8            | Process payroll with invalid period selection | Employee ID: 10001<br>Period: 2024-06-03 to 2024-06-16          | 1. Select payroll type.<br>2. Enter Employee ID.<br>3. Enter invalid period number (e.g., 999). | User sees: "Invalid selection. Please enter a number between 1 and [number of periods]."         | User sees: "Invalid selection. Please enter a number between 1 and [number of periods]."         | PASS        |
| Scenario 4 | 9            | Verify no log messages in console   | Employee ID: 10001<br>Period: 2024-06-03 to 2024-06-16                  | 1. Select payroll type (bi-weekly).<br>2. Enter Employee ID.<br>3. Select period.<br>4. Process payroll. | Console output contains no log messages (e.g., "INFO: Total regular hours..."). Only payroll output and user prompts are shown. | Console output contains no log messages. Only payroll output and user prompts are shown.          | PASS        |



---

## 📈 Current Project Progress

🔧 **Completed**  

---

## 💡 Ideas for Enhancement

Hi po Ms Kim, feel free to edit what improvements should we add. 🚀

---

## 👥 Meet the Team

Meet the amazing team behind Payroll Hub:

# Contributors

This project acknowledges the contributions of the following individuals. Below is a list of contributors with their GitHub handles, avatars, and roles.

| Name                  | GitHub Handle       | Avatar            | Role              |
|-----------------------|---------------------|-------------------|-------------------|
| Kristofer Judilla     | [@Kristov-Judilla](https://github.com/Kristov-Judilla)   |     <img src="https://github.com/Kristov-Judilla.png" width="50" height="50">               |      Developer             |
| Abigail Ann Sarmiento |  [@aasarmiento](https://github.com/aasarmiento)        |     <img src="https://github.com/aasarmiento.png" width="50" height="50">               |                QA Analyst   |
| Justine Ciar |  [@aasarmiento](https://github.com/justineciar)        |     <img src="https://github.com/justineciar.png" width="50" height="50">               |                Technical Lead/Architect |


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

### <img align ='center' src='https://media2.giphy.com/media/UQDSBzfyiBKvgFcSTw/giphy.gif?cid=ecf05e47p3cd513axbek3f56ti3jzizq8hincw20jauyyfyw&rid=giphy.gif' width ='29' /> Here's some humor for you:
<img src="https://readme-jokes.vercel.app/api" alt="Error fetching resource, Refresh again to view Jokes Card" width = '11000' />
---


We’d love to hear from you! Connect with the MO-IT101 Group 1 team:

<p align="center">
  <a href="https://github.com/Kristov-Judilla"><img src="https://img.shields.io/badge/GitHub-Kristov--Judilla-181717?style=social&logo=github" alt="Kristov Judilla GitHub"></a>
  <!-- Add more team members' GitHub links here -->
</p>

<p align="center">
  Made with ❤️ by MO-IT101 Group 1
</p>
