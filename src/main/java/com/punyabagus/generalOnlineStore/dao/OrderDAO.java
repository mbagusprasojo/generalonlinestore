package com.punyabagus.generalOnlineStore.dao;

/**
 * Created by prasojo on 9/14/17.
 */
public interface OrderDAO<Order> {
    Order insert(Order order);
    Order update(Order order);
    Order getById(String orderId);
    Order getByShipmentId(String shipmentId);
}
