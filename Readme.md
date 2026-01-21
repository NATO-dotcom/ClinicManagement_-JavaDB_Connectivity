# Clinic Management System

## Project Overview

**Clinic Management System** is a desktop application built with JavaFX that enables clinic administrators to efficiently manage patient records. The application provides a user-friendly graphical interface for performing CRUD (Create, Read, Update, Delete) operations on patient data, including managing patient information such as name, age, diagnosis, and contact details.

This is a modern, modular application that follows the **MVC (Model-View-Controller)** design pattern and uses **SQLite** for persistent data storage.

---

## Technologies & Dependencies

### Core Technologies
- **Java 25** - Programming language
- **JavaFX 21.0.6** - GUI framework for building the desktop interface
- **SQLite 3.42.0.0** - Lightweight relational database for patient data persistence
- **Maven** - Build and dependency management tool

### Additional Libraries
- **ControlsFX 11.2.1** - Enhanced JavaFX controls
- **FormsFX 11.6.0** - Form building framework for JavaFX
- **BootstrapFX 0.4.0** - Bootstrap styling for JavaFX applications
- **JUnit 5.12.1** - Testing framework

---

## Project Structure

```
ClinicManagement/
│
├── mvnw                          # Maven wrapper script (Linux/Mac)
├── mvnw.cmd                      # Maven wrapper script (Windows)
├── pom.xml                       # Maven configuration file with dependencies
├── Readme.md                     # This file
│
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── module-info.java  # Java module configuration
    │   │   └── com/example/clinicmanagement/
    │   │       ├── Main.java                    # Application entry point
    │   │       │
    │   │       ├── controller/
    │   │       │   └── ClinicController.java    # UI event handler and business logic
    │   │       │
    │   │       ├── dao/
    │   │       │   └── PatientDAO.java          # Data Access Object for database operations
    │   │       │
    │   │       ├── model/
    │   │       │   └── Patient.java             # Patient data model
    │   │       │
    │   │       └── util/
    │   │           └── DBConnection.java        # Database connection utility
    │   │
    │   └── resources/
    │       └── com/example/clinicmanagement/
    │           └── hello-view.fxml              # JavaFX UI layout file
    │
    └── target/                   # Compiled classes and build artifacts (auto-generated)
        ├── classes/
        └── generated-sources/
```

---

## Module Description

### 1. **Main.java** - Application Entry Point
**Location:** `src/main/java/com/example/clinicmanagement/Main.java`

- Extends JavaFX's `Application` class
- Initializes the database on application startup via `DBConnection.initializeDB()`
- Loads the FXML UI layout from `hello-view.fxml`
- Sets up the primary stage with window title "Clinic Management System" (800x500 pixels)
- Contains the `main()` method to launch the application

---

### 2. **Patient.java** - Data Model
**Location:** `src/main/java/com/example/clinicmanagement/model/Patient.java`

- Represents a patient entity with the following attributes:
  - `id` - Unique patient identifier (auto-incremented in database)
  - `name` - Patient's full name
  - `age` - Patient's age
  - `diagnosis` - Medical diagnosis/condition
  - `contact` - Contact information (phone/email)
- Contains constructor and getter methods for all fields
- Used by the TableView to display patient records

---

### 3. **DBConnection.java** - Database Utility
**Location:** `src/main/java/com/example/clinicmanagement/util/DBConnection.java`

- Manages SQLite database connections
- Uses JDBC driver to connect to `clinic.db` SQLite database
- **Key Methods:**
  - `getConnection()` - Returns a database connection
  - `initializeDB()` - Creates the `patients` table if it doesn't exist
- **Database Schema:**
  ```sql
  CREATE TABLE IF NOT EXISTS patients (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      age INTEGER,
      diagnosis TEXT,
      contact TEXT
  )
  ```

---

### 4. **PatientDAO.java** - Data Access Object
**Location:** `src/main/java/com/example/clinicmanagement/dao/PatientDAO.java`

Implements CRUD operations for patient data:

- **`addPatient(String name, int age, String diagnosis, String contact)`**
  - Inserts a new patient record into the database
  
- **`getAllPatients()`**
  - Retrieves all patient records from the database
  - Returns an `ObservableList<Patient>` for JavaFX TableView binding
  
- **`updatePatient(int id, String name, int age, String diagnosis, String contact)`**
  - Updates an existing patient record by ID
  
- **`deletePatient(int id)`**
  - Deletes a patient record by ID from the database

---

### 5. **ClinicController.java** - View Controller
**Location:** `src/main/java/com/example/clinicmanagement/controller/ClinicController.java`

Handles all UI interactions and bridges the view with the model:

**UI Components:**
- `txtName`, `txtAge`, `txtDiagnosis`, `txtContact` - TextField inputs
- `tablePatients` - TableView displaying all patients
- `colId`, `colName`, `colAge`, `colDiagnosis`, `colContact` - Table columns

**Key Methods:**
- **`initialize()`** - Called automatically after FXML loads
  - Configures table columns with data binding
  - Sets up row selection listener to populate input fields
  - Loads patient data into the table
  
- **`handleAdd()`** - Adds a new patient record
  - Validates and retrieves data from input fields
  - Calls `PatientDAO.addPatient()`
  - Reloads table and clears fields
  
- **`handleUpdate()`** - Updates selected patient record
  - Gets selected patient from table
  - Updates database with new values
  - Refreshes the table display
  
- **`handleDelete()`** - Deletes selected patient record
  - Removes selected patient from database
  - Updates table view
  - Clears input fields
  
- **`loadTable()`** - Refreshes table with latest data from database

---

### 6. **hello-view.fxml** - User Interface Layout
**Location:** `src/main/resources/com/example/clinicmanagement/hello-view.fxml`

JavaFX FXML layout file defining the user interface:

**Layout Components:**
- **Title:** "Clinic Patient Management" (bold, 20px font)
- **Input Section:** GridPane with 4 input fields
  - Name (TextField)
  - Age (TextField)
  - Diagnosis (TextField)
  - Contact (TextField)
- **Button Section:** 3 action buttons
  - "Add Patient" - Adds new patient
  - "Update Selected" - Updates selected patient
  - "Delete Selected" - Deletes selected patient
- **Table Section:** TableView with 5 columns
  - ID (50px width)
  - Name (150px width)
  - Age (50px width)
  - Diagnosis (200px width)
  - Contact (100px width)

**Window Size:** 800x500 pixels with 20px padding

---

## How to Build & Run

### Prerequisites
- Java 25 or higher installed
- Maven 3.6+ installed

### Build the Project
```bash
mvn clean package
```

### Run the Application
```bash
mvn javafx:run
```

Or compile and run directly:
```bash
mvn clean compile
mvn javafx:run
```

---

## Features

✅ **Add Patient** - Create new patient records with name, age, diagnosis, and contact information  
✅ **View Patients** - Display all patients in a sortable table  
✅ **Update Patient** - Modify existing patient information by selecting a row  
✅ **Delete Patient** - Remove patient records from the system  
✅ **Persistent Storage** - All data is stored in SQLite database  
✅ **User-Friendly GUI** - Intuitive JavaFX interface  
✅ **Real-time Updates** - Table updates immediately after operations  

---

## Architecture

The application follows the **MVC (Model-View-Controller)** design pattern:

- **Model Layer** (`model/`) - Contains `Patient` data model
- **View Layer** (`resources/hello-view.fxml`) - JavaFX FXML UI definition
- **Controller Layer** (`controller/ClinicController.java`) - Handles user interactions
- **Data Access Layer** (`dao/PatientDAO.java`) - Manages database operations
- **Utility Layer** (`util/DBConnection.java`) - Provides database connectivity

---

## File Descriptions

| File | Purpose |
|------|---------|
| `Main.java` | Application launcher and initialization |
| `Patient.java` | Patient entity model |
| `DBConnection.java` | Database connection and initialization |
| `PatientDAO.java` | Patient CRUD operations |
| `ClinicController.java` | UI event handling and logic |
| `hello-view.fxml` | UI layout definition |
| `pom.xml` | Maven dependencies and build configuration |
| `module-info.java` | Java module system configuration |

---

## Database

**Database File:** `clinic.db` (created in project root on first run)

**Table: patients**
| Column | Type | Notes |
|--------|------|-------|
| id | INTEGER | Primary Key, Auto-increment |
| name | TEXT | Required field |
| age | INTEGER | Patient age |
| diagnosis | TEXT | Medical diagnosis |
| contact | TEXT | Phone/Email contact |

---

## Future Enhancements

- 📋 Add appointment scheduling
- 💊 Track medication/prescriptions
- 📊 Generate patient reports
- 🔐 User authentication and role management
- 📱 Mobile app sync capability
- 🔍 Advanced patient search and filtering
- 💾 Database backup and export functionality

---

## License

This project is open-source and available for educational and personal use.

---

## Author

**Created:** January 2026  
**Project:** Clinic Management System (JavaFX + SQLite)
