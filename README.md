# Hospital Appointment Management System

A web application for managing hospital appointments, built using **Spring Boot (REST APIs)**, **React**, and **MySQL**.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Dependencies](#dependencies)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [Setup Instructions](#setup-instructions)
  - [Clone the Repository](#1-clone-the-repository)
  - [Backend Setup](#2-backend-setup)
  - [Frontend Setup](#3-frontend-setup)
  - [Database Setup](#4-database-setup)
- [Running the Application](#running-the-application)

---

## Prerequisites

Ensure the following software is installed on your system:

1. **Java Development Kit (JDK 17 or higher)**  
   - [Download JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)

2. **Spring Tool Suite (STS)** (optional for backend development)  
   - [Download STS](https://spring.io/tools)

3. **Node.js and npm** (for the frontend)  
   - [Download Node.js](https://nodejs.org/)

4. **MySQL** (for the database)  
   - [Download MySQL](https://dev.mysql.com/downloads/installer/)

5. **Visual Studio Code (VS Code)** (optional for frontend development)  
   - [Download VS Code](https://code.visualstudio.com/)

---

## Dependencies

### Backend

The backend is built using **Spring Boot**. Below are the key dependencies included in `pom.xml`:

- **Spring Boot Starter Data JPA**: For database interactions.
- **Spring Boot Starter Web**: For building REST APIs.
- **MySQL Connector**: For connecting to the MySQL database.
- **Apache POI**: For exporting data to Excel.
- **Jackson Dataformat CSV**: For exporting data to CSV.
- **iText PDF**: For exporting data to PDF.

To view all dependencies, check the `pom.xml` file.

### Frontend

The frontend is built using **React**. Below are the key dependencies from `package.json`:

- **React Router DOM**: For navigation.
- **Bootstrap**: For styling.
- **Material UI Icons**: For iconography.
- **React Icons**: For additional icons.

To view all dependencies, check the `package.json` file.

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/HospitalAppointmentManagement.git
cd HospitalAppointmentManagement
```

---

### 2. Backend Setup

1. Open the `backend` folder in your IDE (e.g., STS or IntelliJ).
2. Ensure you have **JDK 17** installed and set up.
3. Update the `application.properties` file with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Build the backend project:
   ```bash
   mvn clean install
   ```
5. Start the backend server:
   ```bash
   mvn spring-boot:run
   ```

---

### 3. Frontend Setup

#### Using Visual Studio Code:
1. Open the `frontend` folder in **VS Code**.
2. Install the required extensions:
   - **ES7+ React/Redux/React-Native snippets**
   - **Prettier - Code formatter** (optional)
3. Open a terminal in VS Code and navigate to the `frontend` folder:
   ```bash
   cd frontend
   ```
4. Install the required dependencies:
   ```bash
   npm install
   ```
5. Start the development server:
   ```bash
   npm start
   ```

#### Alternate Method:
If you are familiar with other IDEs or tools, continue using your preferred environment to set up and run the frontend application.

---

### 4. Database Setup

1. Open **MySQL Workbench** or any MySQL client.
2. Create a new database:
   ```sql
   CREATE DATABASE hospital_management;
   ```
3. Import the provided SQL file (`database/database_backup.sql`):
   ```bash
   mysql -u your_username -p hospital_management < database/database_backup.sql
   ```
4. Verify the database is correctly set up by checking the tables.

---

## Running the Application

1. **Start the Backend Server**:
   ```bash
   mvn spring-boot:run
   ```
2. **Start the Frontend Development Server**:
   ```bash
   npm start
   ```
3. Open your browser and navigate to:
   ```
   http://localhost:3000
   ```

---

## Notes

- If you are already familiar with setting up and running Java Spring Boot or React projects in your preferred IDE, feel free to proceed with your usual workflow.
- Make sure to verify your database connection and credentials before running the application.

Directory structure:
└── Rajnish-J-Hospify---Training-project/
    ├── Tracking
    ├── hospitalmanagementsystem/
    │   ├── .gitignore
    │   ├── nginx.conf
    │   ├── public/
    │   │   ├── manifest.json
    │   │   ├── index.html
    │   │   └── robots.txt
    │   ├── package.json
    │   ├── Dockerfile
    │   ├── package-lock.json
    │   ├── README.md
    │   └── src/
    │       ├── setupTests.js
    │       ├── findApptsBetTwoDate/
    │       │   └── findApptsBetTwoDate.jsx
    │       ├── fetchPatientDetails/
    │       │   ├── fetchPatientDetails.css
    │       │   └── fetchPatientDetails.jsx
    │       ├── fetchallAppointments/
    │       │   ├── fetchAllAppointments.css
    │       │   └── fetchallAppointments.jsx
    │       ├── index.css
    │       ├── assets/
    │       │   ├── contentpage/
    │       │   ├── fonts/
    │       │   │   └── JosefinSans-VariableFont_wght.ttf
    │       │   └── Landingpage/
    │       ├── LandingPage/
    │       │   ├── landingPage.css
    │       │   └── landingPage.jsx
    │       ├── InsertPatients/
    │       │   ├── insertpatient.jsx
    │       │   └── insertpatient.css
    │       ├── updatePatientDetails/
    │       │   ├── updatePatientDetails.css
    │       │   └── updatePatientDetails.jsx
    │       ├── App.js
    │       ├── reportWebVitals.js
    │       ├── Login/
    │       │   ├── login.css
    │       │   └── login.jsx
    │       ├── UpdateAppointmentDetails/
    │       │   ├── UpdateAppointmentDetails.jsx
    │       │   └── updateAppointmentDetails.css
    │       ├── DeletePatient/
    │       │   ├── DeletePatient.css
    │       │   └── DeletePatient.jsx
    │       ├── DeleteAppointment/
    │       │   ├── DeleteAppointment.css
    │       │   └── DeleteAppointment.jsx
    │       ├── App.css
    │       ├── mainPageComponents/
    │       │   ├── footer/
    │       │   │   ├── footer.jsx
    │       │   │   └── footer.css
    │       │   ├── header/
    │       │   │   ├── header.jsx
    │       │   │   └── header.css
    │       │   ├── main.jsx
    │       │   ├── Admin/
    │       │   │   └── admin.jsx
    │       │   └── centre/
    │       │       ├── centre.jsx
    │       │       └── centre.css
    │       ├── App.test.js
    │       ├── checkAvailablity/
    │       │   └── checkAvailablity.jsx
    │       ├── index.js
    │       ├── AddAppointment/
    │       │   ├── AddAppointment.css
    │       │   └── AddAppointment.jsx
    │       └── findapptsbydate/
    │           └── findapptsbydate.jsx
    ├── videos/
    ├── README.md
    └── HospitalAppointmentScheduling/
        ├── mvnw.cmd
        ├── .gitignore
        ├── mvnw
        ├── pom.xml
        ├── .mvn/
        │   └── wrapper/
        │       └── maven-wrapper.properties
        ├── Dockerfile
        └── src/
            ├── main/
            │   ├── resources/
            │   │   └── application.properties
            │   └── java/
            │       ├── log4j/
            │       │   └── log4j.properities
            │       └── com/
            │           └── HospitalAppointmentScheduling/
            │               ├── Entity/
            │               │   ├── DoctorVO.java
            │               │   ├── StateVO.java
            │               │   ├── HospitalVO.java
            │               │   ├── AppointmentStatusVO.java
            │               │   ├── SpecializationVO.java
            │               │   ├── CityVO.java
            │               │   ├── PatientVO.java
            │               │   ├── AppointmentsVO.java
            │               │   └── CountryVO.java
            │               ├── Clinet/
            │               │   └── PatientClient.java
            │               ├── DTO/
            │               │   ├── AppointmentStatusDTO.java
            │               │   ├── AppointmentPatientDoctorAppointmentStatusDTO.java
            │               │   ├── DoctorDTO.java
            │               │   ├── HospitalDTO.java
            │               │   ├── StateDTO.java
            │               │   ├── SpecializationDTO.java
            │               │   ├── PatientDTO.java
            │               │   ├── CityDTO.java
            │               │   ├── CountryDTO.java
            │               │   ├── AppointmentDTO.java
            │               │   └── PatientDoctorDTO.java
            │               ├── WebConfig/
            │               │   └── WebConfig.java
            │               ├── BO/
            │               │   ├── SpecializationBO.java
            │               │   ├── CountryBO.java
            │               │   ├── HospitalBO.java
            │               │   ├── CityBO.java
            │               │   ├── PatientBO.java
            │               │   ├── AppointmentStatusBO.java
            │               │   ├── StateBO.java
            │               │   ├── DoctorBO.java
            │               │   └── AppointmentsBO.java
            │               ├── DAO/
            │               │   ├── DoctorDetailsProjection.java
            │               │   ├── AppointmentsRepo.java
            │               │   ├── DoctorRepo.java
            │               │   ├── PatientRepo.java
            │               │   ├── PatientProjection.java
            │               │   ├── PatientIdProjection.java
            │               │   ├── PatientPhoneProjection.java
            │               │   └── HospitalRepo.java
            │               ├── HospitalAppointmentSchedulingApplication.java
            │               ├── Response/
            │               │   ├── ResponseHandleAppointments.java
            │               │   ├── ResponseHandleDoctor.java
            │               │   ├── ResponseHandleHospital.java
            │               │   └── ResponseHandle.java
            │               ├── CustomExceptions/
            │               │   ├── IdException.java
            │               │   ├── genderException.java
            │               │   ├── AppointmentException.java
            │               │   ├── DoctorDetailsException.java
            │               │   ├── PasswordException.java
            │               │   ├── EmailException.java
            │               │   ├── ReasonException.java
            │               │   ├── DateOfBirthException.java
            │               │   ├── PatientException.java
            │               │   ├── DateException.java
            │               │   ├── PhoneNumberException.java
            │               │   └── AppointmentBookingDateException.java
            │               ├── Controller/
            │               │   ├── ExportController.java
            │               │   ├── PatientController.java
            │               │   ├── AppointmentController.java
            │               │   ├── HospitalControler.java
            │               │   └── LoginAuthenticationController.java
            │               └── Service/
            │                   ├── CountryService.java
            │                   ├── CityService.java
            │                   ├── AppointmentStatusService.java
            │                   ├── HospitalService.java
            │                   ├── DoctorService.java
            │                   ├── PatientService.java
            │                   ├── ExportService.java
            │                   └── AppointmentsService.java
            └── test/
                └── java/
                    └── com/
                        └── HospitalAppointmentScheduling/
                            └── HospitalAppointmentSchedulingApplicationTests.java

