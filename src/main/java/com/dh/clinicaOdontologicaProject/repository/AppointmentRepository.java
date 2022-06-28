package com.dh.clinicaOdontologicaProject.repository;

import com.dh.clinicaOdontologicaProject.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
