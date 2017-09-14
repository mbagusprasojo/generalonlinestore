package com.punyabagus.generalOnlineStore.dao.mongo;

import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Order;
import com.punyabagus.mongo.MongoInstance;
import org.bson.Document;

import java.util.List;

/**
 * Created by prasojo on 9/14/17.
 */
public class OrderDAOImp extends MongoInstance<Order> implements OrderDAO<Order> {

    private final static String DB_NAME = "GeneralOnlineStore";
    private final static String COLLECTION_NAME = "Order";

    public OrderDAOImp() {
        super(DB_NAME, COLLECTION_NAME, Order.class);
    }

    @Override
    public Order getByShipmentId(String shipmentId) {
        List<Order> result = super.find(new Document("shipmentId", shipmentId));

        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
}
