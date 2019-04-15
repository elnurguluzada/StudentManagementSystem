package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    private final CommonService commonService;
    private final StudentService studentService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public TutorController(StudentService studentService, CommonService commonService) {
        this.studentService = studentService;
        this.commonService = commonService;
    }

    @GetMapping("/addStudent")
    public ModelAndView showStudentForm(){
        ModelAndView modelAndView = new ModelAndView("/Tutor/addStudent");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }


    @PostMapping("/addStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, Errors errors){
        if ( errors.hasErrors() ){
            System.out.println("There are errors");
            return "Tutor/addStudent";
        }
        System.out.println("No errors");
        System.out.println(student);

        return "redirect:/addStudent";
    }

    public List<String> getSections(
            @RequestParam(name = "selectedYear") int year
    ){
        List<String> sectionList = commonService.getSectionList( year );

        return sectionList;
    }




}
