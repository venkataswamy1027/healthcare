package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.Hospital;
import com.healthcare.repository.HospitalRepository;

@Service
public class HospitalService {
	private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

	@Autowired
	public HospitalRepository hospitalRepository;

	/**
	 * @return hospital list
	 */
	public List<Hospital> getAllHospitals() {
		logger.info("Entering into getAllHospitals method {}", System.currentTimeMillis());
		List<Hospital> hospitals = new ArrayList<>();
		hospitalRepository.findAll().forEach(hospitals::add);
		logger.debug("hospitals {}", hospitals);
		return hospitals;
	}

	/**
	 * @param id
	 * @return hospital
	 */
	public Optional<Hospital> getHospitalById(Long id) {
		logger.info("Entering into getHospitalById method {}", System.currentTimeMillis());
		return hospitalRepository.findById(id);
	}

	/**
	 * @param hospital
	 */
	public void addHospital(Hospital hospital) {
		logger.info("Entering into addHospital method {}", System.currentTimeMillis());
		hospitalRepository.save(hospital);
	}

	/**
	 * @param id
	 */
	public void deleteHospital(Long id) {
		logger.info("Entering into deleteHospital method {}", System.currentTimeMillis());
		hospitalRepository.deleteById(id);
	}

	/**
	 * @param id
	 * @param hospital
	 */
	public void updateHospital(Long id, Hospital hospital) {
		logger.info("Entering into updateHospital method {}", System.currentTimeMillis());
		hospital.setId(id);
		hospitalRepository.save(hospital);
	}

}
