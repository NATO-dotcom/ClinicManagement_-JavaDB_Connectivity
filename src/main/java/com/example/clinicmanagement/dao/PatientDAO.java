package com.example.clinicmanagement.dao;

import com.example.clinicmanagement.model.Patient;
import com.example.clinicmanagement.util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class PatientDAO {

    public static void addPatient(String name, int age, String diagnosis, String contact) {
        String sql = "INSERT INTO patients (name, age, diagnosis, contact) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, diagnosis);
            pstmt.setString(4, contact);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static ObservableList<Patient> getAllPatients() {
        ObservableList<Patient> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("diagnosis"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static void updatePatient(int id, String name, int age, String diagnosis, String contact) {
        String sql = "UPDATE patients SET name = ?, age = ?, diagnosis = ?, contact = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, diagnosis);
            pstmt.setString(4, contact);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
