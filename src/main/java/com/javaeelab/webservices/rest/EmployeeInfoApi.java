package com.javaeelab.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javaeelab.webservices.rest.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
        Order order = new Order();

        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("Hello");
        session.save(orderEntity);

        session.getTransaction().commit();
        session.close();
        return Response.ok("Saved order").build();
    }

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getEmployeeById(@PathParam("id") String id) {
//
//        if (StringUtils.isEmpty(id) && Integer.parseInt(id) < 0) {
//            logger.info("Id is not valid");
//            return Response.status(Status.BAD_REQUEST).entity("Id must be greater than 0").build();
//        }
//
//        EmployeesDTO employees = getEmpInfoService().getEmployeeById(Integer.parseInt(id));
//
//        if (employees != null) {
//            logger.info("Returning the required record with id = " + id);
//            return Response.ok().entity(employees).build();
//        }
//
//        logger.info("No record found");
//        return Response.ok().entity("No record found").build();
//    }
//
//    @POST
//    @Path("add")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response addEmployee(final EmployeesDTO employeesDTO) {
//
//        if (employeesDTO == null) {
//            return Response.status(Status.BAD_REQUEST).entity("Empty data to add employee").build();
//        }
//
//        getEmpInfoService().addEmployee(employeesDTO);
//        return Response.ok().build();
//    }
//
//    private EmployeeInfoService getEmpInfoService() {
//        return EmployeeInfoServiceFactory.getEmployeeInfoService();
//    }
//
//    @POST
//    @Path("placeOrder")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response placeOrder(PlaceOrderRequest placeOrderRequest) throws Exception {
//        String orderId = String.valueOf(new Random().nextInt(1000000));
//        OrderDO orderDO = OrderDO.builder().orderId(orderId).itemId(placeOrderRequest.itemId)
//                .sellerId(placeOrderRequest.sellerId).customerId(placeOrderRequest.customerId)
//                .entityId(placeOrderRequest.sellerId.split("_")[0] + "_" + placeOrderRequest.customerId.split("_")[0])
//                .status("orderPlaced").weight(itemDAO.getWeightOfItem((placeOrderRequest.itemId))).build();
//        orderDAO.saveOrder(orderDO);
//
//        int nextSlot = new Random().nextInt(10);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, nextSlot);
//        String newDate = sdf.format(cal.getTime());
//
//        PlaceOrderResponse placeOrderResponse = PlaceOrderResponse.builder().date(newDate).orderId(orderId)
//                .status("OrderPlaced").build();
//        String json = objectMapper.writeValueAsString(placeOrderResponse);
//
//        return Response.ok(json, MediaType.APPLICATION_JSON).build();
//    }
//
//    @POST
//    @Path("getOrders")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getOrders(GetOrdersRequest getOrdersRequest) throws Exception {
//        List<OrderDO> orders;
//        if (getOrdersRequest.clientType.equals("seller")) {
//            orders = orderDAO.getOrders("seller", getOrdersRequest.value);
//        } else {
//            orders = orderDAO.getOrders("entity", getOrdersRequest.value);
//        }
//        String json = objectMapper.writeValueAsString(orders);
//
//        return Response.ok(json, MediaType.APPLICATION_JSON).build();
//    }
//
//    @POST
//    @Path("updateOrderStatus")
//    // @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateStatus(UpdateStatusRequest updateStatusRequest) throws Exception {
//        OrderDO orderDO = orderDAO.getOrder(updateStatusRequest.orderId).get();
//        orderDO.setStatus(updateStatusRequest.status);
//        orderDAO.saveOrder(orderDO);
//        return Response.ok("status changed to " + updateStatusRequest.status).build();
//    }
//
//    @POST
//    @Path("getEntityData")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getEntity(GetEntityRequest getEntityRequest) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        EntityDO entityData = entityDAO.getEntityData(getEntityRequest.entityId).get();
//        String json = objectMapper.writeValueAsString(entityData);
//        return Response.ok(json, MediaType.APPLICATION_JSON).build();
//    }
//
//    @POST
//    @Path("updateCapacity")
//    // @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateCapacity(UpdateCapacityRequest updateCapacityRequest) throws Exception {
//
//        EntityDO entityData = entityDAO.getEntityData(updateCapacityRequest.entityId).get();
//
//        OrderDO orderDO = orderDAO.getOrder(updateCapacityRequest.orderId).get();
//        orderDO.setStatus("shipedByEntity");
//        orderDAO.saveOrder(orderDO);
//
//        int upLeftCapcity = entityData.getCapacityLeft() - orderDO.getWeight();
//        List<String> orders = entityData.getOrders();
//        orders.add(updateCapacityRequest.orderId);
//
//        entityData.setCapacityLeft(upLeftCapcity);
//        entityData.setOrders(orders);
//        entityDAO.saveEntityData(entityData);
//
//        return Response.ok("capacity updated for Order: " + updateCapacityRequest.orderId).build();
//    }
}
