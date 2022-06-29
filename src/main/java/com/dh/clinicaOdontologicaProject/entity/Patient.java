package com.dh.clinicaOdontologicaProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Patients")
public class Patient {

    //attributes
    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;

    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private int dni;
    @Column
    private LocalDate entryDate;

    //como PACIENTE, puede que tengamos más de un turno
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL) //cuando agg el paciente, se agg el domicilio
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;


    //constructors
    public Patient(){}
    public Patient(String surname, String name, String email, int dni, LocalDate entryDate, Address address) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.dni = dni;
        this.entryDate = entryDate;
        this.address = address;
    }
    public Patient(Long id, String surname, String name, String email, int dni, LocalDate entryDate, Address address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.dni = dni;
        this.entryDate = entryDate;
        this.address = address;
    }

    //getters & setters
    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient {" + '\n' +
                "   ID: " + id + '\n' +
                "   Surname: " + surname + '\n' +
                "   Name: " + name + '\n' +
                "   Email: " + email + '\n' +
                "   DNI: " + dni + '\n' +
                "   Date of entry: " + entryDate + '\n' +
                "   Address: " + address + '\n' +
                '}';
    }
}
