package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;

public interface StudentService {

    public Student add(Student student );
    public Student update( Student student );
    public Student remove( Student student );

    public Student getStudentById( long studentId );
    public List<Student> getStudentList();


}
