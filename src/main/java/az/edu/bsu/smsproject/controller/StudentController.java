package az.edu.bsu.smsproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping( value = {"/", "/home", "/index"})
    public ModelAndView showIndexPage(){
        ModelAndView mav = new ModelAndView("demo");
        mav.addObject("sth", 5);
        return mav;
    }



}
