package Controller;

import Service.CategoryService;
import Service.ImageService;
import Service.ProductService;
import Service.impl.CategoryServiceImpl;
import Service.impl.ImageServiceImpl;
import Service.impl.ProductServiceImpl;
import model.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin-product","/admin-product-create","/admin-product-update","/admin-product-delete","/admin-product-edit","/admin-product-disable","/admin-product-enable","/admin-product-search"})
public class ProductController extends HttpServlet {
    
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    ImageService imageService = new ImageServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = request.getRequestURL().toString();
        ProductEntity product = null;
        if (url.contains("create")) {
            findCategory(request, response);
            findColor(request, response);
            findProductSize(request,response);
            request.getRequestDispatcher("/views/admin/add-product.jsp").forward(request, response);
        } else if (url.contains("disable")) {
            disable(request, response);
        } else if (url.contains("enable")) {
            enable(request, response);
        } else if (url.contains("edit")) {
            edit(request, response);
        } else if (url.contains("update")) {
            update(request, response);
        } else if (url.contains("delete")) {
            delete(request,response);
        } else if (url.contains("search")) {
            search(request,response);
            findSearchSize(request,response);
            request.getRequestDispatcher("/views/admin/result-product.jsp").forward(request, response);
        }
        findAll(request,response);
        findSize(request,response);
        request.setAttribute("tag","product");
        request.getRequestDispatcher("/views/admin/product.jsp").forward(request,response);
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
        else if(url.contains("delete"))
        {
            delete(request,response);
        }
        else if (url.contains("search")) {
            search(request,response);
        }
        findAll(request,response);
        findSize(request,response);
        request.getRequestDispatcher("/views/admin/product.jsp").forward(request,response);
    }
    protected void insert(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            int flag=0;
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            ProductEntity product = new ProductEntity();
            BeanUtils.populate(product,request.getParameterMap());
            String categoryId = request.getParameter("categoryId");
            String colorId = request.getParameter("colorId");
            String sizeId = request.getParameter("sizeId");
            String productName=request.getParameter("productName");
            CategoryEntity category = categoryService.findById(Integer.parseInt(categoryId));
            ColorEntity color = productService.findColorsById(Integer.parseInt(colorId));
            SizeEntity size = productService.findSizeById(Integer.parseInt(sizeId));
            List<ProductEntity> relatedProducts=productService.findByName(productName);
            ImageEntity image1 = new ImageEntity();
            ImageEntity image2 = new ImageEntity();
            Part filepart1 = request.getPart("ImageFile1");
            Part filepart2 = request.getPart("ImageFile2");
            InputStream fileContent1= filepart1.getInputStream();
            InputStream fileContent2=filepart2.getInputStream();
            String image1Data= Base64.getEncoder().encodeToString(fileContent1.readAllBytes());
            String image2Data = Base64.getEncoder().encodeToString(fileContent2.readAllBytes());
            image1.setProductImage(image1Data);
            image2.setProductImage(image2Data);
            List<ImageEntity> imageEntities = new ArrayList<>();
            imageEntities.add(image1);
            imageEntities.add(image2);
            if(relatedProducts != null) {
                for (ProductEntity item : relatedProducts) {
                    if (color.getColorId() == item.getColor().getColorId() && size.getSizeId() == item.getSize().getSizeId()) {
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag==0) {
                product.setImages(imageEntities);
                product.setCategory(category);
                product.setColor(color);
                product.setSize(size);
                product.setActivated(true);
                image1.setProduct(product);
                image2.setProduct(product);
                productService.insert(product);
                imageService.insert(image1);
                imageService.insert(image2);
                request.setAttribute("success", "Adding successfully");
            }
            else {
                request.setAttribute("error","Existed product");
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ ex.getMessage());
        }
    }
    protected void delete(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        try{
            String productId=request.getParameter("productId");
            ProductEntity product = productService.findById(Integer.parseInt(productId));
            for(ImageEntity imageEntity :product.getImages())
            {
                imageService.delete(imageEntity.getImageId());
            }
            productService.delete(Integer.parseInt(productId));
            request.setAttribute("success","Delete successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void findAll(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            String index = request.getParameter("index");
            if(index==null)
            {
                index="0";
            }
            int count = productService.count();
            int endPage=count/6;
            if(endPage%6!=0)
            {
                endPage++;
            }
            List<ProductEntity> productEntityList = productService.findAllPage(Integer.parseInt(index));
            request.setAttribute("index",Integer.parseInt(index));
            request.setAttribute("products",productEntityList);
            request.setAttribute("endPage",endPage);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void search(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String index = request.getParameter("index");
            String keyword = request.getParameter("keyword");
            if(index==null)
            {
                index="0";
            }
            int count = productService.countSearch(keyword);
            int endPage=count/6;
            if(endPage%6!=0)
            {
                endPage++;
            }
            List<ProductEntity> productEntityList = productService.search(keyword,Integer.parseInt(index));
            request.setAttribute("index",Integer.parseInt(index));
            request.setAttribute("products",productEntityList);
            request.setAttribute("endPage",endPage);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error:"+ex.getMessage());
        }
    }
    protected void findSize(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException {
        try{
            int size = productService.count();
            request.setAttribute("size",size);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void findSearchSize(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String keyword = request.getParameter("keyword");
            int size = productService.countSearch(keyword);
            request.setAttribute("size",size);
            
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void edit(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String productId = request.getParameter("productId");
            ProductEntity product = productService.findById(Integer.parseInt(productId));
            findCategory(request, response);
            findColor(request,response);
            findProductSize(request,response);
            request.setAttribute("product",product);
            request.getRequestDispatcher("views/admin/update-product.jsp").forward(request,response);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error"+ex.getMessage());
        }
    }
    protected void disable(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String categoryId = request.getParameter("productId");
            ProductEntity product = productService.findById(Integer.parseInt(categoryId));
            product.setActivated(false);
            productService.update(product);
            request.setAttribute("success","Disable successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void enable(HttpServletRequest request,HttpServletResponse response) throws  IOException,ServletException{
        try{
            String categoryId = request.getParameter("productId");
            ProductEntity product = productService.findById(Integer.parseInt(categoryId));
            product.setActivated(true);
            productService.update(product);
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
            ProductEntity product = productService.findById(Integer.parseInt(request.getParameter("productId")));
            BeanUtils.populate(product, request.getParameterMap());
            String categoryId = request.getParameter("categoryId");
            CategoryEntity category = categoryService.findById(Integer.parseInt(categoryId));
            Part filepart1 = request.getPart("ImageFile1");
            Part filepart2 = request.getPart("ImageFile2");
            List<ImageEntity> imageEntities = product.getImages();
            if(filepart1.getSubmittedFileName()!="")
            {
                ImageEntity image1 = imageService.findById(product.getImages().get(0).getImageId());
                InputStream fileContent1= filepart1.getInputStream();
                String imageData1= Base64.getEncoder().encodeToString(fileContent1.readAllBytes());
                image1.setProductImage(imageData1);
                imageEntities.set(0,image1);
                imageService.update(image1);
            }
            if(filepart2.getSubmittedFileName()!="")
            {
                ImageEntity image2 = imageService.findById(product.getImages().get(1).getImageId());
                InputStream fileContent2= filepart2.getInputStream();
                String imageData2= Base64.getEncoder().encodeToString(fileContent2.readAllBytes());
                image2.setProductImage(imageData2);
                imageEntities.set(1,image2);
                imageService.update(image2);
            }
            product.setImages(imageEntities);
            productService.update(product);
            request.setAttribute("product",product);
            request.setAttribute("success", "Update successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Error: " + ex.getMessage());
        }
    }
    protected void findCategory(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        try{
            List<CategoryEntity> categoryEntityList = categoryService.findAllByActivated();
            request.setAttribute("categories",categoryEntityList);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void findColor(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        try{
            List<ColorEntity> colorEntityList = productService.findAllColors();
            request.setAttribute("colors",colorEntityList);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
    protected void findProductSize(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        try{
            List<SizeEntity> sizeEntityList = productService.findAllSizes();
            request.setAttribute("sizes",sizeEntityList);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("error","Error: "+ex.getMessage());
        }
    }
}
