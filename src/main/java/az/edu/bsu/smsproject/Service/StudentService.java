package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> addStudent( Student student );

    Student getStudentById(long studentId);
    List<Student> getStudentList();
    int getNumberOfAllStudents();
    List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    List<Student> getFilteredStudentList(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection);

    int getNumberOfFilteredStudents(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection);

    List<Student> getFilteredStudentListOfSelectedGroup(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection);

    int getNumberOfFilteredStudentsOfSelectedGroup(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection);

    int getNumberOfAllStudentsNotGrouped();
    int getNumberOfFilteredStudentsNotGrouped(String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
                                              String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
                                              String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
                                              String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection);
    List<Student> getFilteredStudentListNotGrouped(int beginRow, int endRow,
                                                          String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
                                                          String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
                                                          String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
                                                          String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection);

    int getNumberOfStudentsOfSameGroup(long groupId);
    int getNumberOfFilteredStudentsOfSameGroup(String searchValue , long groupId);
    List<Student> getStudentsOfSameGroup(long groupId , String searchParam, int startRow, int endRow);

    Optional<Student> updateStudent(Student student);

    boolean delete(long id);

}
