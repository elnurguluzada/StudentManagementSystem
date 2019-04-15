package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.RoleRepository;
import az.edu.bsu.smsproject.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
public class TutorRepositoryImpl implements TutorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RoleRepository roleRepository;

    @Autowired
    public TutorRepositoryImpl(JdbcTemplate jdbcTemplate, RoleRepository roleRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleRepository = roleRepository;
    }


    @Override
    public boolean addStudent(Student student) {
        long userId = insertIntoUserTable( student );
        student.setId( userId );

        return insertIntoStudentTable(student, userId) == 1;

    }

    private long insertIntoUserTable(Student student ){
        int roleIdOfStudent = roleRepository.getRoleIdByName("student");

        String sql = "INSERT INTO bdu_user(user_id, role_id, name, surname, email, password, phone_num, faculty, gender) " +
                "values(nextval('user_sequence'), "+ roleIdOfStudent +", ?, ?, ?, ?, ?, ?, ?)";


        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
        PreparedStatementCreator creator = factory.newPreparedStatementCreator( new Object[]{
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPassword(),
                student.getPhoneNumber(),
                student.getFaculty(),
                student.getGender()
        } );

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( creator, keyHolder );
        long userId = keyHolder.getKey().longValue();
        return userId;
    }

    private int insertIntoStudentTable( Student student, long userId ){
        String sql = "insert into student(" +
                "+user_id, id_card_num, id_card_fin_code, father_name, birth_date, birth_place, living_place, official_home, social_status_id, parent_num, " +
                "graduation_region, graduation_school, entry_id_num, entry_score, education_type, profession, section, group, education_year, scholarship_status) " +
                "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  )";

        return jdbcTemplate.update( sql, new Object[]{
                userId,
                student.getIdCardNumber(),
                student.getIdCardFinCode(),
                student.getFatherName(),
                student.getBirthDate(),
                student.getBirthPlace(),
                student.getLivingPlace(),
                student.getOfficialHome(),
                student.getSocialStatusId(),
                student.getParentPhoneNumber(),
                student.getGraduatedRegion(),
                student.getGraduatedSchool(),
                student.getEntryIdNumber(),
                student.getEntryScore(),
                student.getEducationType(),
                student.getProfession(),
                student.getSection(),
                student.getGroup(),
                student.getEntryYear(),
                student.getScholarshipStatus()
        } );

    }



}
