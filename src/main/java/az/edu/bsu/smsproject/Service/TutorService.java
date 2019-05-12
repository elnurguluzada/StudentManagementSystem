package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Set;

public interface TutorService {
    public boolean addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public int getNumberOfAllStudents();
    public int updateStudent(Student student);
    public Set<String> getFacultySet(int year);
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    public Set<String> getProfessionSet(int year, String faculty);
    public Set<String> getSectionSet(int year, String faculty, String profession);
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
    public List<Student> getFilteredStudentListOfSelectedGroup(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection
    );
    public int getNumberOfFilteredStudentsOfSelectedGroup(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection
    );

}
