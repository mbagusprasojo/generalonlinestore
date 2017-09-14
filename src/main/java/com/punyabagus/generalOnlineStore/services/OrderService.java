package com.punyabagus.generalOnlineStore.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by prasojo on 9/14/17.
 */
@Path("/order")
public class OrderService {

    /**
     * Create blank new order
     * @return
     */
    @PUT
    @Path("/")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response save() {
        return Response.ok("dummy").build();
    }

    /**
     * Add product to existing order
     * @return
     */
    @PUT
    @Path("/product")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addProduct() {
        return Response.ok("dummy").build();
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
        return Response.ok("dummy").build();
    }

    /**
     * Add coupon to existing order
     * @return
     */
    @PUT
    @Path("/coupon")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addCoupon() {
        return Response.ok("dummy").build();
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
        return Response.ok("dummy").build();
    }

    /**
     * Return details of order
     * @param orderId
     * @return
     */
    @GET
    @Path("/{orderId}")
    @Produces(APPLICATION_JSON)
    public Response get(@PathParam("orderId") String orderId) {
        return Response.ok("dummy").build();
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
        return Response.ok("dummy").build();
    }

    /**
     * Change order status
     * NEW -> SUBMITTED
     * SUBMITTED -> WAITINGAPPROVAL
     * WAITINGAPPROVAL -> APPROVED
     * WAITINGAPPROVAL -> CANCELED
     * APPROVED -> SHIPPED
     * SHIPPED -> CLOSED
     * @return
     */
    @POST
    @Path("/status/{orderId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateStatus() {
        return Response.ok("dummy").build();
    }
}
