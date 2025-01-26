package com.example.Ayurveda_Management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Dispensary")
public class Dispensary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "opening_hours")
    private String openingHours;


    // Constructors
    public Dispensary() {

    }

    public Dispensary(String name, String location, String contactNumber, String openingHours) {
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.openingHours = openingHours;
    }


    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }


    // toString
    @Override
    public String toString() {
        return "Dispensary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", openingHours='" + openingHours + '\'' +
                '}';
    }

}
