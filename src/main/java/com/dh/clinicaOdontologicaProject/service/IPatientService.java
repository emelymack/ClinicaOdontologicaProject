package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> listPatients();
    Patient savePatient(Patient patient);
    Optional<Patient> findById(Long id);
    void deletePatient(Long id);
    Patient updatePatient(Patient patient);
}









