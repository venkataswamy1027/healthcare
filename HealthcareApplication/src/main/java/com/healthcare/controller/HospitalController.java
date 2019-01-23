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

import com.healthcare.model.Hospital;
import com.healthcare.service.HospitalService;

@Path("/hospital")
public class HospitalController {
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;
	

	/**
	 * This method used to get all hospital with their patient details
	 * 
	 * @return list of hospitals
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllHospitals() {
		logger.info("Entering into getAllHospitals method {}", System.currentTimeMillis());
		try {
			List<Hospital> hospitals = hospitalService.getAllHospitals();
			logger.debug("hospitals {}", hospitals);
			return Response.ok().entity(hospitals).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getAllHospitals method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to get one hospital with the patient detail
	 * 
	 * @param id
	 * @return hospital
	 */
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getHospitalById(@PathParam("id") Long id) {
		logger.info("Entering into getHospitalById method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			Optional<Hospital> hospitals = hospitalService.getHospitalById(id);
			logger.debug("hospitals {}", hospitals);
			if (!hospitals.isPresent()) {
				throw new RuntimeException("hospital id doesn't exist");
			} else {
				Hospital hospital = hospitals.get();
				logger.debug("hospital {}", hospitals);
				return Response.ok().entity(hospital).build();
			}
		} catch (RuntimeException re) {
			return Response.ok().entity(re.getMessage()).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on getHospitalById method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to add hospital with the patient detail
	 * 
	 * @param hospital
	 * @return message
	 */
	@POST
	@Path("add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addHospital(Hospital hospital) {
		logger.info("Entering into addHospital method {}", System.currentTimeMillis());
		try {
			logger.debug("hospital {}", hospital);
			hospitalService.addHospital(hospital);
			return Response.ok().entity("hospital added successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on addHospital method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to update hospital detail
	 * 
	 * @param id
	 * @param hospital
	 * @return message
	 */
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateHospital(@PathParam("id") Long id, Hospital hospital) {
		logger.info("Entering into updateHospital method {}", System.currentTimeMillis());
		try {
			logger.debug("id {} and hospital {}", id, hospital);
			hospitalService.updateHospital(id, hospital);
			return Response.ok().entity("hospital updated successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on updateHospital method {}", System.currentTimeMillis());
		}
	}

	/**
	 * This method used to delete hospital detail
	 * 
	 * @param id
	 * @return message
	 */
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deleteHospital(@PathParam("id") Long id) {
		logger.info("Entering into deleteHospital method {}", System.currentTimeMillis());
		try {
			logger.debug("id {}", id);
			hospitalService.deleteHospital(id);
			return Response.ok().entity("hospital deleted successfully.").build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).build();
		} finally {
			logger.info("Exiting on deleteHospital method {}", System.currentTimeMillis());
		}
	}
}
