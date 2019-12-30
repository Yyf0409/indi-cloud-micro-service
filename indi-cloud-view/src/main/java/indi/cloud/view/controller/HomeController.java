package indi.cloud.view.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController  extends  BaseController {

    @Value("${auth-center}")
    private String auth_center;

    @GetMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/invoice"})
    public String invoice(HttpServletRequest request) {
        return "invoice";
    }

    @GetMapping({"/login_page"})
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/";
    }




}
