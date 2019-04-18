package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.TutorService;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
