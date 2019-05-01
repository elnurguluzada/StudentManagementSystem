package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Set;

public interface TutorService {
    public boolean addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public List<Student> getFilteredStudentList(String searchValue);
    public int getNumberOfAllStudents();
    public int updateStudent(Student student);
    public Set<String> getFacultySet(int year);
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    public int getNumberOfFilteredStudents(String searchValue);
    public Set<String> getProfessionSet(int year, String faculty);
    public Set<String> getSectionSet(int year, String faculty, String profession);

}
