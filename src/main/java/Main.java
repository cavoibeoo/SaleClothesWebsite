import entity.*;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            String checkedProducts[] = new String[] {"3"};
            
            for (int i=0; i<checkedProducts.length; i++){
                CustomerProductEntityPK pk = new CustomerProductEntityPK();
                pk.setCustomerId(1);
                pk.setProductId(Integer.parseInt(checkedProducts[i]));
                CustomerProductEntity product = entityManager.find(CustomerProductEntity.class, pk);
                if (product != null){
                    byte stat = 1;
                    product.setStatus(stat);
                    transaction.begin();
                    entityManager.merge(product);
                    transaction.commit();
                }
            }
            transaction.begin();
            Query query = entityManager.createNativeQuery("CALL PROC_DeleteBoughtInCart(:param1)");
            query.setParameter("param1", 1);
            query.executeUpdate();
            transaction.commit();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}