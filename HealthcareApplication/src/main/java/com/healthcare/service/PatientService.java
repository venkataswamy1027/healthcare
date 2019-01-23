package com.healthcare.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;

@Service
public class PatientService {
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	@Autowired
	public PatientRepository patientRepository;

	/**
	 * @return patients list
	 */
	public List<Patient> getAllPatients() {
		logger.info("Entering into getAllPatients method {}", System.currentTimeMillis());
		List<Patient> patients = new ArrayList<>();
		patientRepository.findAll().forEach(patients::add);
		logger.debug("patients {}", patients);
		return patients;
	}

	/**
	 * @param id
	 * @return patient data
	 */
	public Optional<Patient> getPatientById(Long id) {
		logger.info("Entering into getPatientById method {}", System.currentTimeMillis());
		return patientRepository.findById(id);
	}

	/**
	 * @param patient
	 */
	public void addPatient(Patient patient) {
		logger.info("Entering into addPatient method {}", System.currentTimeMillis());
		patientRepository.save(patient);
	}

	/**
	 * @param id
	 */
	public void deletePatient(Long id) {
		logger.info("Entering into deletePatient method {}", System.currentTimeMillis());
		patientRepository.deleteById(id);
	}

	/**
	 * @param id
	 * @param patient
	 */
	public void updatePatient(Long id, Patient patient) {
		logger.info("Entering into updatePatient method {}", System.currentTimeMillis());
		patient.setId(id);
		patientRepository.save(patient);
	}

	/**
	 * @param id
	 * @return age
	 * @throws RuntimeException
	 * @throws Exception
	 */
	public int calculateAge(Long id) throws RuntimeException, Exception {
		logger.info("Entering into calculateAge method {}", System.currentTimeMillis());
		Optional<Patient> patients = getPatientById(id);
		if (!patients.isPresent()) {
			throw new RuntimeException("patient id doesn't exist");
		} else {
			Patient patient = patients.get();
			logger.debug("patient {}", patient);
			LocalDate curDate = LocalDate.now();
			String date = convertDateToString(patient.getDateOfBirth());
			logger.debug("date {}", date);
			LocalDate dob = LocalDate.parse(date);
			int currentAge = Period.between(dob, curDate).getYears();
			logger.debug("currentAge {}", currentAge);
			return currentAge;
		}
	}

	/**
	 * 
	 * @param dateOfBirth
	 * @return string date
	 */
	private String convertDateToString(Date dateOfBirth) throws Exception {
		logger.info("Entering into convertDateToString method {}", System.currentTimeMillis());
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(dateOfBirth);
			return strDate;
		} finally {
			logger.info("Exiting on convertDateToString method {}", System.currentTimeMillis());
		}
	}
}
