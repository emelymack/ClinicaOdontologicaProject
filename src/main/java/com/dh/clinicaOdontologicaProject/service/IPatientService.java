package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> listarPacientes();
    Patient guardarPaciente(Patient patient);
    Optional<Patient> buscarXId(Long id);
    void eliminarPaciente(Long id);
    Patient actualizarPaciente(Patient patient);
}









