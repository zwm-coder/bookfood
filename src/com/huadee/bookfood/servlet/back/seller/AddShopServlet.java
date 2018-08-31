package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.service.back.ShopService;
import com.huadee.bookfood.utils.UploadFile;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddShopServlet", urlPatterns = "/back/seller/shop/apply/*")
public class AddShopServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null){
            if (status.equals("step1")){
                path = this.getTextInfo(request);
            } else if (status.equals("step2")){
                path = this.getImgInfo(request);
            } else if (status.equals("commit")){
                path = this.commitAdd(request);
            }
        }

        request.getRequestDispatcher(path).forward(request, response);

    }

    public String getTextInfo(HttpServletRequest request){
        String path = "/pages/errors.jsp";
        String shop_name = request.getParameter("shop_name");
        String contact = request.getParameter("contact");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String descp = request.getParameter("desc");
        HttpSession session = request.getSession(true);

        if (ValidateUtil.validateEmpty(shop_name) && ValidateUtil.validateEmpty(contact) && ValidateUtil.validateEmpty(phone) && ValidateUtil.validateEmpty(address)){
            // 数据验证不可为空
            Shop shop = new Shop();
            shop.setShop_name(shop_name);
            shop.setContact(contact);
            shop.setPhone(phone);
            shop.setAddress(address);
            shop.setDesc(descp);
            session.setAttribute("applyShop", shop);
            path = "/pages/back/seller/shop_add_step2.jsp";
        }

        return path;
    }

    public String getImgInfo(HttpServletRequest request){
        String path = "/pages/errors.jsp";
        HttpSession session = request.getSession(true);
        Shop shop = (Shop)session.getAttribute("applyShop");
        if (shop == null){
            return "/pages/back/seller/shop_add.jsp";
        }

        String img_type = request.getParameter("img_type");

        System.out.println(img_type);

        if (ValidateUtil.validateEmpty(img_type)){
            Date current_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(current_date);

            String fileName = img_type + "_" + shop.getShop_name() + "_" + date + ".jpg";
            UploadFile.uploadImg(request, fileName);
            if (img_type.equals("id_card")){
                shop.setId_card(fileName);
            } else if (img_type.equals("business_license")){
                shop.setBusiness_license(fileName);
            } else if (img_type.equals("cater_permit")){
                shop.setCater_permit(fileName);
            } else if(img_type.equals("shop_out_photo")){
                shop.setShop_out_photo(fileName);
            }
            session.setAttribute("applyShop", shop);
            path = "/pages/back/seller/shop_add_step2.jsp";
        }
        return path;
    }

    public String commitAdd(HttpServletRequest request){
        String path = "/pages/errors.jsp";

        HttpSession session = request.getSession(true);
        Shop shop = (Shop)session.getAttribute("applyShop");
        Seller seller = (Seller)session.getAttribute("seller");
        if (shop != null){
            shop.setSeller_id(seller.getSeller_id());
            if (ShopService.createShop(shop)){
                path = "/back/seller/shop/list?seller_id="+seller.getSeller_id();
            }
        }

        return path;
    }
}
