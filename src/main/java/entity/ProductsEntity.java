package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "ListAllProduct", query = "select e from ProductsEntity e")
@Table(name = "products", schema = "clothesstore", catalog = "")
public class ProductsEntity {
    private int productId;
    private String productName;
    private int unitPrice;
    private int inventoryLeft;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return productId == that.productId && unitPrice == that.unitPrice && inventoryLeft == that.inventoryLeft && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, unitPrice, inventoryLeft);
    }
}
