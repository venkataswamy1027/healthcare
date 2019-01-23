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

import com.healthcare.model.Examination;
import com.healthcare.service.ExaminationService;

@Path("/examination")
public class ExaminationController {
	private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);

	@Autowired
	private ExaminationService examinationService;

	/**
	 * This method used to get all examination details
	 * 
	 * @return list of examinations
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllExaminations() {
		logger.info("Entering into getAllExaminations method {}", System.currentTimeMillis());
		try {
			List<Examination> examinations = examinationService.getAllExaminations();
			return Response.ok().entity(examinations).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getAllExaminations method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to get one examination detail
	 * 
	 * @param id
	 * @return hospital
	 */
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getExaminationById(@PathParam("id") Long id) {
		logger.info("Entering into getExaminationById method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			Optional<Examination> examinations = examinationService.getExaminationById(id);
			if (!examinations.isPresent()) {
				throw new RuntimeException("examination id doesn't exist");
			} else {
				Examination examination = examinations.get();
				return Response.ok().entity(examination).build();
			}
		} catch (RuntimeException re) {
			return Response.ok().entity(re.getMessage()).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getExaminationById method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to add examination detail
	 * 
	 * @param examination
	 * @return message
	 */
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addExamination(Examination examination) {
		logger.info("Entering into addHospital method {}", System.currentTimeMillis());
		try {
			logger.debug("examination {}", examination);
			examinationService.addExamination(examination);
			return Response.ok().entity("examination added successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on addHospital method {}", System.currentTimeMillis());
		}

	}

	/**
	 * This method used to update examination detail
	 * 
	 * @param id
	 * @param examination
	 * @return message
	 */
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateExamination(@PathParam("id") Long id, Examination examination) {
		logger.info("Entering into updateHospital method {}", System.currentTimeMillis());
		try {
			logger.debug("id {} and examination {}", id, examination);

			examinationService.updateExamination(id, examination);
			return Response.ok().entity("examination updated successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on updateHospital method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to delete examination detail
	 * 
	 * @param id
	 * @return message
	 */
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deleteExamination(@PathParam("id") Long id) {
		logger.info("Entering into deleteExamination method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			examinationService.deleteExamination(id);
			return Response.ok().entity("examination deleted successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on deleteExamination method {}", System.currentTimeMillis());
		}
	}
}
