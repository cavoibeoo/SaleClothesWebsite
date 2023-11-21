package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SizeProductEntityPK implements Serializable {
    private int sizeId;
    private int productId;
    
    @Column(name = "size_id")
    @Id
    public int getSizeId() {
        return sizeId;
    }
    
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeProductEntityPK that = (SizeProductEntityPK) o;
        return sizeId == that.sizeId && productId == that.productId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sizeId, productId);
    }
}
