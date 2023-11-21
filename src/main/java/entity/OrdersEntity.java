package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "clothesstore", catalog = "")
@NamedQuery(name = "getCustomerOrders", query = "select e from OrdersEntity e where e.customerId = ?1")
public class OrdersEntity {
    private int orderId;
    private int customerId;
    private int totalAmount;
    private Timestamp date;
    private byte status;
    private BigDecimal discount;
    private byte isCancel;
    
    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    @Basic
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    @Basic
    @Column(name = "total_amount")
    public int getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }
    
    public void setStatus(byte status) {
        this.status = status;
    }
    
    @Basic
    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }
    
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    
    @Basic
    @Column(name = "isCancel")
    public byte getIsCancel() {
        return isCancel;
    }
    
    public void setIsCancel(byte isCancel) {
        this.isCancel = isCancel;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && customerId == that.customerId && totalAmount == that.totalAmount && status == that.status && isCancel == that.isCancel && Objects.equals(date, that.date) && Objects.equals(discount, that.discount);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, totalAmount, date, status, discount, isCancel);
    }
}
