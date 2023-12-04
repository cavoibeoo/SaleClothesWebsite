package Controller;

import Service.CategoryService;
import Service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CategoryEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.IntrospectionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin-category","/admin-category-create","/admin-category-update","/admin-category-edit","/admin-category-disable","/admin-category-enable"})
public class CategoryController extends HttpServlet {

    CategoryService categoryService = new CategoryServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getRequestURL().toString();
        CategoryEntity category = null;
        if(url.contains("disable"))
        {
            disable(request,response);
        }
        if(url.contains("enable"))
        {
            enable(request,response);
        }
        else if(url.contains("edit"))
        {
            edit(request,response);
        }
        else if(url.contains("update"))
        {
            update(request,response);
        }
        findAll(request,response);
        findSize(request,response);
        request.setAttribute("tag","category");
        request.getRequestDispatcher("/views/admin/category.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getRequestURL().toString();
        if(url.contains("create"))
        {
            insert(request,response);
        }
        else if(url.contains("update"))
        {
            update(request,response);
        }
        else if(url.contains("disable"))
        {
            disable(request,response);
        }
        else if(url.contains("enable"))
        {
            enable(request,response);
        }
        else if(url.contains("edit"))
        {
            edit(request,response);
        }
        findAll(request,response);
        findSize(request,response);
        request.getRequestDispatcher("/views/admin/category.jsp").forward(request,response);
    }
    protected void insert(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            CategoryEntity category= new CategoryEntity();
            BeanUtils.populate(category,request.getParameterMap());
            category.setActivated(true);
            categoryService.insert(category);
            request.setAttribute("success","Adding successfully");
        } catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ ex.getMessage());
        }
    }
    protected void findAll(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            List<CategoryEntity> categoryEntityList = categoryService.findAll();
            request.setAttribute("categories",categoryEntityList);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void findSize(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            int size = categoryService.count();
            request.setAttribute("size",size);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void edit(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            CategoryEntity category = categoryService.findById(Integer.parseInt(request.getParameter("categoryId")));
            request.setAttribute("categoryId",category.getCategoryId());
            request.setAttribute("categoryName",category.getCategoryName());
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error"+ex.getMessage());
        }
    }
    protected void disable(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String categoryId = request.getParameter("categoryId");
            CategoryEntity category = categoryService.findById(Integer.parseInt(categoryId));
            category.setActivated(false);
            categoryService.update(category);
            request.setAttribute("success","Disable successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void enable(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String categoryId = request.getParameter("categoryId");
            CategoryEntity category = categoryService.findById(Integer.parseInt(categoryId));
            category.setActivated(true);
            categoryService.update(category);
            request.setAttribute("success","Enable successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void update(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            CategoryEntity category = new CategoryEntity();
            BeanUtils.populate(category, request.getParameterMap());
            categoryService.update(category);
            request.setAttribute("category", category);
            request.setAttribute("message", "Update successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Error: " + ex.getMessage());
        }
    }
}
