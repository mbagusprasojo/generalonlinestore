package com.punyabagus.generalOnlineStore.services;

import com.punyabagus.generalOnlineStore.logic.ProductLogic;
import com.punyabagus.generalOnlineStore.pojo.ProductData.Product;
import com.punyabagus.generalOnlineStore.pojo.ProductData.ProductList;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by prasojo on 9/14/17.
 */
@Path("/product")
public class ProductService {

    private ProductLogic productLogic;

    @Inject
    public ProductService(ProductLogic productLogic) {
        this.productLogic = productLogic;
    }

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Product create(Product product) {
        return productLogic.createProduct(product);
    }

    /**
     * Return List of Available Products
     * @return
     */
    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public ProductList list() {
        return productLogic.getAll();
    }
}
