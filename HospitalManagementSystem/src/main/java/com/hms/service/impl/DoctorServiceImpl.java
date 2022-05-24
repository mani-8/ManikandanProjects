package com.hms.service.impl;
  
  import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;
  
  import com.hms.exception.ResourceNotFoundException; import
  com.hms.entities.Doctor; import com.hms.repositories.DoctorRepository; import
  com.hms.service.DoctorService;
  
  @Service 
  public class DoctorServiceImpl implements DoctorService{
  
		
		  @Autowired 
		  DoctorRepository doctorRepository;
		  
		  @Override 
		  public Doctor saveDr(Doctor contact) {
			  Optional<Doctor> doc = doctorRepository.findDoctorContact(contact);
			  if(doc.isEmpty()){
				  return doctorRepository.save(contact);
			  }
			  throw new ResourceNotFoundException(contact.getDoctorName()+" contact number is already exits!");
		  }
		  
		  @Override
		  public List<Doctor> getAllDr() { 
			  return doctorRepository.findAll();
		  }
		  
		  @Override 
		  public Doctor getDrById(long id) { 
			  return doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor", "Id", id)); 
			  }
		  
		  @Override 
		  public Doctor updateDrById(Doctor dr, long id) { 
			  Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",id));
		  
			  existingDoctor.setDoctorName(dr.getDoctorName());
			  existingDoctor.setDoctorSpecialization(dr.getDoctorSpecialization());
			  existingDoctor.setDoctorContact(dr.getDoctorContact());
			  
			  doctorRepository.save(existingDoctor); return existingDoctor;
		  }
		  
		  @Override
		  public void deleteDrById(long id) {
			  doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor","Id",id));
			  doctorRepository.deleteById(id);
		  }
		  
  }
 