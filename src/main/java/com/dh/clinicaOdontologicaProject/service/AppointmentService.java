package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> listAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) throws BadRequestException{
        if (Appointment.isAppointmentDataCorrect(appointment)) {
            return appointmentRepository.save(appointment);
        } else {
            throw new BadRequestException("Patient or dentist data are non-existent.");
        }
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
