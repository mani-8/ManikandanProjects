package com.hms.repositories;
  
import static org.assertj.core.api.Assertions.assertThat; 

import java.util.List; import java.util.Optional;
  
import org.junit.jupiter.api.MethodOrderer; 
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.TestMethodOrder; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
  
import com.hms.entities.Doctor; 
  
  @DataJpaTest
  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
  class DoctorRepositoryTest {
	  
	  @Autowired
	  private DoctorRepository doctorRepository;
	  
	  @Test  
	  @Order(1) 
	  public void saveDoctorTest() { 
			  Doctor dr=new Doctor();
			  dr.setDoctorName("Ajith Andrew J"); 
			  dr.setDoctorSpecialization("Heart Specialist");
			  dr.setDoctorContact("2348711165"); 
			  dr.setAvailableOrNot("Available");
			  doctorRepository.save(dr);
			  assertThat(dr.getDoctorId()).isGreaterThan(0); 
		  }
	  
	  @Test
	  @Order(2)
	  public void getDoctorTest() { 
		  Doctor dr=doctorRepository.findById(1L).get(); 
		  assertThat(dr.getDoctorId()).isEqualTo(1L); 
	  }
	  
	  @Test
	  @Order(3) 
	  public void getAllDoctorTest() { 
		  List<Doctor>allDr=doctorRepository.findAll(); 
		  assertThat(allDr.size()).isGreaterThan(0);
	  }
	  
	  @Test 
	  @Order(4)
	  public void updateDoctorTest() { 
		  Doctor dr =doctorRepository.findById(1L).get(); 
		  dr.setDoctorName("Ajith"); 
		  Doctor uDr=doctorRepository.save(dr);
		  assertThat(uDr.getDoctorName()).isEqualTo("Ajith");
	  }
	  
	  @Test 
	  @Order(5) 
	  public void deleteDoctorTest() { 
		  Doctor deleteDr=doctorRepository.findById(1L).get();
		  doctorRepository.delete(deleteDr);
		  Doctor dr=null; 
		  Optional<Doctor>opDr=doctorRepository.findById(1L);
		  if(opDr.isPresent()) 
			  dr=opDr.get(); 
		  else
			  assertThat(dr).isNull();
	  }
  
  }