package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Set;

public interface TutorRepository {
    public boolean addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public List<Student> getFilteredStudentList(String searchValue);
    public int getNumberOfAllStudents();
    public int updateStudent(Student student);
    public Set<String> getFacultySet(int year);
    public Set<String> getProfessionSet(int year, String faculty);
    public Set<String> getSectionSet(int year, String faculty, String profession);
}
