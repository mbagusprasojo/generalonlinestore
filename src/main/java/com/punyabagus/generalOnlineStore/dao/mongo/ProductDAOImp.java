package com.punyabagus.generalOnlineStore.dao.mongo;

import com.punyabagus.generalOnlineStore.dao.ProductDAO;
import com.punyabagus.generalOnlineStore.pojo.ProductData.Product;
import com.punyabagus.mongo.MongoInstance;
import org.bson.Document;

import java.util.List;

import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.DB_NAME;
import static com.punyabagus.generalOnlineStore.dao.mongo.Constant.PRODUCT_COLLECTION;

/**
 * Created by prasojo on 9/15/17.
 */
public class ProductDAOImp extends MongoInstance<Product> implements ProductDAO<Product> {

    public ProductDAOImp() {
        super(DB_NAME, PRODUCT_COLLECTION, Product.class);
    }

    @Override
    public List<Product> getAll() {
        return super.find(new Document());
    }
}
