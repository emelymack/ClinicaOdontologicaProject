package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologicaProject.service.IPatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private static final Logger logger = Logger.getLogger(PatientController.class);

    @Autowired
    private IPatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        System.out.println("Getting patients...");
        return ResponseEntity.ok(patientService.listPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatient(@PathVariable Long id) throws ResourceNotFoundException {
        System.out.println("Looking for patient with id "+id+"...");
        Optional<Patient> patientSearch = patientService.findById(id);
        if(patientSearch.isPresent()){
            return ResponseEntity.ok(patientSearch.get());
        }
        else {
            logger.error("Patient search — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Patient with id '"+id+"' does not exist in out DB.");
        }
    }

    @PostMapping
    public ResponseEntity<Patient> postNewPatient(@RequestBody Patient patient) throws BadRequestException {
        System.out.println("Registering new patient...");
        ResponseEntity<Patient> res = ResponseEntity.ok(patientService.savePatient(patient));
        if(res.getStatusCode().is2xxSuccessful()){
            logger.info("New patient created with id: "+patient.getId());
            return res;
        } else{
            logger.error("Patient post --> FAILED.");
            throw new BadRequestException("Request failed. Try again");
        }
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws ResourceNotFoundException {
        System.out.println("Updating patient...");
        Optional<Patient> patientSearch = patientService.findById(patient.getId());
        if(patientSearch.isPresent()){
            return ResponseEntity.ok(patientService.updatePatient(patient));
        } else{
            logger.error("Patient required for UPDATE — id: "+patient.getId()+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Patient with id '"+patient.getId()+"' does not exist in out database.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) throws ResourceNotFoundException{
        System.out.println("Deleting patient...");
        Optional<Patient> patientSearch = patientService.findById(id);
        if(patientSearch.isPresent()){
            patientService.deletePatient(id);
            logger.info("Patient with id: '"+id+"' has been successfully deleted from the database");
            return ResponseEntity.ok("Patient with id: '"+id+"' has been successfully deleted from the database");
        } else{
            logger.error("Patient required for DELETE — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Patient with id '"+id+"' doesn't exist in our database.");
        }
    }

}
