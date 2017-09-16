package com.punyabagus.generalOnlineStore.dao.mongo;

import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Order;
import com.punyabagus.mongo.MongoInstance;
import org.bson.Document;

import java.util.List;

import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.DB_NAME;
import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.ORDER_COLLECTION;

/**
 * Created by prasojo on 9/14/17.
 */
public class OrderDAOImp extends MongoInstance<Order> implements OrderDAO<Order> {

    public OrderDAOImp() {
        super(DB_NAME, ORDER_COLLECTION, Order.class);
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

    @Override
    public Order update(Order order) {
        super.removeField(order.getId(), "product");
        super.removeField(order.getId(), "coupon");

        return super.update(order);
    }
}
