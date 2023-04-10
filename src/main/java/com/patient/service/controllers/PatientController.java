package com.patient.service.controllers;

import com.patient.service.entity.Patient;
import com.patient.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient,@PathVariable long id){
        return patientService.updatePatient(patient,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable long id){
        return patientService.deletePatient(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchPatientById(@PathVariable long id){
        return patientService.fetchPatient(id);
    }

    @GetMapping
    public ResponseEntity<?> fetchAllPatients(){

        return patientService.fetchAllPatients();
    }

    @GetMapping("/desc")
    public ResponseEntity<?> fetchAllPatientsByNameDesc(){
        return patientService.fetchAllPatientsByDesc();
    }
}
