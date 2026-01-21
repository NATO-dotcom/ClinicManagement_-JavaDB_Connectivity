package com.example.clinicmanagement.controller;

import com.example.clinicmanagement.dao.PatientDAO;
import com.example.clinicmanagement.model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClinicController {

    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtDiagnosis;
    @FXML private TextField txtContact;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Integer> colId;
    @FXML private TableColumn<Patient, String> colName;
    @FXML private TableColumn<Patient, Integer> colAge;
    @FXML private TableColumn<Patient, String> colDiagnosis;
    @FXML private TableColumn<Patient, String> colContact;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadTable();

        // Listener to select row for editing
        tablePatients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtName.setText(newSelection.getName());
                txtAge.setText(String.valueOf(newSelection.getAge()));
                txtDiagnosis.setText(newSelection.getDiagnosis());
                txtContact.setText(newSelection.getContact());
            }
        });
    }

    private void loadTable() {
        tablePatients.setItems(PatientDAO.getAllPatients());
    }

    @FXML
    private void handleAdd() {
        PatientDAO.addPatient(txtName.getText(), Integer.parseInt(txtAge.getText()), txtDiagnosis.getText(), txtContact.getText());
        loadTable();
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        Patient selected = tablePatients.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PatientDAO.updatePatient(selected.getId(), txtName.getText(), Integer.parseInt(txtAge.getText()), txtDiagnosis.getText(), txtContact.getText());
            loadTable();
            clearFields();
        }
    }

    @FXML
    private void handleDelete() {
        Patient selected = tablePatients.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PatientDAO.deletePatient(selected.getId());
            loadTable();
            clearFields();
        }
    }

    private void clearFields() {
        txtName.clear(); txtAge.clear(); txtDiagnosis.clear(); txtContact.clear();
    }
}
