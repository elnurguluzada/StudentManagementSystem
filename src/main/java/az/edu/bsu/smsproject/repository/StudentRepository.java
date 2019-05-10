package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {
    public Optional<Student> addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    public int getNumberOfAllStudents();
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
    public Optional<Student> updateStudent(Student student);
    public boolean delete(long userId);
}
