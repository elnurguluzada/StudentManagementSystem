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

        return insertIntoStudentTable(student, userId) == 1 &&
                insertIntoStudentSocialStatusTable(student) == student.getSocialStatusSet().size();
    }

    private long insertIntoUserTable(Student student ){
        int roleIdOfStudent = roleRepository.getRoleIdByName("student");
        String sql = "INSERT INTO bdu_user(user_id, role_id, name, surname, email, password, phone_num, faculty, gender) " +
                "values(nextval('user_sequence'), "+ roleIdOfStudent +", ?, ?, ?, ?, ?, ?, ?)";


        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR);
        factory.setReturnGeneratedKeys(true);
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
        long userId = (long) keyHolder.getKeyList().get(0).get("user_id");
        return userId;
    }

    private int insertIntoStudentTable( Student student, long userId ){
        String sql = "insert into student(user_id, id_card_num, id_card_fin_code, father_name, birth_date, birth_place, living_place, official_home, parent_num, " +
                "graduation_region, graduation_school, entry_id_num, entry_score, education_type, profession, section, bsu_group, education_year, scholarship_status) " +
                "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        return jdbcTemplate.update( sql,
                userId,
                student.getIdCardNumber(),
                student.getIdCardFinCode(),
                student.getFatherName(),
                student.getBirthDate(),
                student.getBirthPlace(),
                student.getLivingPlace(),
                student.getOfficialHome(),
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
                student.getScholarshipStatus());

    }

    private int insertIntoStudentSocialStatusTable( Student student ){
        String sql = "insert into student_social_status(id, user_id, social_status_id) values(nextval('student_social_status_sequence'), ?, ?)";
        int numOfInserts = 0;
        for (int socialStatusId: student.getSocialStatusSet()){
            numOfInserts += jdbcTemplate.update( sql, student.getId(), socialStatusId );
        }

        return numOfInserts;
    }

}
