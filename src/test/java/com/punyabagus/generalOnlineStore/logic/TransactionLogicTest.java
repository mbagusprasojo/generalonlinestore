package com.punyabagus.generalOnlineStore.logic;

import com.punyabagus.generalOnlineStore.dao.CouponDAO;
import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.dao.ProductDAO;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Order;
import com.punyabagus.generalOnlineStore.pojo.ProductData.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by prasojo on 9/17/17.
 */
public class TransactionLogicTest {

    private TransactionLogic transactionLogic;

    @Before
    public void setUp() throws Exception {
        ProductDAO productDAO = mock(ProductDAO.class);
        CouponDAO couponDAO = mock(CouponDAO.class);
        OrderDAO orderDAO = mock(OrderDAO.class);

        when(productDAO.getById("PROD-001")).thenReturn(Product.newBuilder().setQuantity(10).build());
        when(productDAO.getById("PROD-002")).thenReturn(Product.newBuilder().setQuantity(5).build());
        when(productDAO.getById("PROD-003")).thenReturn(Product.newBuilder().setQuantity(0).build());

        this.transactionLogic = new TransactionLogic(orderDAO, productDAO, couponDAO);
    }

    @Test
    public void validateOrder() throws Exception {
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().build()));
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").build()));
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").setEmail("Email").build()));
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").setEmail("Email").setAddress("Address").build()));
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").setAddress("Address").setPhone("00008881234").build()));
        assertFalse(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").setEmail("Email").setPhone("00008881234").build()));
        assertTrue(this.transactionLogic.validateOrder(Order.newBuilder().setName("Customer Name").setEmail("Email").setAddress("Address").setPhone("00008881234").build()));
    }

    @Test
    public void validateProduct() throws Exception {
        assertFalse(this.transactionLogic.validateProduct(Order.newBuilder().build()));
        assertFalse(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-004")).build()));
        assertFalse(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-001").setQuantity(11)).build()));
        assertTrue(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-001").setQuantity(10)).build()));
        assertFalse(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-001").setQuantity(10)).addProduct(Product.newBuilder().setId("PROD-004")).build()));
        assertFalse(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-001").setQuantity(10)).addProduct(Product.newBuilder().setId("PROD-002").setQuantity(9)).build()));
        assertTrue(this.transactionLogic.validateProduct(Order.newBuilder().addProduct(Product.newBuilder().setId("PROD-001").setQuantity(10)).addProduct(Product.newBuilder().setId("PROD-002").setQuantity(2)).build()));
    }

}