package com.example.Ayurveda_Management.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "treatmentrecord")
public class TreatmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "treatment_date", nullable = false)
    private LocalDate treatmentDate;

    @Column(name = "treatment_time", nullable = false)
    private LocalTime treatmentTime;

    @Column(name = "sickness_description")
    private String sicknessDescription;

    @Column(name = "medicine_prescribed")
    private String medicinePrescribed;

    @Column(name = "therapy_given")
    private String therapyGiven;

    @Column(name = "progress_notes")
    private String progressNotes;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "treatment_amount", nullable = false)
    private double treatmentAmount;





    // Getters and Setters

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public LocalTime getTreatmentTime() {
        return treatmentTime;
    }

    public String getSicknessDescription() {
        return sicknessDescription;
    }

    public String getMedicinePrescribed() {
        return medicinePrescribed;
    }

    public String getTherapyGiven() {
        return therapyGiven;
    }

    public String getProgressNotes() {
        return progressNotes;
    }

    public Staff getStaff() {
        return staff;
    }

    public double getTreatmentAmount() {
        return treatmentAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public void setTreatmentTime(LocalTime treatmentTime) {
        this.treatmentTime = treatmentTime;
    }

    public void setSicknessDescription(String sicknessDescription) {
        this.sicknessDescription = sicknessDescription;
    }

    public void setMedicinePrescribed(String medicinePrescribed) {
        this.medicinePrescribed = medicinePrescribed;
    }

    public void setTherapyGiven(String therapyGiven) {
        this.therapyGiven = therapyGiven;
    }

    public void setProgressNotes(String progressNotes) {
        this.progressNotes = progressNotes;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setTreatmentAmount(double treatmentAmount) {
        this.treatmentAmount = treatmentAmount;
    }

    public TreatmentRecord() {
    }

//    public TreatmentRecord(int id, Patient patient, LocalDate treatmentDate, LocalTime treatmentTime, String sicknessDescription, String medicinePrescribed, String therapyGiven, String progressNotes, Staff staff, double treatmentAmount) {
//        this.id = id;
//        this.patient = patient;
//        this.treatmentDate = treatmentDate;
//        this.treatmentTime = treatmentTime;
//        this.sicknessDescription = sicknessDescription;
//        this.medicinePrescribed = medicinePrescribed;
//        this.therapyGiven = therapyGiven;
//        this.progressNotes = progressNotes;
//        this.staff = staff;
//        this.treatmentAmount = treatmentAmount;
//    }

    public TreatmentRecord(int id, LocalDate treatmentDate, Patient patient, LocalTime treatmentTime, String sicknessDescription, String medicinePrescribed, String therapyGiven, String progressNotes, Staff staff, double treatmentAmount) {
        this.id = id;
        this.treatmentDate = treatmentDate;
        this.patient = patient;
        this.treatmentTime = treatmentTime;
        this.sicknessDescription = sicknessDescription;
        this.medicinePrescribed = medicinePrescribed;
        this.therapyGiven = therapyGiven;
        this.progressNotes = progressNotes;
        this.staff = staff;
        this.treatmentAmount = treatmentAmount;

    }


    //toString
    @Override
    public String toString() {
        return "TreatmentRecord{" +
                "id=" + id +
                ", patient=" + patient +
                ", treatmentDate=" + treatmentDate +
                ", treatmentTime=" + treatmentTime +
                ", sicknessDescription='" + sicknessDescription + '\'' +
                ", medicinePrescribed='" + medicinePrescribed + '\'' +
                ", therapyGiven='" + therapyGiven + '\'' +
                ", progressNotes='" + progressNotes + '\'' +
                ", staff=" + staff +
                ", treatmentAmount=" + treatmentAmount +
                '}';
    }
}
