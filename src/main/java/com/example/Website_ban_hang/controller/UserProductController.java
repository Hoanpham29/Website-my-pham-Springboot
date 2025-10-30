package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.*;
import com.example.Website_ban_hang.repository.OrderDetailRepository;
import com.example.Website_ban_hang.service.ContactService;
import com.example.Website_ban_hang.service.OrderService;
import com.example.Website_ban_hang.service.ProductService;
import com.example.Website_ban_hang.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public void SplitPage(int page,
                          Model model,
                          List<Product> inputProduct)
    {
        int pageSize = 24;
        int totalProducts = inputProduct.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        List<Product> products = inputProduct.subList(start, end);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

    @GetMapping
    public String showHomePage(@ModelAttribute("user") User user,
                               @RequestParam(defaultValue = "0") int page,
                               Model model,
                               HttpSession session)
    {
        if(session.getAttribute("user")==null)
            return "redirect:/guest";

        List<Product> inputProduct = service.getAllProducts();
        SplitPage(page,model,inputProduct);

        if(user == null)
            return "redirect:/";

        return "user/product/home";
    }

    @GetMapping("/lien-he")
    public String contact(Model model) {
        model.getAttribute("user");
        model.addAttribute("lienHe", new Contact());
        return "user/lien-he";
    }

    @PostMapping("/lien-he")
    public String submitContact(@ModelAttribute Contact contact, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            contact.setUser(user);
        }

        contactService.save(contact);
        return "user/lien-he";
    }

    @GetMapping("/tim-kiem")
    public String searchProducts(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam("query") String query,
                                 Model model)
    {
        List<Product> inputProducts = service.getProductBySearch(query);

        SplitPage(page,model,inputProducts);

        model.addAttribute("query", query);
        return "/user/tim-kiem";
    }

    @GetMapping("/don-hang")
    public String xemDonHang(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest";
        }

        List<Order> orders = orderService.getOrdersByUser(user);

        for (Order o : orders) {
            List<OrderDetail> details = orderDetailRepository.findByOrder(o);
            o.setChiTietDonHang(details);
        }

        model.addAttribute("orders", orders);
        return "user/product/don-hang";
    }

    @GetMapping("/product/san-pham-moi")
    public String showNewProduct(@RequestParam(defaultValue = "0") int page,Model model)
    {
        List<Product> inputProduct = service.getNewProduct();

        SplitPage(page,model,inputProduct);

        model.addAttribute("san_pham_moi","Sản phẩm mới");
        return "user/product/danh-muc";
    }

    @GetMapping("/product/giam-gia")
    public String productDiscount(@RequestParam(defaultValue = "0") int page, Model model)
    {
        List<Product> inputProduct = service.getDiscountProduct();

        SplitPage(page,model,inputProduct);

        model.addAttribute("giam_gia","Sản phẩm ưu đãi");
        return "user/product/danh-muc";
    }

    @GetMapping("product/ban-chay")
    public String productTopSeller(@RequestParam(defaultValue = "0") int page, Model model)
    {
        List<Product> inputProduct = service.getTopSeller();

        SplitPage(page, model, inputProduct);

        model.addAttribute("ban_chay","Bán chạy");
        return "user/product/danh-muc";
    }

    @GetMapping("/product/phan-loai")
    public String productByCategory(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam("query") String phanloai,
                                    Model model)
    {
        List<Product> inputProduct = service.getProductByCategory(phanloai);

        SplitPage(page, model, inputProduct);

        model.addAttribute("phanloai",phanloai);

        return "user/product/danh-muc";
    }

    @PostMapping("/sua-thong-tin")
    public String updateUserInfo(@RequestParam("hoTen") String hoTen,
                                 @RequestParam("sdt") String sdt,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/guest";

        user.setHoTen(hoTen);
        user.setSdt(sdt);

        userService.updateUser(user);
        session.setAttribute("user", user);

        return "redirect:/user";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable("id") int id,
                                Model model,
                                HttpSession session) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);

        model.addAttribute("session", session);

        return "user/product/chi-tiet";
    }

}
