package com.healthcare;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.Patient;
import com.healthcare.service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientControllerTest {

	@Autowired
	private PatientService patientService;

	// get all patients
	@Test
	public void getAllPatients() {
		System.out.println("getAllPatients method executed..!");
		List<Patient> patients = patientService.getAllPatients();
		System.out.println("total no.of patients :" + patients.size());
		assertThat(patients.size()).isEqualTo(2);
	}

	// get patient by id
	@Test
	public void getPatientById() {
		System.out.println("getPatientById method executed..!");
		long id = 1;
		Optional<Patient> patients = patientService.getPatientById(id);
		System.out.println("patient id is present :" + patients.isPresent());
		Patient patient = patients.get();
		assertThat(patient.getId()).isEqualTo(1);
	}

	// get patient age by id
	@Test
	public void calculatePatientAge() throws RuntimeException, Exception {
		System.out.println("calculatePatientAge method executed..!");
		long id = 1;
		int patientAge = patientService.calculateAge(id);
		System.out.println("patientAge :" + patientAge);
		assertThat(patientAge).isEqualTo(24);
	}
}
