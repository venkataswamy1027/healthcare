package com.healthcare;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.Examination;
import com.healthcare.model.Hospital;
import com.healthcare.model.Patient;
import com.healthcare.service.HospitalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalControllerTest {

	@Autowired
	private HospitalService hospitalService;

	// get all hospitals
	@Test
	public void getAllHospitals() {
		System.out.println("getAllHospitals method executed..!");
		List<Hospital> hospitals = hospitalService.getAllHospitals();
		System.out.println("total no.of hospital :" + hospitals.size());
		assertThat(hospitals.size()).isEqualTo(2);
	}

	// get hospital by id
	@Test
	public void getHospitalById() {
		System.out.println("getHospitalById method executed..!");
		long id = 1;
		Optional<Hospital> hospitals = hospitalService.getHospitalById(id);
		System.out.println("hospital id is present :" + hospitals.isPresent());
		Hospital h = hospitals.get();
		assertThat(h.getId()).isEqualTo(1);
	}

	// add hospital
	@Test
	public void addHospital() {
		System.out.println("addHospital method executed..!");
		Hospital h = new Hospital();
		h.setName("Narayana Multispeciality Clinic Hospital");
		h.setDescription("Dental Hospital");
		// create patients list
		Patient p1 = new Patient();
		p1.setName("dhanu");
		String dob = "20-02-2001";
		Date dt1 = convertStringToDate(dob);
		p1.setDateOfBirth(dt1);
		p1.setGender("male");
		List<Patient> l1 = new ArrayList<>();
		// create examination list
		Examination e1 = new Examination();
		e1.setName("Electric pulp test");
		e1.setDescription("electric pulp test for tooth");
		String ex = "20-02-2018";
		Date dt2 = convertStringToDate(ex);
		e1.setExamDate(dt2);
		e1.setHeight(123.4);
		e1.setWeight(60.4);
		List<Examination> l2 = new ArrayList<>();
		l2.add(e1);
		// add examination list to patient
		p1.setExaminations(l2);
		l1.add(p1);
		// add patient list to hospital
		h.setPatients(l1);
		hospitalService.addHospital(h);
	}

	private Date convertStringToDate(String dob) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
			System.out.println("date :" + date);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
	}
}
