package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer_product", schema = "clothesstore", catalog = "")
@NamedQuery(name = "getCustomerCart", query = "select e from CustomerProductEntity e where e.customerId = ?1")
@NamedQuery(name = "getProductIn4", query = "select e from ProductsEntity e where e.productId = ?1")
@NamedQuery(name = "deletePurchased", query = "delete from CustomerProductEntity e where e.status = 1")
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
    
    public int assignUnitPrice(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        int unitPrice = 0;
        // Call the named query
        TypedQuery<ProductsEntity> query = entityManager.createNamedQuery("getProductIn4", ProductsEntity.class);
        query.setParameter(1,this.productId);
        for (ProductsEntity product :  query.getResultList()){
            unitPrice = product.getUnitPrice();
        }
        return unitPrice;
    }
    
    public String assignName(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        String name = "";
        // Call the named query
        TypedQuery<ProductsEntity> query = entityManager.createNamedQuery("getProductIn4", ProductsEntity.class);
        query.setParameter(1,this.productId);
        for (ProductsEntity product :  query.getResultList()){
            name = product.getProductName();
        }
        return name;
    }
    
    
}

