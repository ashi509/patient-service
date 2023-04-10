package com.patient.service.service.impl;

import com.patient.service.entity.Patient;
import com.patient.service.repository.PatientRepository;
import com.patient.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResponseEntity<?> addPatient(Patient patient) {
        var response=patientRepository.save(patient);
        if(response==null)
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Something went wrong data could not be saved");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> updatePatient(Patient patient, long id) {
        var patientFromDB=patientRepository.findById(id).get();
        if(patientFromDB==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Patient not found with id "+id);
        patient.setId(id);
        patient.setCreatedDate(new Date());
        patient.getPatientContacts().setId(patientFromDB.getPatientContacts().getId());
        patient.getPatientContacts().setCreatedDate(new Date());
        patientFromDB=patientRepository.save(patient);
        if(patientFromDB==null)
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Something went wrong data could not be updated");
        return ResponseEntity.status(HttpStatus.OK).body(patientFromDB);
    }

    @Override
    public ResponseEntity<?> deletePatient(long id) {
        var patient= patientRepository.findById(id).get();
        if(patient==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Patient could not be delete due to record not found with id "+id);
        patientRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @Override
    public ResponseEntity<?> fetchPatient(long id) {
        var patient=patientRepository.findById(id).get();
        if(patient==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Patient not found with id "+id);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @Override
    public ResponseEntity<?> fetchAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientRepository.findAll());
    }

    @Override
    public ResponseEntity<?> fetchAllPatientsByDesc() {
        var sortedPatientData=patientRepository.findAll();
        sortedPatientData.sort(Comparator.comparing(Patient::getName).reversed());
        return ResponseEntity.status(HttpStatus.OK).body(sortedPatientData);
    }
}
