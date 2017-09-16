package com.punyabagus.generalOnlineStore.logic;

import com.punyabagus.generalOnlineStore.dao.CouponDAO;
import com.punyabagus.generalOnlineStore.pojo.CouponData.Coupon;
import com.punyabagus.generalOnlineStore.pojo.CouponData.CouponList;

import javax.inject.Inject;

/**
 * Created by prasojo on 9/16/17.
 */
public class CouponLogic {
    private CouponDAO couponDAO;

    @Inject
    public CouponLogic(CouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    public Coupon createCoupon(Coupon coupon) {
        return (Coupon) couponDAO.insert(coupon);
    }

    public CouponList getAll() {
        return CouponList.newBuilder().addAllCoupon(couponDAO.getAll()).build();
    }
}
