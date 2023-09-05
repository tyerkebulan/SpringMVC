package javaBootcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping()
    public String mainPage(@RequestParam(required = false,value = "name") String name,
                           @RequestParam(required = false,value = "age") String age) {
        System.out.println(name + "  " + age);
        return "first";
    }
}
