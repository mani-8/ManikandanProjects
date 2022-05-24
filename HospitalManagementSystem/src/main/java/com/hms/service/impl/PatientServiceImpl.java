package com.hms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repositories.DoctorRepository;
import com.hms.repositories.PatientRepository;
import com.hms.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired 
	PatientRepository patientRepository;
	
	@Autowired 
	DoctorRepository doctorRepository;
	
	@Override
	public Patient savePatient(Patient p) {
		Optional<Patient> pat = patientRepository.findPatientContact(p);
		if(pat.isEmpty()){
			return patientRepository.save(p);
		}
		throw new ResourceNotFoundException(p.getPatientName()+" contact number is already exits!");
	}

	@Override 
	public Patient appointPatient(long pId,long drId) {
		Doctor dr=doctorRepository.findById(drId).orElseThrow(()->new ResourceNotFoundException("Doctor", "Id", drId));
		Patient p=patientRepository.findById(pId).orElseThrow(()->new ResourceNotFoundException("Patient", "Id", pId));
		int temp =dr.getPatientAttend();
			if(dr.getAvailableOrNot().equalsIgnoreCase("Available")) {
				if(temp<=2) {
					if(temp==2) {
						dr.setAvailableOrNot("Not Available");
						doctorRepository.save(dr);
					}	
						p.setDoctor(dr);	  
						temp++;
						dr.setPatientAttend(temp);
						return patientRepository.save(p);	
				}
			}
			throw new ResourceNotFoundException("Doctor "+dr.getDoctorName()+" is Not Available");
	}

	@Override
	public Patient getPatientById(long id) {
		return patientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient","Id", id));
	}
	  
		/*
		 * @Override public Patient updatePublisherById(Patient patient, long id) {
		 * 
		 * Patient existingPatient = patientRepository.findById(id).orElseThrow(()-> new
		 * ResourceNotFoundException("Patient","Id",id));
		 * 
		 * 
		 * existingPatient.setPatientName(patient.getPatientName());
		 * existingPatient.setPatientAge(patient.getPatientAge());
		 * 
		 * 
		 * patientRepository.save(existingPatient); return existingPatient; }
		 */
	 

}
