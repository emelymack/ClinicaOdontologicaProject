package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> listarPacientes() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> buscarXId(Long id) { return patientRepository.findById(id); }

    @Override
    public void eliminarPaciente(Long id) { patientRepository.deleteById(id); }

    @Override
    public Patient guardarPaciente(Patient patient) { return patientRepository.save(patient); }

    @Override
    public Patient actualizarPaciente(Patient patient) { return patientRepository.save(patient); }
}
