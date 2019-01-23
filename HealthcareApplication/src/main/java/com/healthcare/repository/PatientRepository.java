package com.healthcare.repository;

import org.springframework.data.repository.CrudRepository;

import com.healthcare.model.Patient;


public interface PatientRepository extends CrudRepository<Patient, Long> {

}
