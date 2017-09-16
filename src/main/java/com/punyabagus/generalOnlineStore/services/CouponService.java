package com.punyabagus.generalOnlineStore.services;

import com.punyabagus.generalOnlineStore.logic.CouponLogic;
import com.punyabagus.generalOnlineStore.pojo.CouponData.Coupon;
import com.punyabagus.generalOnlineStore.pojo.CouponData.CouponList;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by prasojo on 9/14/17.
 */
@Path("/coupon")
public class CouponService {

    private CouponLogic couponLogic;

    @Inject
    public CouponService(CouponLogic couponLogic) {
        this.couponLogic = couponLogic;
    }

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Coupon create(Coupon product) {
        return couponLogic.createCoupon(product);
    }

    /**
     * Return List of Available Coupon
     * @return
     */
    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    public CouponList list() {
        return couponLogic.getAll();
    }
}
