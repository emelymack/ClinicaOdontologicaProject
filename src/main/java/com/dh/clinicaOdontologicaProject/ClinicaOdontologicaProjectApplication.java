package com.dh.clinicaOdontologicaProject;

import com.dh.clinicaOdontologicaProject.entity.Address;
import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.service.DentistService;
import com.dh.clinicaOdontologicaProject.service.IPatientService;
import com.dh.clinicaOdontologicaProject.service.PatientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ClinicaOdontologicaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaProjectApplication.class, args);
	}

}
