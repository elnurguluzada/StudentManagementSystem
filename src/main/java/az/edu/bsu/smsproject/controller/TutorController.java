package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.Service.TutorService;
import az.edu.bsu.smsproject.domain.DataTable;
import az.edu.bsu.smsproject.domain.Student;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    private final CommonService commonService;
    private final StudentService studentService;
    private final TutorService tutorService;


    @Autowired
    public TutorController(StudentService studentService, TutorService tutorService, CommonService commonService) {
        this.studentService = studentService;
        this.commonService = commonService;
        this.tutorService = tutorService;
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

//      Elnur
//    @GetMapping("/getStudents")
//    public ModelAndView showStudents() {
//        ModelAndView modelAndView = new ModelAndView("studentList");
//        modelAndView.addObject("students", tutorService.getStudentList());
//        return modelAndView;
//    }

    @GetMapping("/getStudentInfo")
    public ModelAndView showStudentInfo(@RequestParam(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("student-info");
        modelAndView.addObject("student" , tutorService.getStudentById(id));
        return modelAndView;
    }

    @GetMapping("/getStudentsForm")
    public String getStudentsForm(){
        return "studentList";
    }


    @GetMapping("/getStudents")
    public ModelAndView showStudents(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "search[value]") String searchValue
    ) {
        System.out.println(draw);
        System.out.println(start);
        System.out.println(length);
        System.out.println(searchValue);
        DataTable dataTable = new DataTable();

        dataTable.setDraw(draw);

        List<Student> allStudents = tutorService.getStudentList();
        dataTable.setRecordsTotal( allStudents.size() );

        dataTable.setRecordsFiltered( allStudents.size() );

        String[][] data = new String[length][3];
        for (int i=0; i<length; i++){
            data[i][0] = String.valueOf(allStudents.get(i).getId());
            data[i][1] = allStudents.get(i).getName();
            data[i][2] = allStudents.get(i).getSurname();
        }

        dataTable.setData( data );


        Gson gson = new Gson();
        String dataTableJson =gson.toJson(dataTable);

        System.out.println(dataTableJson);

        ModelAndView modelAndView = new ModelAndView("ajaxReceiver");
        modelAndView.addObject("dataTable", dataTableJson);
        return modelAndView;
    }


}
