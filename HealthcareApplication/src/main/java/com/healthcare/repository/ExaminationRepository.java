package com.healthcare.repository;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.model.Examination;

public interface ExaminationRepository extends CrudRepository<Examination, Long> {

}
