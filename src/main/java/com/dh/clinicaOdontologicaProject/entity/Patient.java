package com.dh.clinicaOdontologicaProject.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Patients")
public class Patient {

    //attributes
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
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

    @OneToOne(cascade = CascadeType.ALL) //cuando agg el paciente, se agg el domicilio
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

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

    public Address getDomicilio() {
        return address;
    }

    public void setDomicilio(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
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