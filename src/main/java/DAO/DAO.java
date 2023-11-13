package DAO;

import entity.ProductsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private SessionFactory sessionFactory;
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<ProductsEntity> getAllProduct() {
        Session session = sessionFactory.openSession();

        List<ProductsEntity> list = new ArrayList<>();
        TypedQuery<ProductsEntity> ListAllProduct = entityManager.createNamedQuery("ListAllProduct", ProductsEntity.class);
        try {
            for (ProductsEntity productsEntity : ListAllProduct.getResultList()) {
                list.add(productsEntity);
            }
            System.out.println(list);
        }catch (Exception e) {

        }
        return list;
    }
}
