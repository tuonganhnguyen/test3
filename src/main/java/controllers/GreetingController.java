package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domains.Employee;;

/**
 * @author anhtuongnguyen
 *
 */
@RestController
@RequestMapping(value = "/employees")
public class GreetingController {

    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");
    /**
     * 
     */
    public GreetingController() {
        System.out.println("GreetingController");
    }

    /**
     * 
     * @return String
     */
    @RequestMapping(value = "/english", method = RequestMethod.GET, produces = "application/json")
    public String hello(HttpServletResponse response, HttpSession session) {
        System.out.println("GreetingController hello");
        System.out.println("CSRF token:" + session.getAttribute(DEFAULT_CSRF_TOKEN_ATTR_NAME));
        
//        response.addHeader("X-CSRF-TOKEN", session.getAttribute(DEFAULT_CSRF_TOKEN_ATTR_NAME).toString());
        return "Hello, how are you?";

    }

    /**
     * 
     * @return List<Employee>
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Employee> listEmployees() {
        List<Employee> empList = new ArrayList<Employee>();

        Employee firstEmployee = new Employee();
        firstEmployee.setCellphone("111111");
        firstEmployee.setEmail("test@gmail.com");
        firstEmployee.setName("test");

        empList.add(firstEmployee);

        return empList;
    }

    /**
     * @return List<Employee>
     */
    @RequestMapping(value = "/xml", method = RequestMethod.GET, produces = "application/xml")
    public List<Employee> listEmployeesAsPdf() {
        List<Employee> empList = new ArrayList<Employee>();

        Employee firstEmployee = new Employee();
        firstEmployee.setCellphone("111111");
        firstEmployee.setEmail("test@gmail.com");
        firstEmployee.setName("test");

        empList.add(firstEmployee);

        return empList;
    }
    
    
    public void testData() {
        
    }
}


