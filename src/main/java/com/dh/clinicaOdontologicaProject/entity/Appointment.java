package com.dh.clinicaOdontologicaProject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointments")
public class Appointment {

    //attributes
    @Id
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    private LocalDate date;

    //private LocalTime time; ?

    //constructors
    public Appointment(){}
    public Appointment(Patient patient, Dentist dentist, LocalDate date) {
        this.patient = patient;
        this.dentist = dentist;
        this.date = date;
    }

    //getters & setters
    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static boolean isAppointmentDataCorrect(Appointment appointment){
        boolean respPatient = false;
        boolean respDentist = false;
        if(appointment != null && appointment.date != null){
            if(appointment.patient.getId() != null
            && appointment.patient.getSurname() != null
            && appointment.patient.getName() != null
            && appointment.patient.getEmail() != null
            && appointment.patient.getDni() != null){
                respPatient = true;
            }
            if(appointment.dentist.getId() != null
            && appointment.dentist.getSurname() != null
            && appointment.dentist.getName() != null
            && appointment.dentist.getLicense() != null){
                respDentist = true;
            }
        }
        return respPatient && respDentist;
    }

    @Override
    public String toString() {
        return "Appointment {" + '\n' +
                "   ID: " + id + '\n' +
                "   Patient: " + patient + '\n' +
                "   Dentist: " + dentist + '\n' +
                "   date: " + date + '\n' +
                '}';
    }
}
