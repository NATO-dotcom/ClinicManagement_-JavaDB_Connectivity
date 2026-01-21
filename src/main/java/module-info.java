module com.example.clinicmanagement {
    // 1. Core JavaFX Modules
    requires javafx.controls;
    requires javafx.fxml;

    // 2. Database Modules (Critical for SQLite)
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    // 3. Third-party UI Libraries
    // (Keep these if your project template included them, otherwise you can remove them)
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    // 4. MAIN ACCESS
    // Allows JavaFX to launch the application
    opens com.example.clinicmanagement to javafx.fxml;
    exports com.example.clinicmanagement;

    // 5. CONTROLLER ACCESS (Critical)
    // Allows FXML to inject @FXML fields (buttons, text fields) into your Controller
    opens com.example.clinicmanagement.controller to javafx.fxml;

    // 6. MODEL ACCESS (Critical)
    // Allows TableView to read private fields in Patient via reflection (PropertyValueFactory)
    opens com.example.clinicmanagement.model to javafx.base;
}