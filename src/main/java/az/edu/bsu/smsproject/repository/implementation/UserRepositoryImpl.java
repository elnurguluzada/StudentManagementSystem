package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.domain.Tutor;
import az.edu.bsu.smsproject.domain.User;
import az.edu.bsu.smsproject.repository.SQLqueries;
import az.edu.bsu.smsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User authenticate(String email, String password) {
        User user = null;

        int roleId = jdbcTemplate.query( SQLqueries.GET_ROLE_ID_IF_EMAIL_AND_PASSWORD_MATCH,
                ((resultSet, i) -> resultSet.getInt("role_id") ),
                new Object[]{email, password}
                ).get(0);

        if ( roleId == 1 ){ //  If Student
            user = jdbcTemplate.query( SQLqueries.GET_ALL_DATA_FROM_JOINED_BDU_USER_AND_STUDENT,
                    (resultSet, i) -> {
                Student student = new Student();
                student.setId( resultSet.getLong("user_id") );
                student.setName( resultSet.getString("name") );
                student.setSurname( resultSet.getString( "surname" ) );
                student.setEmail( resultSet.getString( "email" ) );
                student.setPassword( resultSet.getString( "password" ) );
                student.setFatherName( resultSet.getString( "father_name" ) );
                student.setRoleId( resultSet.getInt( "role_id" ) );
                student.setEntryScore( resultSet.getInt( "entry_score" ) );
                return student;
                },
                    email).get(0);
            }

            else if ( roleId == 2 ){ // If Tutor
                user = jdbcTemplate.query( SQLqueries.GET_ALL_DATA_FROM_JOINED_BDU_USER_AND_TUTOR,
                        (resultSet, i) -> {
                            Tutor tutor = new Tutor();
                            tutor.setId( resultSet.getInt("user_id") );
                            tutor.setSurname( resultSet.getString( "surname" ) );
                            tutor.setName( resultSet.getString("name") );
                            tutor.setEmail( resultSet.getString( "email" ) );
                            tutor.setPassword( resultSet.getString( "password" ) );
                            tutor.setRoleId( resultSet.getInt( "role_id" ) );
                            return tutor;
                        },
                        email).get(0);
            }


        return user;
    }

}