package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Optional<Student> addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public int getNumberOfAllStudents();
    public Optional<Student> updateStudent(Student student);
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    public List<Student> getFilteredStudentList(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    );
    public int getNumberOfFilteredStudents(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    );
    public boolean delete(long userId);


}
