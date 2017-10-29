package com.javaeelab.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javaeelab.webservices.rest.model.EmployeesDTO;
import org.apache.commons.lang.StringUtils;

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

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllEmployees() {
        return Response.ok(getEmpInfoService().getAllEmployees()).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEmployeeById(@PathParam("id") String id) {

        if (StringUtils.isEmpty(id) && Integer.parseInt(id) < 0) {
            return Response.status(Status.BAD_REQUEST).entity("Id must be greater than 0").build();
        }

        EmployeesDTO employees = getEmpInfoService().getEmployeeById(Integer.parseInt(id));

        if (employees != null) {
            return Response.ok().entity(employees).build();
        }

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

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") String id) {

        if (getEmpInfoService().deleteEmployee(Integer.parseInt(id))) {
            return Response.ok().build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    private EmployeeInfoService getEmpInfoService() {
        return EmployeeInfoServiceFactory.getEmployeeInfoService();
    }
}
