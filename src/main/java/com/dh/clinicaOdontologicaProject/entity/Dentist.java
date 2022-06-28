package com.dh.clinicaOdontologicaProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Dentists")
public class Dentist {

    //attributes
    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;
    private String surname;
    private String name;
    private int license;

    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private List<Patient> patients;

    //constructors
    public Dentist(){}
    public Dentist(String surname, String name, int license) {
        this.surname = surname;
        this.name = name;
        this.license = license;
    }
    public Dentist(Long id, String surname, String name, int license) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.license = license;
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

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Dentist {" + '\n' +
                "   ID: " + id + '\n' +
                "   Surname: " + surname + '\n' +
                "   Name: " + name + '\n' +
                "   License: " + license + '\n' +
                '}';
    }
}
