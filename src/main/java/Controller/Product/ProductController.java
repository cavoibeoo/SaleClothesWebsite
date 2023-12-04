package Controller.Product;

import Service.CategoryService;
import Service.ImageService;
import Service.ProductService;
import Service.impl.CategoryServiceImpl;
import Service.impl.ImageServiceImpl;
import Service.impl.ProductServiceImpl;
import model.ProductEntity;
import model.Review;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product", name = "ProductListServlet")
public class ProductController extends HttpServlet {
    
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    ImageService imageService = new ImageServiceImpl();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("getShop")){
            getShop(req, resp);
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        }
        
        else if (action.equalsIgnoreCase("getDetails")){
            getDetails(req, resp);
            req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
        }
    }
    
    protected void getShop(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException,ServletException{
        try {
            List<ProductEntity> productList = productService.findAllDistinct();
            HttpSession session = req.getSession();
            session.setAttribute("productList", productList);
        }catch (Exception ex){
            ex.printStackTrace();
            req.setAttribute("error","Error: "+ ex.getMessage());
        }
    }
    
    protected void getDetails(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException,ServletException{
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            ProductEntity productEntity = productService.findById(productId);
            if (productEntity != null){
                req.setAttribute("product", productEntity);
                ReviewController.getReview(req, resp);
            }
            else {
                String message = "Error item not found>";
                req.setAttribute("error",message);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            req.setAttribute("error","Error: "+ ex.getMessage());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
