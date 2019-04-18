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


    @GetMapping("/getStudentsList")
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
        System.out.println("Before");
        List<Student> allStudents = tutorService.getStudentList();
        System.out.println("After");
        dataTable.setRecordsTotal( allStudents.size() );

        dataTable.setRecordsFiltered( allStudents.size() );
        String[][] data = new String[length][25];
        for (int i=0; i<length; i++){
            data[i][0] = String.valueOf(allStudents.get(i).getId());
            data[i][1] = allStudents.get(i).getName();
            data[i][2] = allStudents.get(i).getSurname();
            data[i][3] = allStudents.get(i).getFatherName();
            data[i][4] = allStudents.get(i).getBirthDate().toString();
            data[i][5] = allStudents.get(i).getBirthPlace();
            data[i][6] = allStudents.get(i).getLivingPlace();
            data[i][7] = allStudents.get(i).getOfficialHome();
            data[i][8] = allStudents.get(i).getEmail();
            data[i][9] = allStudents.get(i).getPhoneNumber();
            data[i][10] = allStudents.get(i).getParentPhoneNumber();
            data[i][11] = String.valueOf(allStudents.get(i).getEntryYear());
            data[i][12] = allStudents.get(i).getGraduatedRegion();
            data[i][13] = allStudents.get(i).getGraduatedRegion();
            data[i][14] = String.valueOf(allStudents.get(i).getEntryIdNumber());
            data[i][15] = String.valueOf(allStudents.get(i).getEntryScore());
            data[i][16] = allStudents.get(i).getSection();
            data[i][17] = allStudents.get(i).getFaculty();
            data[i][18] = allStudents.get(i).getProfession();
            data[i][19] = allStudents.get(i).getGroup();
            data[i][20] = allStudents.get(i).getEducationType();
//            data[i][10] = allStudents.get(i).getEducationYear;
            data[i][21] = allStudents.get(i).getIdCardNumber();
            data[i][22] = allStudents.get(i).getIdCardFinCode();
            data[i][23] = String.valueOf(allStudents.get(i).getGender());
            data[i][24] = allStudents.get(i).getSocialStatusSet().toString();


            /*
            <%--todo scholarship status--%>
             */
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
