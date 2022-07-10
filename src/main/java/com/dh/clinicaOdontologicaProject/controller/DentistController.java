package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologicaProject.service.IDentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    private static final Logger logger = Logger.getLogger(Dentist.class);

    @Autowired
    private IDentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Dentist>> getDentists() {
        System.out.println("Getting dentists...");
        return ResponseEntity.ok(dentistService.listDentists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(@PathVariable Long id) throws ResourceNotFoundException {
        System.out.println("Looking for dentist with id "+id+"...");
        Optional<Dentist> dentistSearch = dentistService.findById(id);
        if (dentistSearch.isPresent()) {
            return ResponseEntity.ok(dentistSearch.get());
        } else {
            logger.error("Dentist search — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Dentist with id '"+id+"' does not exist in out DB.");
        }
    }

    @PostMapping
    public ResponseEntity<Dentist> postNewDentist(@RequestBody Dentist dentist) throws BadRequestException {
        System.out.println("Registering new dentist...");
        ResponseEntity<Dentist> res = ResponseEntity.ok(dentistService.saveDentist(dentist));
        if(res.getStatusCode().is2xxSuccessful()){
            logger.info("New dentist created with id: "+dentist.getId());
            return res;
        } else{
            logger.error("Dentist post --> FAILED.");
            throw new BadRequestException("Request failed. Try again");
        }
    }

    @PutMapping
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) throws ResourceNotFoundException {
        System.out.println("Updating dentist...");
        Optional<Dentist> dentistSearch = dentistService.findById(dentist.getId());
        if (dentistSearch.isPresent()) {
            return ResponseEntity.ok(dentistService.updateDentist(dentist));
        } else {
            logger.error("Dentist required for UPDATE — id: "+dentist.getId()+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Dentist with id '"+dentist.getId()+"' does not exist in out database.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        System.out.println("Deleting dentist...");
        Optional<Dentist> dentistSearch = dentistService.findById(id);
        if (dentistSearch.isPresent()) {
            dentistService.deleteDentist(id);
            logger.info("Dentist with id: '"+id+"' has been successfully deleted from the database");
            return ResponseEntity.ok("Dentist with id '"+id+"' has been successfully deleted from the database");
        } else{
            logger.error("Dentist required for DELETE — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Dentist with id '"+id+"' doesn't exist in our database.");
        }
    }

}
