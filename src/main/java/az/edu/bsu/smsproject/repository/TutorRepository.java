package az.edu.bsu.smsproject.repository;

import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.domain.Student;

import java.util.List;
import java.util.Set;

public interface TutorRepository {



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



    public List<Group> groupStudents(String profession, String section , String eduType, int year ,int groupCount );
}
