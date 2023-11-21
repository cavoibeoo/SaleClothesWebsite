package product;

import entity.CustomerProductEntity;
import entity.CustomerProductEntityPK;
import entity.CustomeraccountEntity;
import entity.OrdersEntity;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost_GetOrders(req, resp);
        
        String action = req.getParameter("action");
        if (action != null){
            if (action.equalsIgnoreCase("create")){
                doPost_CreateOrder(req, resp);
            }
            else if (action.equalsIgnoreCase("cancel")){
                doPost_CancelOrder(req, resp);
            }
        }
        doPost_DisplayOrders(req, resp);
    }
    
    protected void doPost_GetOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
    
        CustomeraccountEntity customer = (CustomeraccountEntity) session.getAttribute("user");
        TypedQuery<OrdersEntity> query = entityManager.createNamedQuery("getCustomerOrders", OrdersEntity.class);
        query.setParameter(1,customer.getCustomerId());
        List<OrdersEntity> customerOrders = query.getResultList();
        session.setAttribute("customerOrders", customerOrders);
    }
    
    protected void doPost_DisplayOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        doPost_GetOrders(req,resp);
        List<OrdersEntity> customerOrders = (List<OrdersEntity>) session.getAttribute("customerOrders");
    
        String url = "/CustomerAccount.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }
    
    protected void doPost_CreateOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        String checkedProducts[] = req.getParameterValues("checkedProduct");
    
        for (int i=0; i<checkedProducts.length; i++){
            CustomerProductEntityPK pk = new CustomerProductEntityPK();
            pk.setCustomerId(user.getCustomerId());
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
    }
    
    protected void doPost_CancelOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
