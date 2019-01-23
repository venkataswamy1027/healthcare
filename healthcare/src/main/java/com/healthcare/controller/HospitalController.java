package com.healthcare.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.Hospital;
import com.healthcare.service.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;

	/**
	 * This method used to get all hospital with their patient details
	 * 
	 * @return list of hospitals
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Hospital> getAllHospitals() {
		logger.info("Entering into getAllHospitals method {}", System.currentTimeMillis());
		List<Hospital> hospitals = null;
		try {
			hospitals = hospitalService.getAllHospitals();
			logger.debug("hospitals {}", hospitals);
		} catch (Exception e) {
			logger.error("error due to {}", e.getMessage());
		} finally {
			logger.info("Exiting on getAllHospitals method {}", System.currentTimeMillis());
		}
		return hospitals;
	}

	/**
	 * This method used to get one hospital with the patient detail
	 * 
	 * @param id
	 * @return hospital
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Hospital getHospitalById(@PathVariable("id") Long id) {
		logger.info("Entering into getHospitalById method {}", System.currentTimeMillis());
		Hospital hospital = null;
		try {
			logger.debug("id {}", id);
			Optional<Hospital> hospitals = hospitalService.getHospitalById(id);
			logger.debug("hospitals {}", hospitals);
			if (!hospitals.isPresent()) {
				throw new RuntimeException("hospital id doesn't exist");
			} else {
				hospital = hospitals.get();
				logger.debug("hospital {}", hospitals);
			}

		} catch (RuntimeException re) {
			logger.error("error due to {}", re.getMessage());
		} catch (Exception e) {
			logger.error("error due to {}", e.getMessage());
		} finally {
			logger.info("Exiting on getHospitalById method {}", System.currentTimeMillis());
		}
		return hospital;
	}

}
