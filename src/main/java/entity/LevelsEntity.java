package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "levels", schema = "clothesstore", catalog = "")
public class LevelsEntity {
    private int levelId;
    private BigDecimal discount;
    private BigDecimal totalPayment;

    @Id
    @Column(name = "level_id")
    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
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
    @Column(name = "total_payment")
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelsEntity that = (LevelsEntity) o;
        return levelId == that.levelId && Objects.equals(discount, that.discount) && Objects.equals(totalPayment, that.totalPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelId, discount, totalPayment);
    }
}
