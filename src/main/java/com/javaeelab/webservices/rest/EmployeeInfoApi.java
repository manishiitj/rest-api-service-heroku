package com.javaeelab.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javaeelab.webservices.rest.model.EmployeesDTO;
import com.javaeelab.webservices.rest.model.Student;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author azam.akram
 *
 * EmployeeDTO information API
 * This API supports HTTP GET, POST and DELETE methods
 * It accepts input parameters and produces results in both XML and JSON format
 *
 */

@Path("employees")
public class EmployeeInfoApi {

    private final static Logger logger = Logger.getLogger(EmployeeInfoApi.class);

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        logger.info("Returning all employee record");
        Student student = new Student("Hi",12);
        Student student1 = new Student("HWRt", 32);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        return Response.ok(studentList).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeById(@PathParam("id") String id) {

        if (StringUtils.isEmpty(id) && Integer.parseInt(id) < 0) {
            logger.info("Id is not valid");
            return Response.status(Status.BAD_REQUEST).entity("Id must be greater than 0").build();
        }

        EmployeesDTO employees = getEmpInfoService().getEmployeeById(Integer.parseInt(id));

        if (employees != null) {
            logger.info("Returning the required record with id = " + id);
            return Response.ok().entity(employees).build();
        }

        logger.info("No record found");
        return Response.ok().entity("No record found").build();
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEmployee(final EmployeesDTO employeesDTO) {

        if (employeesDTO == null) {
            return Response.status(Status.BAD_REQUEST).entity("Empty data to add employee").build();
        }

        getEmpInfoService().addEmployee(employeesDTO);
        return Response.ok().build();
    }

    private EmployeeInfoService getEmpInfoService() {
        return EmployeeInfoServiceFactory.getEmployeeInfoService();
    }
}
