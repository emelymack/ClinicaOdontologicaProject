package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) { return patientRepository.findById(id); }

    @Override
    public void deletePatient(Long id) { patientRepository.deleteById(id); }

    @Override
    public Patient savePatient(Patient patient) throws BadRequestException {
        if(patient.getEntryDate().isBefore(LocalDate.now())){
            return patientRepository.save(patient);
        }else {
            throw new BadRequestException("Patient data is incorrect.");
        }
    }

    @Override
    public Patient updatePatient(Patient patient) { return patientRepository.save(patient); }
}
