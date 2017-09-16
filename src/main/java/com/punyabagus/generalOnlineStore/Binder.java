package com.punyabagus.generalOnlineStore;

import com.punyabagus.generalOnlineStore.dao.CouponDAO;
import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.dao.ProductDAO;
import com.punyabagus.generalOnlineStore.dao.mongo.CouponDAOImp;
import com.punyabagus.generalOnlineStore.dao.mongo.OrderDAOImp;
import com.punyabagus.generalOnlineStore.dao.mongo.ProductDAOImp;
import com.punyabagus.generalOnlineStore.logic.CouponLogic;
import com.punyabagus.generalOnlineStore.logic.ProductLogic;
import com.punyabagus.generalOnlineStore.logic.TransactionLogic;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by prasojo on 9/14/17.
 */
public class Binder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(OrderDAOImp.class).to(OrderDAO.class);
        bind(ProductDAOImp.class).to(ProductDAO.class);
        bind(CouponDAOImp.class).to(CouponDAO.class);

        bind(TransactionLogic.class).to(TransactionLogic.class);
        bind(ProductLogic.class).to(ProductLogic.class);
        bind(CouponLogic.class).to(CouponLogic.class);
    }
}