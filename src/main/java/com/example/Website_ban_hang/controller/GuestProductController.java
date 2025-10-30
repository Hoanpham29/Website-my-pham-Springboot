package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.Contact;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.service.ContactService;
import com.example.Website_ban_hang.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/","/guest"})
public class GuestProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ContactService contactService;

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
    public String home(@ModelAttribute("user") User user,
                       @RequestParam(defaultValue = "0") int page,
                       Model model,
                       HttpSession session) {
        List<Product> inputProduct = service.getAllProducts();

        SplitPage(page,model,inputProduct);
        model.getAttribute("user");
        if(user != null)
            return "redirect:/user";
        return "guest/product/home";
    }

    @GetMapping("/lien-he")
    public String contact(Model model) {
        model.addAttribute("lienHe", new Contact());
        return "guest/lien-he";
    }

    @PostMapping("/lien-he")
    public String submitContact(@ModelAttribute Contact contact, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            contact.setUser(user);
        }

        contactService.save(contact);
        return "guest/lien-he";
    }

    @GetMapping("/tim-kiem")
    public String searchProducts(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam("query") String query,
                                 Model model)
    {
        List<Product> inputProducts = service.getProductBySearch(query);

        SplitPage(page,model,inputProducts);
        model.addAttribute("query", query);
        return "/guest/tim-kiem";
    }

    @GetMapping("/product/san-pham-moi")
    public String showNewProduct(@RequestParam(defaultValue = "0") int page,Model model)
    {
        List<Product> inputProduct = service.getNewProduct();

        SplitPage(page,model,inputProduct);

        model.addAttribute("san_pham_moi","Sản phẩm mới");
        return "guest/product/danh-muc";
    }

    @GetMapping("/product/giam-gia")
    public String productDiscount(@RequestParam(defaultValue = "0") int page, Model model)
    {
        List<Product> inputProduct = service.getDiscountProduct();

        SplitPage(page,model,inputProduct);

        model.addAttribute("giam_gia","Sản phẩm ưu đãi");
        return "guest/product/danh-muc";
    }

    @GetMapping("product/ban-chay")
    public String productTopSeller(@RequestParam(defaultValue = "0") int page, Model model)
    {
        List<Product> inputProduct = service.getTopSeller();

        SplitPage(page, model, inputProduct);

        model.addAttribute("ban_chay","Bán chạy");
        return "guest/product/danh-muc";
    }

    @GetMapping("/product/phan-loai")
    public String productByCategory(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam("query") String phanloai,
                                    Model model)
    {
        List<Product> inputProduct = service.getProductByCategory(phanloai);

        SplitPage(page, model, inputProduct);

        model.addAttribute("phanloai",phanloai);

        return "guest/product/danh-muc";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable("id") int id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "guest/product/chi-tiet";
    }
}
