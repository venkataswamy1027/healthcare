package com.healthcare;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.model.Examination;
import com.healthcare.service.ExaminationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExaminationControllerTest {

	@Autowired
	private ExaminationService examinationService;

	// get all examinations
	@Test
	public void getAllExaminations() {
		System.out.println("getAllExaminations method executed..!");
		List<Examination> examinations = examinationService.getAllExaminations();
		System.out.println("total no.of examination :" + examinations.size());
		assertThat(examinations.size()).isEqualTo(2);
	}

	// get examination by id
	@Test
	public void getExaminationById() {
		System.out.println("getExaminationById method executed..!");
		long id = 1;
		Optional<Examination> examinations = examinationService.getExaminationById(id);
		System.out.println("examination id is present :" + examinations.isPresent());
		Examination examination = examinations.get();
		assertThat(examination.getId()).isEqualTo(1);
	}
}
