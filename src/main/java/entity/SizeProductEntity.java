package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "size_product", schema = "clothesstore", catalog = "")
@IdClass(SizeProductEntityPK.class)
public class SizeProductEntity {
    private int sizeId;
    private int productId;
    
    @Id
    @Column(name = "size_id")
    public int getSizeId() {
        return sizeId;
    }
    
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }
    
    @Id
    @Column(name = "product_id")
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
        SizeProductEntity that = (SizeProductEntity) o;
        return sizeId == that.sizeId && productId == that.productId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sizeId, productId);
    }
}
