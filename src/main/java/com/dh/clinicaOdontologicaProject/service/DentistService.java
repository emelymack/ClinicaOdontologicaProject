package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class DentistService implements IDentistService{

    @Autowired
    private DentistRepository dentistRepository;

    @Override
    public List<Dentist> listDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Optional<Dentist> findById(Long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public void deleteDentist(Long id) {
        dentistRepository.deleteById(id);
    }

    @Override
    public Dentist updateDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }
}
