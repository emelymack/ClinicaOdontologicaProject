package com.dh.clinicaOdontologicaProject.controller;

import com.dh.clinicaOdontologicaProject.entity.Appointment;
import com.dh.clinicaOdontologicaProject.entity.Dentist;
import com.dh.clinicaOdontologicaProject.entity.Patient;
import com.dh.clinicaOdontologicaProject.exceptions.BadRequestException;
import com.dh.clinicaOdontologicaProject.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologicaProject.service.DentistService;
import com.dh.clinicaOdontologicaProject.service.IAppointmentService;
import com.dh.clinicaOdontologicaProject.service.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private static final Logger logger = Logger.getLogger(AppointmentController.class);

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private DentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(){
        return ResponseEntity.ok(appointmentService.listAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentSearch = appointmentService.findById(id);
        if(appointmentSearch.isPresent()){
            return ResponseEntity.ok(appointmentSearch.get());
        } else {
            logger.error("Appointment search — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Appointment with id "+id+" does not exist in our database.");
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> postNewAppointment(@RequestBody Appointment appointment) throws BadRequestException {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) throws ResourceNotFoundException {
        Optional<Appointment> appointmentSearch = appointmentService.findById(appointment.getId());
        if(appointmentSearch.isPresent()){
            return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
        } else {
            logger.error("Appointment required for UPDATE — id: "+appointment.getId()+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Appointment with id '"+appointment.getId()+"' does not exist in our database.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentSearch = appointmentService.findById(id);
        if(appointmentSearch.isPresent()){
            appointmentService.deleteAppointment(id);
            logger.info("Appointment with id: '"+id+"' has been successfully deleted from the database");
            return ResponseEntity.ok("Appointment with id '"+id+"' has been successfully deleted from the database.");
        } else{
            logger.error("Appointment required for DELETE — id: "+id+" --> NOT FOUND.");
            throw new ResourceNotFoundException("Appointment with id '"+id+"' does not exist in our database.");
        }
    }
}
