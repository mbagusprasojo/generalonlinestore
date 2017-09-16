package com.punyabagus.generalOnlineStore.logic;

import com.punyabagus.generalOnlineStore.dao.ProductDAO;
import com.punyabagus.generalOnlineStore.pojo.ProductData.Product;
import com.punyabagus.generalOnlineStore.pojo.ProductData.ProductList;

import javax.inject.Inject;

/**
 * Created by prasojo on 9/15/17.
 */
public class ProductLogic {
    private ProductDAO productDAO;

    @Inject
    public ProductLogic(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product createProduct(Product product) {
        return (Product) productDAO.insert(product);
    }

    public ProductList getAll() {
        return ProductList.newBuilder().addAllProduct(productDAO.getAll()).build();
    }
}
