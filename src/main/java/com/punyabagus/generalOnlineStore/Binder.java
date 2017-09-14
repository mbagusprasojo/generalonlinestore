package com.punyabagus.generalOnlineStore;

import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.dao.mongo.OrderDAOImp;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by prasojo on 9/14/17.
 */
public class Binder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(OrderDAOImp.class).to(OrderDAO.class);
    }
}