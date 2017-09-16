package com.punyabagus.generalOnlineStore.logic;

import com.punyabagus.generalOnlineStore.dao.CouponDAO;
import com.punyabagus.generalOnlineStore.dao.OrderDAO;
import com.punyabagus.generalOnlineStore.dao.ProductDAO;
import com.punyabagus.generalOnlineStore.pojo.CouponData.Coupon;
import com.punyabagus.generalOnlineStore.pojo.OrderData;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Order;
import com.punyabagus.generalOnlineStore.pojo.OrderData.Status;
import com.punyabagus.generalOnlineStore.pojo.ProductData.Product;

import javax.inject.Inject;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Created by prasojo on 9/15/17.
 */
public class TransactionLogic {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private CouponDAO couponDAO;

    @Inject
    public TransactionLogic(OrderDAO orderDAO, ProductDAO productDAO, CouponDAO couponDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.couponDAO = couponDAO;
    }

    /**
     * Add coupon to Order
     * @param order
     * @return
     */
    public Order addCoupon(Order order) {
        // Check first if order had coupon
        if (order.hasCoupon() && order.getCoupon().hasCode()) {
            // Check validity of coupon
            Coupon coupon = (Coupon) couponDAO.getByCode(order.getCoupon().getCode());
            long currentTime = Instant.now().getEpochSecond();

            if (coupon != null && coupon.getQuantity() > 0
                    && coupon.getEndDate() > currentTime && coupon.getStartDate() < currentTime) {
                // If order exist if create new one
                Order existingOrder = order.hasId() ? (Order) orderDAO.getById(order.getId()) : getBlankOrder();
                // Only add coupon if order status still NEW
                if (!existingOrder.getStatus().equals(Status.NEW)) {
                    return null;
                }
                // Set/replace coupon then update
                return (Order) orderDAO.update(existingOrder.toBuilder().setCoupon(coupon).build());
            }
        }

        return null;
    }

    /**
     * Add product to related order/newly created one
     * @param order
     * @return
     */
    public Order addProduct(Order order) {
        // Check if product exist or request not valid and return null
        if (order.getProductCount() > 0) {
            // Ordered Product from request
            Product orderedProduct = order.getProduct(0);
            // Stock Product from product dictionary
            Product stockProduct = (Product) productDAO.getById(orderedProduct.getId());
            // Stock Product should had at least same quantity with ordered request
            if (stockProduct != null && stockProduct.getQuantity() >= orderedProduct.getQuantity()) {
                Order existingOrder = order.hasId() ? (Order) orderDAO.getById(order.getId()) : getBlankOrder();
                // Check if product already consist in existing order
                if (existingOrder.getProductCount() > 0) {
                    if (existingOrder.getProductList().stream().anyMatch(p -> p.getCode().equals(orderedProduct.getCode()))) {
                        return null;
                    }
                }
                // Only update if status still NEW
                if (!existingOrder.getStatus().equals(Status.NEW)){
                    return null;
                }
                return (Order) orderDAO.update(existingOrder.toBuilder().addProduct(orderedProduct).build());
            }
        }

        return null;
    }

    /**
     * Get Order Details
     * @param orderId
     * @return
     */
    public Order getOrderDetails(String orderId) {
        Order result = (Order) orderDAO.getById(orderId);
        return result != null ? result : Order.newBuilder().build();
    }

    /**
     * Get Status for an Order
     * @param orderId
     * @return
     */
    public Status getOrderStatus(String orderId) {
        Order result = (Order) orderDAO.getById(orderId);
        return result != null ? result.getStatus() : null;
    }

    /**
     * Remove any coupon from an order
     * @param orderId
     * @return
     */
    public boolean removeCoupon(String orderId) {
        // Check if order is exist
        Order existingOrder = (Order) orderDAO.getById(orderId);

        // Remove coupon and update order
        if (existingOrder != null && existingOrder.hasCoupon() && existingOrder.getStatus().equals(Status.NEW)) {
            orderDAO.update(existingOrder.toBuilder().clearCoupon().build());

            return true;
        }

        return false;
    }

    /**
     * Remove product from existing order
     * @param orderId
     * @param productId
     * @return
     */
    public boolean removeProduct(String orderId, String productId) {
        Order existingOrder = (Order) orderDAO.getById(orderId);

        // Check if order is exist & contain product
        if (existingOrder != null && existingOrder.getProductCount() > 0
                && existingOrder.getProductList().stream().anyMatch(p -> p.getId().equals(productId))
                && existingOrder.getStatus().equals(Status.NEW)) {
            Order.Builder updatedOrder = existingOrder.toBuilder();
            // Replace product with updated list which current product removed then update
            orderDAO.update(updatedOrder.clearProduct().addAllProduct(existingOrder.getProductList().stream().filter(p -> !p.getId().equals(productId)).collect(Collectors.toList())).build());

            return true;
        }

        // Return false if request is not valid
        return false;
    }

    /**
     * Return new created blank order
     * @return
     */
    private Order getBlankOrder() {
        return (Order) orderDAO.insert(Order.newBuilder()
                .setCreatedDate(Instant.now().getEpochSecond())
                .setStatus(OrderData.Status.NEW)
                .build());
    }
}
