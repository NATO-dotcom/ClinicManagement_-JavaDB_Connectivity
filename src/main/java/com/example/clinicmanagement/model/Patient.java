package com.example.clinicmanagement.model;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String diagnosis;
    private String contact;

    public Patient(int id, String name, int age, String diagnosis, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.contact = contact;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDiagnosis() { return diagnosis; }
    public String getContact() { return contact; }
}