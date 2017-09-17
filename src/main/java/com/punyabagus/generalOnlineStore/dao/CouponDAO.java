package com.punyabagus.generalOnlineStore.dao;

import java.util.List;

/**
 * Created by prasojo on 9/15/17.
 */
public interface CouponDAO<Coupon> {
    Coupon getByCode(String code);
    Coupon insert(Coupon coupon);
    Coupon update(Coupon coupon);
    List<Coupon> getAll();
}
