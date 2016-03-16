package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class PageController {
    
    public PageController() {
        // TODO Auto-generated constructor stub
    }
    
    @RequestMapping(value = "/test")
    public String goToTestPage(HttpServletRequest request) {
        System.out.println("PageController goToTestPage");
        //ModelAndView test = new ModelAndView("login");
        //return test;
        return "test";
    }
}
