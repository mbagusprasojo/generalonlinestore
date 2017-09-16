package com.punyabagus.generalOnlineStore.dao.mongo;

import com.punyabagus.generalOnlineStore.dao.CouponDAO;
import com.punyabagus.generalOnlineStore.pojo.CouponData.Coupon;
import com.punyabagus.mongo.MongoInstance;
import org.bson.Document;

import java.util.List;

import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.COUPON_COLLECTION;
import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.DB_NAME;

/**
 * Created by prasojo on 9/15/17.
 */
public class CouponDAOImp extends MongoInstance<Coupon> implements CouponDAO<Coupon> {

    public CouponDAOImp() {
        super(DB_NAME, COUPON_COLLECTION, Coupon.class);
    }

    @Override
    public Coupon getByCode(String code) {
        List<Coupon> couponList = super.find(new Document("code", code));

        if (couponList != null && couponList.size() > 0) {
            return couponList.get(0);
        }

        return null;
    }

    @Override
    public List<Coupon> getAll() {
        return super.find(new Document());
    }
}
