package com.punyabagus.generalOnlineStore.dao;

import java.util.List;

/**
 * Created by prasojo on 9/15/17.
 */
public interface ProductDAO<Product> {
    Product insert(Product product);
    Product update(Product product);
    Product getById(String id);
    List<Product> getAll();
}
