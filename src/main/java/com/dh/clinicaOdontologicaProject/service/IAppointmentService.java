package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> listAppointments();
    Appointment saveAppointment(Appointment appointment) throws BadRequestException;
    Optional<Appointment> findById(Long id);
    void deleteAppointment(Long id);
    Appointment updateAppointment(Appointment appointment);
}
