package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.Examination;
import com.healthcare.repository.ExaminationRepository;

@Service
public class ExaminationService {
	private static final Logger logger = LoggerFactory.getLogger(ExaminationService.class);
	
	@Autowired
	public ExaminationRepository examinationRepository;

	/**
	 * @return examinations list
	 */
	public List<Examination> getAllExaminations() {
		logger.info("Entering into getAllExaminations method {}", System.currentTimeMillis());
		List<Examination> examinations = new ArrayList<>();
		examinationRepository.findAll().forEach(examinations::add);
		logger.debug("examinations {}", examinations);
		return examinations;
	}

	/**
	 * @param id
	 * @return examination
	 */
	public Optional<Examination> getExaminationById(Long id) {
		logger.info("Entering into getExaminationById method {}", System.currentTimeMillis());
		return examinationRepository.findById(id);
	}

	/**
	 * @param examination
	 */
	public void addExamination(Examination examination) {
		logger.info("Entering into addExamination method {}", System.currentTimeMillis());
		examinationRepository.save(examination);
	}

	/**
	 * @param id
	 */
	public void deleteExamination(Long id) {
		logger.info("Entering into deleteExamination method {}", System.currentTimeMillis());
		examinationRepository.deleteById(id);
	}

	/**
	 * @param id
	 * @param examination
	 */
	public void updateExamination(Long id, Examination examination) {
		logger.info("Entering into updateExamination method {}", System.currentTimeMillis());
		examination.setId(id);
		examinationRepository.save(examination);
	}

	
}
