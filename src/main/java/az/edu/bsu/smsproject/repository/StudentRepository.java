package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;

public interface StudentRepository {
    public Student add( Student student );
    public Student update( Student student );
    public Student remove( Student student );

    public Student getStudentById( long StudentId );
    public List<Student> getStudentList();
}
