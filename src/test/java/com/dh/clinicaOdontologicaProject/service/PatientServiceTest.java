package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Address;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PatientServiceTest {

    @Autowired
    IPatientService patientService;

    @Test
    @Order(1)
    public void addPatientTest() throws BadRequestException {
        Patient patientTest = new Patient("Martínez", "Diego", "diego@test.com", 184569, LocalDate.of(2022,02,22), new Address("Pellegrini",111,"Rosario","Santa Fe"));
        patientService.savePatient(patientTest);
        //search for patientTest
        Optional<Patient> patientTestFound = patientService.findById(1L);
        assertTrue(patientTestFound.isPresent());
    }

    @Test
    @Order(2)
    public void findPatientTest(){
        Long idSearch = 1L;
        Optional<Patient> patientSearch = patientService.findById(idSearch);
        assertTrue(patientSearch.isPresent());
    }

    @Test
    @Order(3)
    public void listPatientsTest(){
        List<Patient> testList = patientService.listPatients();
        assertTrue(testList.size() > 0);
    }

    @Test
    @Order(4)
    public void updatePatientTest(){
        Long idSearch = 1L;
        Patient patientTest2 = new Patient(idSearch,"Perez", "Diego", "diego@test.com", 184569, LocalDate.of(2022,02,22), new Address("Pellegrini",111,"Rosario","Santa Fe"));
        patientService.updatePatient(patientTest2);
        Optional<Patient> patientSearch = patientService.findById(idSearch);
        assertEquals("Perez", patientSearch.get().getSurname());
    }

    @Test
    @Order(5)
    public void deletePatientTest(){
        Long idSearch = 1L;
        patientService.deletePatient(idSearch);
        Optional<Patient> patientSearch = patientService.findById(idSearch);
        assertFalse(patientSearch.isPresent());
    }

}