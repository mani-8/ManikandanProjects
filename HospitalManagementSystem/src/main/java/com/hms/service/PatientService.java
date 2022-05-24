package com.hms.service;

import com.hms.entities.Patient;

public interface PatientService {
	
	Patient savePatient(Patient p);
	
	 Patient appointPatient(long pId,long drId);
	 
	 Patient getPatientById(long id);

}
