package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.StudentService;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student add(Student student) {
        return studentRepository.add( student );
    }

    @Override
    public Student update(Student student) {
        return studentRepository.update( student );
    }

    @Override
    public Student remove(Student student) {
        return studentRepository.remove( student );
    }

    @Override
    public Student getStudentById(long studentId) {
        return studentRepository.getStudentById( studentId);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.getStudentList();
    }
}
