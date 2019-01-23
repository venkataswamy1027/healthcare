package com.healthcare.repository;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.model.Hospital;


public interface HospitalRepository extends CrudRepository<Hospital, Long> {

}
