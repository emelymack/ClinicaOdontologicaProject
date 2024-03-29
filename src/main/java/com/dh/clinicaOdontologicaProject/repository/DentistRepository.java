package com.dh.clinicaOdontologicaProject.repository;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
