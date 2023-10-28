import entity.CustomerEntity;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            //------INSERT------
//            CustomerEntity quang = new CustomerEntity();
//            quang.setAddress("Le Van Viet, HCM");
//            quang.setCustomerId(3);
//            quang.setFirstName("Quang");
//            quang.setLastName("Tran");
//            quang.setPhoneNumber("0355305120");
//            entityManager.persist(quang);


            -------ExecuteQueryWrittren-------
            transaction.begin();

            TypedQuery<CustomerEntity> CustomerByFirstName = entityManager.createNamedQuery("CustomerbyFirstname", CustomerEntity.class);
            CustomerByFirstName.setParameter(1, "Quang");
            for (CustomerEntity customerEntity : CustomerByFirstName.getResultList()) {
                System.out.println(customerEntity);
            }

            //--------ExecuteSQLQUery------
//              transaction.begin();
//
//              Query countQuang = entityManager.createNativeQuery("SELECT COUNT(*) AS QuangCount FROM customer WHERE firstName =:customername");
//              countQuang.setParameter("customername", "Quang");
//              System.out.println("Có tất cả " + countQuang.getSingleResult() + " Thằng khách tên Quang");



            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();

        }
    }
}