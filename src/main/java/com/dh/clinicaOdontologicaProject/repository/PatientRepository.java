package com.dh.clinicaOdontologicaProject.repository;

import com.dh.clinicaOdontologicaProject.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
