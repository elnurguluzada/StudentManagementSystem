package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Set;

public interface TutorRepository {
    public boolean addStudent( Student student );
    public Student getStudentById(long studentId);
    public List<Student> getStudentList();
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow);
    public int getNumberOfAllStudents();
    public int updateStudent(Student student);
    public Set<String> getFacultySet(int year);
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



    public int getNumberOfAllGroups();
    public  List<Group> getFilteredGroupList(String searchParam , int startRow , int endRow);
    public int getNumberOfFilteredGroups(String searchParam);
    public List<Student> getStudentsOfIdenticalGroup(long groupId , String searchParam, int startRow, int endRow);
    public int getNumberOfStudentsOfIdenticalGroup(long groupId);
    public int getNumberOfFilteredStudentsOfIdenticalGroup(String searchValue , long groupId);
    public int getNumberOfAllStudentsNotGrouped();
    public int getNumberOfFilteredStudentsNotGrouped( String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
                                                      String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
                                                      String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
                                                      String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    );
    public List<Student> getFilteredStudentListNotGrouped( int beginRow, int endRow,
                                                           String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
                                                           String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
                                                           String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
                                                           String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    );



    public List<Group> groupStudents(List<Student> studentList, List<Long> groupIdList);
}
