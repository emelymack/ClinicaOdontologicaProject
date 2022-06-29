package com.dh.clinicaOdontologicaProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Integer license;

    //como ODONTÓLOGO, vamos a tener más de un turno
    //Paciente y Odontólogo se relacionan mediante la tabla de Turnos
    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

    //constructors
    public Dentist(){}
    public Dentist(String surname, String name, Integer license) {
        this.surname = surname;
        this.name = name;
        this.license = license;
    }
    public Dentist(Long id, String surname, String name, Integer license) {
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

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
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
