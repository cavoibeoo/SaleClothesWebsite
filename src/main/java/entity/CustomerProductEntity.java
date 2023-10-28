package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_product", schema = "itproject", catalog = "")
@IdClass(CustomerProductEntityPK.class)
public class CustomerProductEntity {
    private int customerId;
    private int productId;
    private int quantity;
    private byte status;

    @Id
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProductEntity that = (CustomerProductEntity) o;
        return customerId == that.customerId && productId == that.productId && quantity == that.quantity && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId, quantity, status);
    }
}
