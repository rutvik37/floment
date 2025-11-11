🎯 Playwright Java Automation Project

📘 Overview

This project automates the complete Sign-up, Login, and To-Do flows for the Incenti Web Application using the Playwright framework with Java.


✅ Key Features

1. Fully automated Sign-up → Email Verification → Login → To-Do workflow

2. Dynamic random data generation for each test run (email, password, names)

3. Email verification using Maildrop.cc

4. Profile image upload functionality automated

5. To-Do module: Create , update and complete task flow

6. Visual debugging support (headless mode OFF)


⚙️ Prerequisites

Make sure the following tools are installed on your system:

| Tool                    | Description                                                    |

| **Java JDK 17+**        | Required for Playwright Java                                   | 
| **Apache Maven**        | For dependency management                                      |                             
| **Internet Connection** | Needed for OTP retrieval from Maildrop and website interaction | 
                    |      


🧩 Project Structure

playwright-java-incenti/
├── pom.xml                  # Maven dependencies
├── src/
│   ├── main/
│   │   └── java/
│   │       └── main.java    # Base setup and data handling
│   │       └── signup.java  # Sign-up and email verification flow
│   │       └── todo.java    # To-Do creation and update flow
│   │       └── login.java   # Login automation flow
├── README.md                # Project documentation


📦 Output

After execution, the console will display:

✅ Generated email and password
✅ Verification status
✅ To-Do creation and update confirmation

👤 Author

Rutvik Jasani
Software Tester – Manual & Automation
