package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.Service.TutorService;
import az.edu.bsu.smsproject.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    private final CommonService commonService;
    private final StudentService studentService;
    private final TutorService tutorService;

    @Autowired
    public TutorController(StudentService studentService, CommonService commonService, TutorService tutorService) {
        this.studentService = studentService;
        this.commonService = commonService;
        this.tutorService = tutorService;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView showStudentForm(){
        ModelAndView modelAndView = new ModelAndView("/Tutor/addStudent");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent( @Valid @ModelAttribute("student") Student student, Errors errors ){

        ModelAndView modelAndView = new ModelAndView("Tutor/addStudent");

        System.out.println( student );

        if ( !errors.hasErrors() ){

            if ( tutorService.addStudent( student ) )
                modelAndView.addObject("success", true);
            else
                modelAndView.addObject("success", false);
        }
        return modelAndView;
    }

    @PostMapping("/getSectionList")
    public List<String> getSections( @RequestParam(name = "selectedYear") int year ){
        List<String> list = commonService.getSectionList( year );
        System.out.println(list);
        return list;
    }

    public List<String> getFaculties( @RequestParam(name = "selectedYear") int year ){
            return commonService.getFacultyList( year );
    }

//-------------------------------------------------------------------------------------------------------------


}
