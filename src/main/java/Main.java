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

        CustomerEntity cus1 = entityManager.find(CustomerEntity.class, 2);
        String address = cus1.getAddress();
        String phonnum = cus1.getPhoneNumber();
        String firstname = cus1.getFirstName();
        String lastname = cus1.getLastName();

        System.out.println(address);
        System.out.println(phonnum);
        System.out.println(firstname);
        System.out.println(lastname);


    }
}