package product;

import entity.ProductsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;
import javax.persistence.EntityTransaction;

@WebServlet(urlPatterns = "/productList", name = "ProductListServlet", loadOnStartup = 1)
public class ProductListServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        // Call the named query
        TypedQuery<ProductsEntity> query = entityManager.createNamedQuery("ListAllProduct", ProductsEntity.class);
        List<ProductsEntity> productList = query.getResultList();

        transaction.commit();
        getServletContext().setAttribute("productList", productList);

        String url = "/Home.jsp";
    }
}