package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<Student> addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    @Override
    public Student getStudentById(long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.getStudentList();
    }

    @Override
    public int getNumberOfAllStudents() {
        return 0;
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow) {
        return studentRepository.getFilteredStudentList(searchValue, beginRow, endRow);
    }

    @Override
    public List<Student> getFilteredStudentList(int beginRow, int endRow, String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection) {
        return studentRepository.getFilteredStudentList(beginRow, endRow,  searchValueForName, searchValueForSurname, searchValueForFatherName, searchValueForBirthDate, searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion, searchValueForEntryScore, searchValueForFaculty, searchValueForProfession,  searchValueForGroup, searchValueForSection);
    }

    @Override
    public int getNumberOfFilteredStudents(String searchValueForName, String searchValueForSurname, String searchValueForFatherName, String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace, String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore, String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection) {
        return studentRepository.getNumberOfFilteredStudents( searchValueForName,  searchValueForSurname, searchValueForFatherName, searchValueForBirthDate, searchValueForBirthPlace, searchValueForLivingPlace, searchValueForEntryYear, searchValueForGraduationRegion, searchValueForEntryScore, searchValueForFaculty, searchValueForProfession, searchValueForGroup, searchValueForSection);
    }

    @Override
    public boolean delete(long userId) {
        return studentRepository.delete(userId);
    }
}
