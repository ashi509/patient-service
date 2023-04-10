package com.patient.service.service;

import com.patient.service.entity.Patient;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<?> addPatient(Patient patient);
    ResponseEntity<?> updatePatient(Patient patient,long id);
    ResponseEntity<?> deletePatient(long id);
    ResponseEntity<?> fetchPatient(long id);
    ResponseEntity<?> fetchAllPatients();
    ResponseEntity<?> fetchAllPatientsByDesc();
}
