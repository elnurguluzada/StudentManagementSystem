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
import java.util.Set;

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
    public ModelAndView addStudent( @Valid @ModelAttribute("student") Student student,
                                    Errors errors ){

        ModelAndView modelAndView = new ModelAndView("Tutor/addStudent");

        if ( !errors.hasErrors() ){

            if ( tutorService.addStudent( student ) )
                modelAndView.addObject("success", true);
            else
                modelAndView.addObject("success", false);
        }
        return modelAndView;
    }

    @GetMapping("/getStudentsList")
    public ModelAndView getStudentsForm(){
        return new ModelAndView("Tutor/studentList");
    }

//    @GetMapping("/getStudents")
//    public ModelAndView showStudents(
//            @RequestParam(name = "draw") int draw,
//            @RequestParam(name = "start") int start,
//            @RequestParam(name = "length") int length,
//            @RequestParam(name = "search[value]") String searchValue
//    ) {
//        DataTable dataTable = new DataTable();
//        dataTable.setDraw(draw);
//
//        int numberOfAllStudents = tutorService.getNumberOfAllStudents();
//        dataTable.setRecordsTotal( numberOfAllStudents );
//
//        List<Student > filteredStudentList = tutorService.getFilteredStudentList( searchValue );
//        int numberOfFilteredStudents = filteredStudentList.size();
//        dataTable.setRecordsFiltered( numberOfFilteredStudents );
//
//        if ( start + length > numberOfFilteredStudents )
//            length = numberOfFilteredStudents % length;
//
//        String[][] data = new String[length][25];
//        for (int i=0; i<length; i++){
//            Student student = filteredStudentList.get(start+i);
//            data[i][0] = String.valueOf(student.getId());
//            data[i][1] = student.getName();
//            data[i][2] = student.getSurname();
//            data[i][3] = student.getFatherName();
//            data[i][4] = student.getBirthDate().toString();
//            data[i][5] = student.getBirthPlace();
//            data[i][6] = student.getLivingPlace();
//            data[i][7] = student.getOfficialHome();
//            data[i][8] = student.getEmail();
//            data[i][9] = student.getPhoneNumber();
//            data[i][10] = student.getParentPhoneNumber();
//            data[i][11] = String.valueOf(student.getEntryYear());
//            data[i][12] = student.getGraduatedRegion();
//            data[i][13] = student.getGraduatedRegion();
//            data[i][14] = String.valueOf(student.getEntryIdNumber());
//            data[i][15] = String.valueOf(student.getEntryScore());
//            data[i][16] = student.getSection();
//            data[i][17] = student.getFaculty();
//            data[i][18] = student.getProfession();
//            data[i][19] = student.getGroup();
//            data[i][20] = student.getEducationType();
//            data[i][21] = student.getIdCardNumber();
//            data[i][22] = student.getIdCardFinCode();
//            data[i][23] = String.valueOf(student.getGender());
//            data[i][24] = student.getSocialStatusSet().toString();
////            data[i][25] = "<a href='#' class='sth' customerId='"+ student.getId() +"'></a>";
//
//            /*
//            <%--todo scholarship status--%>
//             */
//        }
//        dataTable.setData( data );
//
//        Gson gson = new Gson();
//        String dataTableJson =gson.toJson(dataTable);
//        System.out.println(dataTableJson);
//
//        ModelAndView modelAndView = new ModelAndView("Tutor/ajaxReceiver");
//        modelAndView.addObject("dataTable", dataTableJson);
//        return modelAndView;
//    }

    @ResponseBody @GetMapping("/getStudents")
    public DataTable showStudents(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "search[value]") String searchValue
    ) {
        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);

        int numberOfAllStudents = tutorService.getNumberOfAllStudents();
        dataTable.setRecordsTotal( numberOfAllStudents );

        List<Student > filteredStudentList = tutorService.getFilteredStudentList( searchValue );
        int numberOfFilteredStudents = filteredStudentList.size();
        dataTable.setRecordsFiltered( numberOfFilteredStudents );

        if ( start + length > numberOfFilteredStudents )
            length = numberOfFilteredStudents % length;

        String[][] data = new String[length][25];
        for (int i=0; i<length; i++){
            Student student = filteredStudentList.get(start+i);
            data[i][0] = String.valueOf(student.getId());
            data[i][1] = student.getName();
            data[i][2] = student.getSurname();
            data[i][3] = student.getFatherName();
            data[i][4] = student.getBirthDate().toString();
            data[i][5] = student.getBirthPlace();
            data[i][6] = student.getLivingPlace();
            data[i][7] = student.getOfficialHome();
            data[i][8] = student.getEmail();
            data[i][9] = student.getPhoneNumber();
            data[i][10] = student.getParentPhoneNumber();
            data[i][11] = String.valueOf(student.getEntryYear());
            data[i][12] = student.getGraduatedRegion();
            data[i][13] = student.getGraduatedRegion();
            data[i][14] = String.valueOf(student.getEntryIdNumber());
            data[i][15] = String.valueOf(student.getEntryScore());
            data[i][16] = student.getSection();
            data[i][17] = student.getFaculty();
            data[i][18] = student.getProfession();
            data[i][19] = student.getGroup();
            data[i][20] = student.getEducationType();
            data[i][21] = student.getIdCardNumber();
            data[i][22] = student.getIdCardFinCode();
            data[i][23] = String.valueOf(student.getGender());
            data[i][24] = student.getSocialStatusSet().toString();
//            data[i][25] = "<a href='#' class='sth' customerId='"+ student.getId() +"'></a>";

            /*
            <%--todo scholarship status--%>
             */
        }
        dataTable.setData( data );

        return dataTable;
    }


    @GetMapping("/getStudentInfoPopup")
    public ModelAndView getPersonalStudentInfo(
            @RequestParam(name="userId") long userId
    ) {
        ModelAndView modelAndView = new ModelAndView("Tutor/studentPersonalInfo");
        modelAndView.addObject("student", tutorService.getStudentById(userId));

        return modelAndView;
    }

    @GetMapping("/updateStudent")
    public ModelAndView updateStudent(
            @RequestParam(name = "userId") long userId
    ){
        System.out.println(userId);
        ModelAndView modelAndView = new ModelAndView("Tutor/updateStudentInfo");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

    @PostMapping("/updateStudent")
    public ModelAndView updateStudent(
            @Valid @ModelAttribute(name = "student") Student student,
            Errors errors
    ){
        String message;
        ModelAndView modelAndView = new ModelAndView("Tutor/studentPersonalInfo");

        if ( !errors.hasErrors() ){
            tutorService.updateStudent(student);
            message = "Good";
        }
        else {
            message = "Bad";
        }

        modelAndView.addObject("message", message);
        return modelAndView;
    }



    @PostMapping("/getFaculties")
    public ModelAndView getFaculties(
            @RequestParam(name="year") int year
    ){
        Set<String> facultySet = tutorService.getFacultySet(year);
        ModelAndView modelAndView = new ModelAndView("Tutor/ajaxReceiverForAddStudent");
        modelAndView.addObject("optionsSet", facultySet).addObject("functionToCall", "fillProfession(this)");
        return modelAndView;
    }

    @PostMapping("/getProfessions")
    public ModelAndView getProfessions(
            @RequestParam(name="year") int year,
            @RequestParam(name="faculty") String faculty
    ){
        System.out.println("In getProfessions");
        Set<String> professionSet = tutorService.getProfessionSet(year, faculty);
        ModelAndView modelAndView = new ModelAndView("Tutor/ajaxReceiverForAddStudent");
        modelAndView.addObject("optionsSet", professionSet).addObject("functionToCall", "fillSection(this)");
        return modelAndView;
    }

    @PostMapping("/getSections")
    public ModelAndView getSections(
            @RequestParam(name="year") int year,
            @RequestParam(name="faculty") String faculty,
            @RequestParam(name="profession") String profession
    ){
        System.out.println("In getSections");
        Set<String> sectionSet = tutorService.getSectionSet(year, faculty, profession);
        ModelAndView modelAndView = new ModelAndView("Tutor/ajaxReceiverForAddStudent");
        modelAndView.addObject("optionsSet", sectionSet);
        return modelAndView;
    }

}
