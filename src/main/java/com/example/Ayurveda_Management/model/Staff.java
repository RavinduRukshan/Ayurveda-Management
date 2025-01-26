package com.example.Ayurveda_Management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private double salary;

    @Column(name = "hire_date")
    private String hireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "dispensary_id")
    private Dispensary dispensary;



    // Controller
    public Staff() {

    }

    public Staff(String name, Role role, String contactNumber, String email, String address, double salary,
                 String hireDate, Status status, Dispensary dispensary) {
        this.name = name;
        this.role = role;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.hireDate = hireDate;
        this.status = status;
        this.dispensary = dispensary;
    }

    // Getters & Setters
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Dispensary getDispensary() {
        return dispensary;
    }

    public void setDispensary(Dispensary dispensary) {
        this.dispensary = dispensary;
    }


    // toString
    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                ", status=" + status +
                ", dispensary=" + dispensary +
                '}';
    }
}
