package entitites;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    private Long id;
    private Long productId;
    private Long OrderId;

    public OrderDetail(Long id, Long productId, Long orderId) {
        this.id = id;
        this.productId = productId;
        OrderId = orderId;
    }

    public OrderDetail(Long productId, Long orderId) {
        this.productId = productId;
        OrderId = orderId;
    }
    public OrderDetail() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
