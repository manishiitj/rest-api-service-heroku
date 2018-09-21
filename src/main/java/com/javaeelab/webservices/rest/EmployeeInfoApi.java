package com.javaeelab.webservices.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.javaeelab.webservices.rest.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.*;

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

    private static List<Order> US_IN = Arrays.asList(
            Order.builder().customerid("IN_BLR").sellerid("US_1").entityid("US_IN").itemid("CAMERA").nextcycle
                    ("25Sept18").orderid("1").status("OrderPlace").weight(8).build();
    private static Order US_EU
    private static Order CN_

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        logger.info("Returning all employee record");

        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());


        Session session = factory.openSession();
        Transaction tx = null;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("Hello");

        try {
            tx = session.beginTransaction();
            session.save(orderEntity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Response.ok(orderEntity).build();
    }


    @POST
    @Path("placeOrder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeOrder(PlaceOrderRequest placeOrderRequest) throws Exception {

        PlaceOrderResponse placeOrderResponse = null;
        if(placeOrderRequest.itemId.equals("CAMERA")){
             placeOrderResponse = PlaceOrderResponse.builder().date(US_IN.get(0).getNextcycle())
                    .orderId(US_IN.get(0).getOrderid())
                    .status("OrderPlaced").build();
        }

        return Response.ok(placeOrderResponse).build();
    }
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
