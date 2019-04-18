package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Optional;

public interface TutorService {
    public boolean addStudent( Student student );
    List<Student> getStudentInfoById(long id);
    List<Student> getStudentList();

}
