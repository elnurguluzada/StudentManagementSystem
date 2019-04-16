package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;

public interface TutorRepository {
    public boolean addStudent( Student student );
    public List<Student> getStudentInfo(long studentId);
    public List<Student> getStudentList();

}
