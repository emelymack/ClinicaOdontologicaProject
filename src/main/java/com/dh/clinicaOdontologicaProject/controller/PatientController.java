package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologicaProject.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        System.out.println("Getting patients...");
        return ResponseEntity.ok(patientService.listPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatient(@PathVariable Long id){
        System.out.println("Looking for patient with id "+id+"...");
        Optional<Patient> patientSearch = patientService.findById(id);
        if(patientSearch.isPresent()){
            return ResponseEntity.ok(patientSearch.get());
        }
        else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
        }
    }

    @PostMapping
    public ResponseEntity<Patient> postNewPatient(@RequestBody Patient patient){
        System.out.println("Registering new patient...");
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        System.out.println("Updating patient...");
        Optional<Patient> patientSearch = patientService.findById(patient.getId());
        if(patientSearch.isPresent()){
            return ResponseEntity.ok(patientService.updatePatient(patient));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) throws ResourceNotFoundException{
        System.out.println("Deleting patient...");
        Optional<Patient> patientSearch = patientService.findById(id);
        if(patientSearch.isPresent()){
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient with id: '"+id+"' has been successfully deleted from the database");
        } else{
            throw new ResourceNotFoundException("Patient with id '"+id+"' doesn't exist in our database.");
        }
    }









}
