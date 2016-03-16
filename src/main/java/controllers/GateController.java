package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value="/")
public class GateController {
    
    public GateController() {
        System.out.println("GateController init 1");
    }
    
    @RequestMapping(value = "/welcome")
    public String login(HttpServletRequest request) {
        System.out.println("GateController login 2");
        //ModelAndView test = new ModelAndView("login");
        //return test;
        return "login";
    }
    
    
    @RequestMapping(value = "/error")
    public String returnToErrorPage(HttpServletRequest request) {
        System.out.println("GateController login 2");
        //ModelAndView test = new ModelAndView("login");
        //return test;
        return "error";
    }

}
