package com.punyabagus.generalOnlineStore.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by prasojo on 9/14/17.
 */
@Path("/product")
public class ProductService {

    /**
     * Return List of Available Products
     * @return
     */
    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public Response list() {
        return Response.ok("dummy").build();
    }
}
