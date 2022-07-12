package com.dh.clinicaOdontologicaProject.service;

import com.dh.clinicaOdontologicaProject.entity.Address;
import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
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
        private IAppointmentService appointmentService;
        @Autowired
        private IPatientService patientService;
        @Autowired
        private IDentistService dentistService;

        @Test
        @Order(1)
        public void addAppointmentTest() throws BadRequestException {
            System.out.println("TEST: adding new Appointment...");

            Patient patientNew = new Patient("Abraham", "Silvina", "silvina@test.com", 197586, LocalDate.of(2022,4,13),
                    new Address("Pellegrini",111,"Córdoba","Córdoba"));
            patientService.savePatient(patientNew);
            Dentist dentistNew = new Dentist("Miranda", "Rodrigo", 112233);
            dentistService.saveDentist(dentistNew);

            Optional<Patient> patientTestAppmt = patientService.findById(1L);
            System.out.println(patientTestAppmt);
            Optional<Dentist> dentistTestAppmt = dentistService.findById(1L);
            System.out.println(dentistTestAppmt);

            Appointment appointmentTest = new Appointment(patientTestAppmt.get(), dentistTestAppmt.get(), LocalDate.of(2022,8,20));
            appointmentService.saveAppointment(appointmentTest);
            //search for appointmentTest
            Optional<Appointment> appointmentTestFound = appointmentService.findById(1L);
            assertTrue(appointmentTestFound.isPresent());
            assertEquals(197586, appointmentTestFound.get().getPatient().getDni());
            assertEquals(112233, appointmentTestFound.get().getDentist().getLicense());
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
            Patient patientTestAppmt = patientService.findById(1L).get();
            Dentist dentistTestAppmt = dentistService.findById(1L).get();
            Appointment appointmentTest2 = new Appointment(idSearch,patientTestAppmt,dentistTestAppmt,LocalDate.of(2022,8,30));
            appointmentService.updateAppointment(appointmentTest2);
            Optional<Appointment> appointmentSearch = appointmentService.findById(idSearch);
            assertEquals(LocalDate.of(2022,8,30), appointmentSearch.get().getDate());
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
