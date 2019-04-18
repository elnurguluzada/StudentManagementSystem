package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Optional;

public interface TutorService {
    public boolean addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public List<Student> getFilteredStudentList(String searchValue);
    public int getNumberOfAllStudents();

}
