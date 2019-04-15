package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Student add(Student student) {
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory("eud");
        PreparedStatementCreator creator = factory.newPreparedStatementCreator(new Object[]{});
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update( creator );
        long userId = keyHolder.getKey().longValue();

        jdbcTemplate.update("", new Object[]{});


        return null;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public Student remove(Student student) {
        return null;
    }

    @Override
    public Student getStudentById(long studentId) {

        Student student = jdbcTemplate.query("SELECT name, surname FROM student WHERE user_id = ?",
                (resultSet, i)->{
                    Student studentInner = new Student();
                    studentInner.setName( resultSet.getString("name") );
                    studentInner.setSurname( resultSet.getString("surname") );
                    return studentInner;
                },
                studentId
        ).get(0);

        return student;
    }

    private class MapRowToStudent implements RowMapper<Student>{
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student studentInner = new Student();
            studentInner.setName( resultSet.getString( "name" ) );
            studentInner.setSurname( resultSet.getString( "surname" ) );
            return studentInner;
            }
        }

    @Override
    public List<Student> getStudentList() {
        return null;
    }

}
