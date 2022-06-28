package com.dh.clinicaOdontologicaProject.repository;

import com.dh.clinicaOdontologicaProject.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
