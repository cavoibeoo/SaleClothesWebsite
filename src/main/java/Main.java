
import JpaConfig.JpaConfig;
import Service.CartItemService;
import Service.OrderDetailsService;
import Service.OrderService;
import Service.ReviewService;
import Service.impl.CartItemServiceImpl;
import Service.impl.OrderDetailServiceImpl;
import Service.impl.OrderServiceImpl;
import Service.impl.ReviewServiceImpl;
import model.*;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        OrderDetailsService orderDetailsService = new OrderDetailServiceImpl();
        CartItemService cartItemService = new CartItemServiceImpl();
        EntityManager entityManager = JpaConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ReviewService reviewService = new ReviewServiceImpl();
        
        CustomerEntity customerEntity = JpaConfig.getEntityManager().find(CustomerEntity.class, 6);
        ProductEntity productEntity = JpaConfig.getEntityManager().find(ProductEntity.class, 3);
        /*Review review = new Review("This is so good",customerEntity, productEntity, 5);
        reviewService.insert(review);*/
        boolean isBuy = reviewService.isBought(customerEntity, productEntity);
        System.out.println(isBuy);
    }
}
