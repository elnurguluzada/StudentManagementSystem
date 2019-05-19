package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentRest")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") long id){
        System.out.println(id);
        return studentService.getStudentById(id);
    }

    @GetMapping("/")
    public List<Student> getStudentList(){
        return studentService.getStudentList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") long id){
        studentService.delete(id);
    }

    @PutMapping("/update")
    public Student updateUser(@RequestBody Student student){
        Optional<Student> optionalStudent = studentService.updateStudent(student);
        return optionalStudent.orElse(null);
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        Optional<Student> optionalStudent = studentService.addStudent(student);
        return optionalStudent.orElse(null);
    }

}
