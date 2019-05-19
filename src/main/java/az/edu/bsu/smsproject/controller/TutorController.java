package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.Service.GroupService;
import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.Service.TutorService;
import az.edu.bsu.smsproject.domain.DataTable;
import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.domain.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    private final CommonService commonService;
    private final StudentService studentService;
    private final TutorService tutorService;
    private final GroupService groupService;
    private final StudentValidation studentValidation;

    @Autowired
    public TutorController(StudentService studentService, CommonService commonService, TutorService tutorService, GroupService groupService, StudentValidation studentValidation) {
        this.studentService = studentService;
        this.commonService = commonService;
        this.tutorService = tutorService;
        this.groupService = groupService;
        this.studentValidation = studentValidation;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        Object target = webDataBinder.getTarget();

        if (target == null)
            return;
        if ( target.getClass() == Student.class )
            webDataBinder.setValidator(studentValidation);

    }

    @GetMapping(value = {"/index", "/"})
    public String index(){
        return "tutor/index";
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/studentForm")
    public ModelAndView showStudentForm(){
        ModelAndView modelAndView = new ModelAndView("tutor/StudentRegistration/addStudent");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent( @Valid @ModelAttribute("student") Student student, Errors errors ){

        ModelAndView modelAndView = new ModelAndView("tutor/StudentRegistration/addStudent");

        if ( !errors.hasErrors() ){
            if ( studentService.addStudent( student ).isPresent() ){
                modelAndView.addObject("success", true);
            }
            else{
                modelAndView.addObject("success", false);
            }
        }
        else{
            modelAndView.addObject("success", false);
        }
        return modelAndView;
    }

    @ResponseBody @GetMapping("/getFaculties")
    public Set<String> getFaculties( @RequestParam(name="year") int year ){
        return commonService.getFacultySet(year);
    }

    @ResponseBody @GetMapping("/getProfessions")
    public Set<String> getProfessions(@RequestParam(name="year") int year, @RequestParam(name="faculty") String faculty){
        return commonService.getProfessionSet(year, faculty);
    }

    @ResponseBody @GetMapping("/getSections")
    public Set<String> getSections(@RequestParam(name="year") int year, @RequestParam(name="faculty") String faculty, @RequestParam(name="profession") String profession
    ){
        return commonService.getSectionSet(year, faculty, profession);
    }
 //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/studentsList")
    public String getStudentsForm(){
        return "tutor/StudentList/studentList";
    }

    @ResponseBody @GetMapping("/getStudents")
    public DataTable showStudents(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "search[value]") String searchValue,
            @RequestParam(name = "columns[1][search][value]") String searchValueForName,
            @RequestParam(name = "columns[2][search][value]") String searchValueForSurname,
            @RequestParam(name = "columns[3][search][value]") String searchValueForFatherName,
            @RequestParam(name = "columns[4][search][value]") String searchValueForBirthDate,
            @RequestParam(name = "columns[5][search][value]") String searchValueForBirthPlace,
            @RequestParam(name = "columns[6][search][value]") String searchValueForLivingPlace,
            @RequestParam(name = "columns[11][search][value]") String searchValueForEntryYear,
            @RequestParam(name = "columns[12][search][value]") String searchValueForGraduationRegion,
            @RequestParam(name = "columns[15][search][value]") String searchValueForEntryScore,
            @RequestParam(name = "columns[17][search][value]") String searchValueForFaculty,
            @RequestParam(name = "columns[18][search][value]") String searchValueForProfession,
            @RequestParam(name = "columns[19][search][value]") String searchValueForGroup,
            @RequestParam(name = "columns[16][search][value]") String searchValueForSection
    ) {
        int numberOfAllStudents = studentService.getNumberOfAllStudents();

        List<Student > filteredStudentList = studentService.getFilteredStudentList( start+1, start+length+1,
                searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, searchValueForGroup, searchValueForSection);
        System.out.println(filteredStudentList);
        int numberOfFilteredStudents = studentService.getNumberOfFilteredStudents(
                searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, searchValueForGroup, searchValueForSection
        );
        System.out.println("numberOfFilteredStudents = "+numberOfFilteredStudents);
        if ( start + length > numberOfFilteredStudents )
            length = numberOfFilteredStudents - start;

        String[][] data = new String[length][25];
        for (int i=0; i<length; i++){
            Student student = filteredStudentList.get(i);
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
            data[i][13] = student.getGraduatedSchool();
            data[i][14] = String.valueOf(student.getEntryIdNumber());
            data[i][15] = String.valueOf(student.getEntryScore());
            data[i][16] = student.getSection();
            data[i][17] = student.getFaculty();
            data[i][18] = student.getProfession();
            data[i][19] = String.valueOf(student.getGroupId());
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

        return new DataTable(draw, numberOfAllStudents, numberOfFilteredStudents, data);
    }

    @GetMapping("/getStudentInfoPopup/{userId}")
    public String getPersonalStudentInfo( @PathVariable("userId") long userId, Model model) {
        model.addAttribute("student", studentService.getStudentById(userId));
        return "tutor/StudentList/studentPersonalInfo";
    }

    @GetMapping("/updateStudent/{userId}")
    public String showUpdateStudent( @PathVariable("userId") int userId, Model model){
        model.addAttribute("student", studentService.getStudentById(userId));
        return "tutor/StudentList/updateStudentForm";
    }

    @PostMapping("/updateStudent")
    public ModelAndView updateStudent( @Valid @ModelAttribute(name = "student") Student student, BindingResult bindingResult ){
//        bindingResult.getAllErrors().forEach(System.out::println);
        System.out.println(student);
        ModelAndView modelAndView = new ModelAndView("tutor/StudentList/updateStudentForm");
        if ( !bindingResult.hasErrors() ){
            boolean success = studentService.updateStudent(student).isPresent();
            modelAndView.addObject("success", success);
        }
        return modelAndView;
    }

//----------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/groups")
    public String groupList(){
        return "tutor/groupList";
    }

    @ResponseBody @GetMapping("/getGroups")
    public DataTable showGroups(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "columns[1][search][value]") String searchValueForName,
            @RequestParam(name = "columns[2][search][value]") String searchValueForYear,
            @RequestParam(name = "columns[3][search][value]") String searchValueForFaculty,
            @RequestParam(name = "columns[4][search][value]") String searchValueForProfession,
            @RequestParam(name = "columns[5][search][value]") String searchValueForSection
    ){
        int numberOfAllGroups = groupService.getNumberOfAllGroups();

        List<Group> filteredGroupList = groupService.getFilteredGroupList(start, start+length, searchValueForName, searchValueForYear, searchValueForFaculty, searchValueForProfession, searchValueForSection);
        int numberOfFilteredStudents = groupService.getNumberOfFilteredGroups(searchValueForName, searchValueForYear, searchValueForFaculty, searchValueForProfession, searchValueForSection);

        if ( start+length > numberOfFilteredStudents )
            length = numberOfFilteredStudents - start;

        String[][] data = new String[length][7];
        for (int i=0; i<length; i++){
            Group group = filteredGroupList.get(i);
            data[i][0] = String.valueOf(group.getId());
            data[i][1] = group.getName();
            data[i][2] = String.valueOf(group.getCreationYear());
            data[i][3] = group.getFaculty();
            data[i][4] = group.getProfession();
            data[i][5] = String.valueOf(group.getSection());
        }

        return new DataTable(draw, numberOfAllGroups, numberOfFilteredStudents, data);
    }

    @ResponseBody @GetMapping("/getGroupStudents")
    public DataTable showStudentsOfGroup(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "search[value]") String searchValue,
            @RequestParam(name = "columns[1][search][value]") String searchValueForName,
            @RequestParam(name = "columns[2][search][value]") String searchValueForSurname,
            @RequestParam(name = "columns[3][search][value]") String searchValueForFatherName,
            @RequestParam(name = "columns[4][search][value]") String searchValueForBirthDate,
            @RequestParam(name = "columns[5][search][value]") String searchValueForBirthPlace,
            @RequestParam(name = "columns[6][search][value]") String searchValueForLivingPlace,
            @RequestParam(name = "columns[11][search][value]") String searchValueForEntryYear,
            @RequestParam(name = "columns[12][search][value]") String searchValueForGraduationRegion,
            @RequestParam(name = "columns[15][search][value]") String searchValueForEntryScore,
            @RequestParam(name = "columns[17][search][value]") String searchValueForFaculty,
            @RequestParam(name = "columns[18][search][value]") String searchValueForProfession,
            @RequestParam(name = "groupId") int groupId,
            @RequestParam(name = "columns[16][search][value]") String searchValueForSection
    ) {

        int numberOfAllStudents = studentService.getNumberOfAllStudents();

        List<Student > filteredStudentList = studentService.getFilteredStudentListOfSelectedGroup( start, start+length,
                searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, groupId, searchValueForSection);


        int numberOfFilteredStudents = studentService.getNumberOfFilteredStudentsOfSelectedGroup(
                searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, groupId, searchValueForSection
        );


        if ( start + length > numberOfFilteredStudents )
            length = numberOfFilteredStudents - start;

        String[][] data = new String[length][25];
        for (int i=0; i<length; i++){
            Student student = filteredStudentList.get(i);
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
            data[i][19] = String.valueOf(student.getGroupId());
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

        return new DataTable(draw, numberOfAllStudents, numberOfFilteredStudents, data);
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

//    @GetMapping("/getGroupsList")
//    public ModelAndView getGroups() {
//        return new ModelAndView("tutor/Group/groupList");
//    }

//    @ResponseBody @GetMapping("/getGroups")
//    public DataTable showGroups(@RequestParam(name = "draw") int draw,
//                                @RequestParam(name = "start") int start,
//                                @RequestParam(name = "length") int length,
//                                @RequestParam(name = "search[value]") String searchValue) {
//
//
//        DataTable dataTable = new DataTable();
//        dataTable.setDraw(draw);
//
//        int amountOfAllGroups = tutorService.getNumberOfAllGroups();
//        dataTable.setRecordsTotal(amountOfAllGroups);
//
//        int amountOfFilteredGroups = tutorService.getNumberOfFilteredGroups(searchValue);
//        dataTable.setRecordsFiltered(amountOfFilteredGroups);
//
//
//        List<Group> filteredGroupList = tutorService.getFilteredGroupList(searchValue, start, start + length);
//
//        System.out.println(filteredGroupList);
//
//        if (start + length > amountOfFilteredGroups) {
//            length = amountOfFilteredGroups % length;
//        }
//
//        String[][] data = new String[length][8];
//        for (int i = 0; i < length; i++) {
//            Group groups = filteredGroupList.get(i);
//            System.out.println(groups);
//            data[i][0] = String.valueOf(groups.getId());
//            data[i][1] = groups.getName();
//            data[i][2] = String.valueOf(groups.getCreationYear());
//            data[i][3] = groups.getFaculty();
//            data[i][4] = groups.getProfession();
//            data[i][5] = groups.getSection();
//            data[i][6] = String.valueOf(groups.getStudentNumber());
//            data[i][7] = "<a href=\"/tutor/getGroupMembers?groupId=" + groups.getId() + "\">View Group Members</a>";
//
//        }
//
//        dataTable.setData(data);
//        return dataTable;
//    }

//------------------------------------------------------------------------------------------------------------------------------



    @GetMapping("/getGroupMembers")
    public ModelAndView getStudentsOfIdenticalGroup(@RequestParam("groupId") long groupId,
                                                    HttpSession httpSession) {
        //httpServletRequest.setAttribute("groupId" , groupId);
        httpSession.setAttribute("groupId" , groupId);
        //TODO take from httpsession
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("groupId" , groupId);
        modelAndView.setViewName( "tutor/Group/groupMembers");
        return  modelAndView;
    }

    @ResponseBody @GetMapping("/getStudentsOfGroup")
    public DataTable showStudentsOfGroup(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "search[value]") String searchValue,
            HttpSession httpSession

    ) {

        long groupId = (long) httpSession.getAttribute("groupId");

        System.out.println("in /getStudentsOfGroup " + groupId);

        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);

        int numberOfStudentsOfGroup = studentService.getNumberOfStudentsOfIdenticalGroup(groupId);
        dataTable.setRecordsTotal(numberOfStudentsOfGroup);


        int numberOfFilteredStudentsOfGroup = studentService.getNumberOfFilteredStudentsOfIdenticalGroup( searchValue, groupId);
        dataTable.setRecordsFiltered(numberOfFilteredStudentsOfGroup);

        List<Student> studentList = studentService.getStudentsOfIdenticalGroup( groupId, searchValue , start , start + length);

        System.out.println(studentList);

        if (start + length > numberOfFilteredStudentsOfGroup)
            length = numberOfFilteredStudentsOfGroup % length;

        String[][] data = new String[length][25];
        for (int i = 0; i < length; i++) {
            Student student = studentList.get(i);
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
            data[i][19] = String.valueOf(student.getGroupId());
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
        dataTable.setData(data);


        return dataTable;

    }


//------------------------------------------------------------------------------------------------------------------------------


    @GetMapping("/getNotGroupedStudent")
    public ModelAndView getNotGroupedStudents(){
        return new ModelAndView("tutor/StudentList/notGroupedStudentList");
    }

    @ResponseBody @GetMapping("/getNotGroupedStudents")
    public DataTable showNotGroupedStudents( @RequestParam(name = "draw") int draw,
                                             @RequestParam(name = "start") int start,
                                             @RequestParam(name = "length") int length,
                                             @RequestParam(name = "search[value]") String searchValue,
                                             @RequestParam(name = "columns[1][search][value]") String searchValueForName,
                                             @RequestParam(name = "columns[2][search][value]") String searchValueForSurname,
                                             @RequestParam(name = "columns[3][search][value]") String searchValueForFatherName,
                                             @RequestParam(name = "columns[4][search][value]") String searchValueForBirthDate,
                                             @RequestParam(name = "columns[5][search][value]") String searchValueForBirthPlace,
                                             @RequestParam(name = "columns[6][search][value]") String searchValueForLivingPlace,
                                             @RequestParam(name = "columns[11][search][value]") String searchValueForEntryYear,
                                             @RequestParam(name = "columns[12][search][value]") String searchValueForGraduationRegion,
                                             @RequestParam(name = "columns[15][search][value]") String searchValueForEntryScore,
                                             @RequestParam(name = "columns[17][search][value]") String searchValueForFaculty,
                                             @RequestParam(name = "columns[18][search][value]") String searchValueForProfession,
                                             @RequestParam(name = "columns[19][search][value]") String searchValueForGroup,
                                             @RequestParam(name = "columns[16][search][value]") String searchValueForSection,
                                             HttpSession httpSession
    ){

        ModelAndView modelAndView = new ModelAndView();

        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);

        int numberOfAllStudentsNotGrouped = studentService.getNumberOfAllStudentsNotGrouped();
        dataTable.setRecordsTotal(numberOfAllStudentsNotGrouped);


        int numberOfFilteredStudentsNotGrouped = studentService.getNumberOfFilteredStudentsNotGrouped(searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, searchValueForGroup, searchValueForSection
        );

        dataTable.setRecordsFiltered(numberOfFilteredStudentsNotGrouped);

        List<Student> filteredNotGroupedStudentList = studentService.getFilteredStudentListNotGrouped( start,  start + length,
                searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate,
                searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion,
                searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, searchValueForGroup, searchValueForSection
        );



        if (start + length > numberOfFilteredStudentsNotGrouped)
            length = numberOfFilteredStudentsNotGrouped % length;


        String[][] data = new String[length][25];



        int a = 0;
        for (int i = 0 ; i < length; i++) {
            Student student = filteredNotGroupedStudentList.get(i);

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
            System.out.println("giris bali =" + student.getEntryScore());
            data[i][16] = student.getSection();
            data[i][17] = student.getFaculty();
            data[i][18] = student.getProfession();
            data[i][19] = String.valueOf(student.getGroupId());
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
        dataTable.setData(data);


        httpSession.setAttribute("studentList" , filteredNotGroupedStudentList);

        return dataTable;

        }


//------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getNewCreatedGroup")
    public ModelAndView getNewGroups(@RequestParam (name = "profession") String profession,
                                     @RequestParam (name = "section") String section,
                                     @RequestParam (name = "year") int year,
                                     @RequestParam (name = "groupCount") int groupCount,
                                     @RequestParam (name = "educationType") String eduType,
                                     HttpSession httpSession) {

//        System.out.println("/getNewCreatedGroup");
//        System.out.println(profession + " " + section  + " " + year + " " + eduType + " " + groupCount);
//        httpSession.setAttribute("profession" , profession );
//        httpSession.setAttribute("section" , section );
//        httpSession.setAttribute("year" , year );
//        httpSession.setAttribute("eduType" , eduType );
//        httpSession.setAttribute("groupCount" , groupCount );
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("tutor/Group/createdGroupList");

        List<Group> groupList = new ArrayList<>();
        groupList = groupService.groupStudents(profession, section , eduType , year , groupCount);

        httpSession.setAttribute("groupList", groupList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tutor/Group/createdGroupList");

        return modelAndView;
    }


    @ResponseBody @GetMapping("/createNewGroup")
    public DataTable createGroup(@RequestParam(name = "draw") int draw,
                                 @RequestParam(name = "start") int start,
                                 @RequestParam(name = "length") int length,
                                 @RequestParam(name = "search[value]") String searchValue,
                                 HttpSession httpSession){

        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);

       List<Group> groupList = new ArrayList<>();

        groupList = (List<Group>) httpSession.getAttribute("groupList");
        int amountOfAllGroups = groupList.size();
        dataTable.setRecordsTotal(amountOfAllGroups);

        int amountOfFilteredGroups = groupList.size();
        dataTable.setRecordsFiltered(amountOfFilteredGroups);

        if (start + length > amountOfFilteredGroups)
            length = amountOfFilteredGroups - start;

        String[][] data = new String[length][8];
        for (int i = 0; i < length; i++) {
            Group groups = groupList.get(i);
            System.out.println(groups);
            data[i][0] = String.valueOf(groups.getId());
            data[i][1] = groups.getName();
            data[i][2] = String.valueOf(groups.getCreationYear());
            data[i][3] = groups.getFaculty();
            data[i][4] = groups.getProfession();
            data[i][5] = groups.getSection();
            data[i][6] = String.valueOf(groups.getStudentNumber());
            data[i][7] = "<a href=\"/tutor/getGroupMembers?groupId=" + groups.getId() + "\">View Group Members</a>";
        }

        dataTable.setData(data);
        return dataTable;
    }


    @GetMapping("/chat")
    public String chat(){
        return "tutor/chat";
    }
}
