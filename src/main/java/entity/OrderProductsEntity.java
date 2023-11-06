package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_products", schema = "clothesstore", catalog = "")
@IdClass(OrderProductsEntityPK.class)
public class OrderProductsEntity {
    private int orderId;
    private int productId;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal total;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "unit_price")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "total")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsEntity that = (OrderProductsEntity) o;
        return orderId == that.orderId && productId == that.productId && quantity == that.quantity && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, unitPrice, quantity, total);
    }
}
