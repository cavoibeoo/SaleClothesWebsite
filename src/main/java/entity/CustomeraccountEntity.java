package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NamedQuery(name = "CheckLogin", query = "select e from CustomeraccountEntity e where e.mail = ?1 and e.pwd = ?2")
@Table(name = "customeraccount", schema = "clothesstore")
public class CustomeraccountEntity {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    private int customerId;
    private String mail;
    private String pwd;
    private BigDecimal totalPayment;

    @Id
    @Column(name = "customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
        CustomeraccountEntity that = (CustomeraccountEntity) o;
        return customerId == that.customerId && Objects.equals(mail, that.mail) && Objects.equals(pwd, that.pwd) && Objects.equals(totalPayment, that.totalPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, mail, pwd, totalPayment);
    }

    public boolean CheckLogin() {
        try {

            transaction.begin();
            TypedQuery<CustomeraccountEntity> Result = entityManager.createNamedQuery("CheckLogin", CustomeraccountEntity.class);
            Result.setParameter(1, this.mail);
            Result.setParameter(2, this.pwd);
            if (Result.getResultList().stream().count() != 0)
                return true;
            transaction.commit();
            return false;
        }catch (Exception exception) {
            return false;
        }

    }


}
