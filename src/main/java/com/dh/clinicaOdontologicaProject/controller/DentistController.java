package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    private IDentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Dentist>> getDentists() {
        return ResponseEntity.ok(dentistService.listDentists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(@PathVariable Long id) {
        Optional<Dentist> dentistSearch = dentistService.findById(id);
        if (dentistSearch.isPresent()) {
            return ResponseEntity.ok(dentistSearch.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Dentist> postNewDentist(@RequestBody Dentist dentist){
        return ResponseEntity.ok(dentistService.saveDentist(dentist));
    }

    @PutMapping
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist){
        Optional<Dentist> dentistSearch = dentistService.findById(dentist.getId());
        if (dentistSearch.isPresent()) {
            return ResponseEntity.ok(dentistService.updateDentist(dentist));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id){
        Optional<Dentist> dentistSearch = dentistService.findById(id);
        if (dentistSearch.isPresent()) {
            dentistService.deleteDentist(id);
            return ResponseEntity.ok("Dentist with id: "+id+" has been deleted from the database");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
