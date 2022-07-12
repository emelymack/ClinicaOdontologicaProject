package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Address;
import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AppointmentServiceTest {

        @Autowired
        IAppointmentService appointmentService;

        public Patient patientTestAppmt = new Patient(2L,"Abraham", "Silvina", "silvina@test.com", 197586, LocalDate.of(2022,4,13), new Address("Pellegrini",111,"Córdoba","Córdoba"));
        public Dentist dentistTestAppmt = new Dentist(2L,"Miranda", "Rodrigo", 112233);

        @Test
        @Order(1)
        public void addAppointmentTest() throws BadRequestException {
            System.out.println("TEST: adding new Appointment...");
            Appointment appointmentTest = new Appointment(patientTestAppmt, dentistTestAppmt, LocalDate.of(2022,7,12));
            appointmentService.saveAppointment(appointmentTest);
            //search for appointmentTest
            Optional<Appointment> appointmentTestFound = appointmentService.findById(1L);
            assertTrue(appointmentTestFound.isPresent());
        }

        @Test
        @Order(2)
        public void findAppointmentTest(){
            System.out.println("TEST: looking for Appointment...");
            Long idSearch = 1L;
            Optional<Appointment> appointmentSearch = appointmentService.findById(idSearch);
            assertTrue(appointmentSearch.isPresent());
        }

        @Test
        @Order(3)
        public void listAppointmentsTest(){
            System.out.println("TEST: Appointment list");
            List<Appointment> testList = appointmentService.listAppointments();
            assertTrue(testList.size() > 0);
        }

        @Test
        @Order(4)
        public void updateAppointmentTest(){
            Long idSearch = 1L;
            System.out.println("Updating appointment date...");
            Appointment appointmentTest2 = new Appointment(idSearch,patientTestAppmt,dentistTestAppmt,LocalDate.of(2022,8,1));
            appointmentService.updateAppointment(appointmentTest2);
            Optional<Appointment> appointmentSearch = appointmentService.findById(idSearch);
            assertEquals(LocalDate.of(2022,8,1), appointmentSearch.get().getDate());
        }

        @Test
        @Order(5)
        public void deleteAppointmentTest(){
            System.out.println("TEST: deleting Appointment...");
            Long idSearch = 1L;
            appointmentService.deleteAppointment(idSearch);
            Optional<Appointment> appointmentSearch = appointmentService.findById(idSearch);
            assertFalse(appointmentSearch.isPresent());
        }

}
