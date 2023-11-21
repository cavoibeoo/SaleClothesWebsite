package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "clothesstore", catalog = "")
@NamedQuery(name = "ListAllProduct", query = "select e from ProductsEntity e")
@NamedQuery(name = "FindProduct", query = "select e from ProductsEntity e where e.productId=?1")
public class ProductsEntity {
    private int productId;
    private String productName;
    private int unitPrice;
    private int inventoryLeft;
    private int styleId;
    private int categoryId;
    
    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Basic
    @Column(name = "unit_price")
    public int getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @Basic
    @Column(name = "inventory_left")
    public int getInventoryLeft() {
        return inventoryLeft;
    }
    
    public void setInventoryLeft(int inventoryLeft) {
        this.inventoryLeft = inventoryLeft;
    }
    
    @Basic
    @Column(name = "style_id")
    public int getStyleId() {
        return styleId;
    }
    
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    
    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return productId == that.productId && unitPrice == that.unitPrice && inventoryLeft == that.inventoryLeft && styleId == that.styleId && categoryId == that.categoryId && Objects.equals(productName, that.productName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, unitPrice, inventoryLeft, styleId, categoryId);
    }
}
