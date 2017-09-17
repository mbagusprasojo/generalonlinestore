package com.punyabagus.generalOnlineStore.services;

import com.punyabagus.generalOnlineStore.logic.TransactionLogic;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Order;
import com.punyabagus.generalOnlineStore.pojo.OrderData.OrderList;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Status;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by prasojo on 9/14/17.
 */
@Path("/order")
public class OrderService {

    private TransactionLogic transactionLogic;

    @Inject
    public OrderService(TransactionLogic transactionLogic) {
        this.transactionLogic = transactionLogic;
    }

    /**
     * Add product to existing order
     * @return
     */
    @PUT
    @Path("/product")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order addProduct(Order order) {
        Order newOrder = transactionLogic.addProduct(order);

        return newOrder != null ? newOrder : Order.newBuilder().build();
    }

    /**
     * Remove product from existing order
     * @param orderId
     * @param productId
     * @return
     */
    @DELETE
    @Path("/product/{orderId}/{productId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response removeProduct(@PathParam("orderId") String orderId, @PathParam("productId") String productId) {

        if (transactionLogic.removeProduct(orderId, productId)) {
            return Response.ok("Product Removed.").build();
        }

        return Response.serverError().build();
    }

    /**
     * Add coupon to existing order
     * @return
     */
    @PUT
    @Path("/coupon")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order addCoupon(Order order) {

        Order newOrder = transactionLogic.addCoupon(order);

        return newOrder != null ? newOrder : Order.newBuilder().build();
    }

    /**
     * Remove coupon from existing order
     * @param orderId
     * @return
     */
    @DELETE
    @Path("/coupon/{orderId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response removeCoupon(@PathParam("orderId") String orderId) {

        if (transactionLogic.removeCoupon(orderId)) {
            return Response.ok("Coupon Removed.").build();
        }

        return Response.serverError().build();
    }

    /**
     * Return all order
     * @return
     */
    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public OrderList getAll() {
        return transactionLogic.getAll();
    }

    /**
     * Return details of order
     * @param orderId
     * @return
     */
    @GET
    @Path("/{orderId}")
    @Produces(APPLICATION_JSON)
    public Order get(@PathParam("orderId") String orderId) {
        return transactionLogic.getOrderDetails(orderId);
    }

    /**
     * Return status of order
     * @param orderId
     * @return
     */
    @GET
    @Path("/status/{orderId}")
    @Produces(APPLICATION_JSON)
    public Response getStatus(@PathParam("orderId") String orderId) {
        Status status = transactionLogic.getOrderStatus(orderId);

        if (status != null) {
            return Response.ok(status.toString()).build();
        }

        return Response.ok("Order not Found.").build();
    }

    /**
     * Submit Order
     * @param order
     * @return
     */
    @POST
    @Path("/submit")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Order submitOrder(Order order) {
        return transactionLogic.submitOrder(order);
    }
}
