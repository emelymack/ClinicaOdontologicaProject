package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(){
        return ResponseEntity.ok(appointmentService.listAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findAppointment(@PathVariable Long id){
        Optional<Appointment> appointmentSearch = appointmentService.findById(id);
        if(appointmentSearch.isPresent()){
            return ResponseEntity.ok(appointmentSearch.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> postNewAppointment(@RequestBody Appointment appointment){
        if(appointment.getPatient() != null && appointment.getDentist() != null){
            return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment){
        Optional<Appointment> appointmentSearch = appointmentService.findById(appointment.getId());
        if(appointmentSearch.isPresent()){
            return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        Optional<Appointment> appointmentSearch = appointmentService.findById(id);
        if(appointmentSearch.isPresent()){
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment with id: "+id+" has been successfully deleted from the database.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
