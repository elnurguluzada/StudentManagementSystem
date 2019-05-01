package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.TutorService;
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
    public List<Student> getFilteredStudentList(String searchValue) {
        return tutorRepository.getFilteredStudentList(searchValue);
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
    public int getNumberOfFilteredStudents(String searchValue) {
        return tutorRepository.getNumberOfFilteredStudents(searchValue);
    }

    @Override
    public Set<String> getProfessionSet(int year, String faculty) {
        return tutorRepository.getProfessionSet(year, faculty);
    }

    @Override
    public Set<String> getSectionSet(int year, String faculty, String profession) {
        return tutorRepository.getSectionSet(year, faculty, profession);
    }


}
