package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.TutorService;
import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }


    @Override
    public boolean addStudent(Student student) {
        return tutorRepository.addStudent( student );
    }

    @Override
    public Student getStudentById(long id) {
        return tutorRepository.getStudentById(id);
    }

    @Override
    public List<Student> getStudentList() {
        return  tutorRepository.getStudentList();
    }

    @Override
    public int getNumberOfAllStudents() {
        return tutorRepository.getNumberOfAllStudents();
    }

    @Override
    public int updateStudent(Student student) {
        return tutorRepository.updateStudent( student );
    }

    @Override
    public Set<String> getFacultySet(int year) {
        return tutorRepository.getFacultySet(year);
    }

    @Override
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow) {
        return tutorRepository.getFilteredStudentList(searchValue, beginRow, endRow);
    }

    @Override
    public Set<String> getProfessionSet(int year, String faculty) {
        return tutorRepository.getProfessionSet(year, faculty);
    }

    @Override
    public Set<String> getSectionSet(int year, String faculty, String profession) {
        return tutorRepository.getSectionSet(year, faculty, profession);
    }

    @Override
    public List<Student> getFilteredStudentList(int beginRow, int endRow, String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection) {
        return tutorRepository.getFilteredStudentList(beginRow, endRow,  searchValueForName,  searchValueForSurname,  searchValueForFatherName,  searchValueForBirthDate,  searchValueForBirthPlace,  searchValueForLivingPlace,  searchValueForEntryYear,  searchValueForGraduationRegion,  searchValueForEntryScore, searchValueForFaculty,  searchValueForProfession,  searchValueForGroup,  searchValueForSection);
    }

    @Override
    public int getNumberOfFilteredStudents(String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection) {
        return tutorRepository.getNumberOfFilteredStudents( searchValueForName,  searchValueForSurname,  searchValueForFatherName,  searchValueForBirthDate,  searchValueForBirthPlace,  searchValueForLivingPlace,  searchValueForEntryYear,  searchValueForGraduationRegion,  searchValueForEntryScore,  searchValueForFaculty,  searchValueForProfession,  searchValueForGroup,  searchValueForSection);
    }


    @Override
    public int getNumberOfAllGroups() {
        return tutorRepository.getNumberOfAllGroups();
    }

    @Override
    public List<Group> getFilteredGroupList(String searchParam, int startRow, int endRow) {
        return tutorRepository.getFilteredGroupList(searchParam, startRow, endRow);
    }

    @Override
    public int getNumberOfFilteredGroups(String searchParam) {
        return tutorRepository.getNumberOfFilteredGroups(searchParam);
    }

    @Override
    public List<Student> getStudentsOfIdenticalGroup(long groupId , String searchParam, int startRow, int endRow) {
        return  tutorRepository.getStudentsOfIdenticalGroup(groupId , searchParam , startRow , endRow);
    }

    @Override
    public int getNumberOfStudentsOfIdenticalGroup(long groupId) {
        return tutorRepository.getNumberOfStudentsOfIdenticalGroup(groupId);
    }

    @Override
    public int getNumberOfFilteredStudentsOfIdenticalGroup(String searchValue, long groupId) {
        return tutorRepository.getNumberOfFilteredStudentsOfIdenticalGroup(searchValue , groupId);
    }

    @Override
    public int getNumberOfAllStudentsNotGrouped() {
        return tutorRepository.getNumberOfAllStudentsNotGrouped();
    }

    @Override
    public int getNumberOfFilteredStudentsNotGrouped(String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection) {
        return tutorRepository.getNumberOfFilteredStudentsNotGrouped(searchValueForName,  searchValueForSurname,  searchValueForFatherName,  searchValueForBirthDate,  searchValueForBirthPlace,  searchValueForLivingPlace,  searchValueForEntryYear,  searchValueForGraduationRegion,  searchValueForEntryScore,  searchValueForFaculty,  searchValueForProfession,  searchValueForGroup,  searchValueForSection);

    }

    @Override
    public List<Student> getFilteredStudentListNotGrouped(int beginRow, int endRow, String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection){
        return tutorRepository.getFilteredStudentListNotGrouped(beginRow, endRow,  searchValueForName,  searchValueForSurname,  searchValueForFatherName,  searchValueForBirthDate,  searchValueForBirthPlace,  searchValueForLivingPlace,  searchValueForEntryYear,  searchValueForGraduationRegion,  searchValueForEntryScore, searchValueForFaculty,  searchValueForProfession,  searchValueForGroup,  searchValueForSection);


    }

    @Override
    public List<Group> groupStudents(String profession, String section, String eduType, int year, int groupCount) {
        return tutorRepository.groupStudents(profession , section , eduType , year , groupCount);
    }


}
