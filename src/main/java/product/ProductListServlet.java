package product;

import entity.ProductsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/productList", name = "ProductListServlet")
public class ProductListServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        doPost_GetShop(req, resp);
        
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }
    
    protected void doPost_GetShop(HttpServletRequest req, HttpServletResponse resp){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        // Call the named query
        TypedQuery<ProductsEntity> query = entityManager.createNamedQuery("ListAllProduct", ProductsEntity.class);
        List<ProductsEntity> productList = query.getResultList();
        
        HttpSession session = req.getSession();
        
        session.setAttribute("productList", productList);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
