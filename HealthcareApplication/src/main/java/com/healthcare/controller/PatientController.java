package com.healthcare.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthcare.model.Patient;
import com.healthcare.service.PatientService;

@Path("/patient")
public class PatientController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	@Autowired
	private PatientService patientService;

	/**
	 * This method used to get all patient with their examination details
	 * 
	 * @return list of patients
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllPatients() {
		logger.info("Entering into getAllPatients method {}", System.currentTimeMillis());
		try {
			List<Patient> patients = patientService.getAllPatients();
			return Response.ok().entity(patients).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getAllPatients method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to get one patient with the examination details
	 * 
	 * @param id
	 * @return patient
	 */
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getPatientById(@PathParam("id") Long id) {
		logger.info("Entering into getPatientById method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			Optional<Patient> patients = patientService.getPatientById(id);
			if (!patients.isPresent()) {
				throw new RuntimeException("patient id doesn't exist");
			} else {
				Patient patient = patients.get();
				return Response.ok().entity(patient).build();
			}
		} catch (RuntimeException re) {
			return Response.ok().entity(re.getMessage()).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getPatientById method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to add patient with the examination details
	 * 
	 * @param patient
	 * @return message
	 */
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPatient(Patient patient) {
		logger.info("Entering into addPatient method {}", System.currentTimeMillis());
		try {
			logger.debug("patient {}", patient);
			patientService.addPatient(patient);
			return Response.ok().entity("patient added successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on addPatient method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to update patient detail
	 * 
	 * @param id
	 * @param patient
	 * @return message
	 */
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updatePatient(@PathParam("id") Long id, Patient patient) {
		logger.info("Entering into updatePatient method {}", System.currentTimeMillis());
		try {
			logger.debug("id {} and patient {}", id, patient);
			patientService.updatePatient(id, patient);
			return Response.ok().entity("Patient updated successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on updatePatient method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to delete patient detail
	 * 
	 * @param id
	 * @return message
	 */
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deletePatient(@PathParam("id") Long id) {
		logger.info("Entering into deletePatient method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			patientService.deletePatient(id);
			return Response.ok().entity("Patient deleted successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on deletePatient method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to calculate age of patient
	 * 
	 * @param id
	 * @return age
	 */
	@GET
	@Path("/age/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calculatePatientAge(@PathParam("id") Long id) {
		logger.info("Entering into calculatePatientAge method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			int currentAge = patientService.calculateAge(id);
			logger.debug("currentAge {}", currentAge);
			return Response.ok().entity(currentAge).build();
		} catch (RuntimeException re) {
			return Response.ok().entity(re.getMessage()).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on calculatePatientAge method {}", System.currentTimeMillis());
		}
	}

}
