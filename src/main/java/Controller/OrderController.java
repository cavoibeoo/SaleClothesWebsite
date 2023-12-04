package Controller;

import Service.CategoryService;
import Service.OrderService;
import Service.impl.CategoryServiceImpl;
import Service.impl.OrderServiceImpl;
import model.CategoryEntity;
import model.OrderEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.Order;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin-order","/admin-category-accept","/admin-category-delete"})
public class OrderController extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getRequestURL().toString();
        if (url.contains("accept")) {
            accept(request, response);
        }
        else if (url.contains("delete"))
        {
            delete(request,response);
        }
        findAll(request, response);
        findSize(request, response);
        request.setAttribute("tag", "order");
        request.getRequestDispatcher("/views/admin/order.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
    protected void findAll(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            List<OrderEntity> orderEntityList = orderService.findAll();
            request.setAttribute("orders",orderEntityList);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void findSize(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            int size = orderService.count();
            request.setAttribute("size",size);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void accept(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        try{
            OrderEntity order = orderService.findById(Integer.parseInt(request.getParameter("orderId")));
            Date orderDate = order.getOrderDate();
            LocalDate localOrderDate = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate orderDeliveryDate = localOrderDate.plusDays(5);
            Date deliveryDate = Date.from(orderDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            order.setOrderDeliveryDate(deliveryDate);
            order.setAccepted(true);
            orderService.update(order);
            request.setAttribute("success","Accept Order Successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        try{
            orderService.delete(Integer.parseInt(request.getParameter("orderId")));
            request.setAttribute("success","Delete Order Successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
}
