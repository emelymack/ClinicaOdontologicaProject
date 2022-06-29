package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> listAppointments();
    Appointment saveAppointment(Appointment appointment);
    Optional<Appointment> findById(Long id);
    void deleteAppointment(Long id);
    Appointment updateAppointment(Appointment appointment);
}
