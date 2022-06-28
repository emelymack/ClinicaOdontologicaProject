package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    List<Dentist> listDentists();
    Dentist saveDentist(Dentist  dentist);
    Optional<Dentist> findById(Long id);
    void deleteDentist(Long id);
    Dentist updateDentist(Dentist dentist);
}
