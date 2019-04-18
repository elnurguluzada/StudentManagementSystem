package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.repository.RoleRepository;
import az.edu.bsu.smsproject.repository.SQLqueries;
import az.edu.bsu.smsproject.repository.TutorRepository;
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
import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                "graduation_region, graduation_school, entry_id_num, entry_score, education_type, profession, section, bsu_group, entry_year, scholarship_status) " +
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

    private int insertIntoStudentSocialStatusTable( Student student ) {
        String sql = "insert into student_social_status(id, user_id, social_status_id) values(nextval('student_social_status_sequence'), ?, ?)";
        int numOfInserts = 0;
        for (int socialStatusId : student.getSocialStatusSet()) {
            numOfInserts += jdbcTemplate.update(sql, student.getId(), socialStatusId);
        }
        return numOfInserts;
    }

    @Override
    public List<Student> getStudentList() {

        return jdbcTemplate.query(SQLqueries.GET_STUDENT_LIST,
                new StudentMapper()
        );
    }

    @Override
    public Student getStudentById(long studentId) {

        Student studentUltimate = jdbcTemplate.query(SQLqueries.GET_STUDENT_INFO_BY_ID ,
//                ((resultSet, i) -> {
//                    Student student = new Student();
//                    student.setName( resultSet.getString("name") );
//                    student.setSurname(resultSet.getString("surname"));
//                    student.setFaculty(resultSet.getString("faculty"));
//                    student.setFatherName(resultSet.getString("father_name"));
//                    student.setGender(resultSet.getString("gender").charAt(0));
//                    student.setProfession(resultSet.getString("profession"));
//                    student.setSection(resultSet.getString("section"));
//                    student.setGroup(resultSet.getString("group"));
//                    student.setEntryYear(resultSet.getInt("education_year"));
//                    student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
//                    student.setBirthPlace(resultSet.getString("birth_place"));
//                    student.setEducationType(resultSet.getString("education_type"));
//                    return student;
//
//                })
                new StudentMapper()
                ,new Object[]{studentId} ).get(0);

        return studentUltimate;
    }

    private class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId( resultSet.getLong("user_id") );
            student.setSurname(resultSet.getString("surname"));
            student.setName( resultSet.getString("name") );
            student.setEmail(resultSet.getString("email"));
            student.setRoleId( resultSet.getInt("role_id") );
            student.setPassword(resultSet.getString("password"));
            student.setPhoneNumber(resultSet.getString("phone_num"));
            student.setFaculty(resultSet.getString("faculty"));
            student.setGender(resultSet.getString("gender").charAt(0));
            student.setIdCardNumber(resultSet.getString("id_card_num"));
            student.setIdCardFinCode(resultSet.getString("id_card_fin_code"));
            student.setFatherName(resultSet.getString("father_name")); //
            student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            student.setBirthPlace(resultSet.getString("birth_place"));
            student.setLivingPlace(resultSet.getString("living_place"));
            student.setOfficialHome(resultSet.getString("official_home"));
            student.setSocialStatusSet( getSocialStatusSetById( resultSet.getLong("user_id")));
            student.setParentPhoneNumber(resultSet.getString("parent_num"));
            student.setGraduatedRegion(resultSet.getString("graduation_school"));
            student.setEntryIdNumber(resultSet.getInt("entry_id_num"));
            student.setEntryScore(resultSet.getInt("entry_score"));
            student.setEducationType(resultSet.getString("education_type"));
            student.setProfession(resultSet.getString("profession"));
            student.setSection(resultSet.getString("section"));
            student.setGroup(resultSet.getString("bsu_group"));
            student.setScholarshipStatus(resultSet.getInt("scholarship_status"));
            student.setEntryYear(resultSet.getInt("entry_year"));
            return student;
        }
    }

    private Set<Integer> getSocialStatusSetById( long userId ){

        List<Integer> socialStatusList = jdbcTemplate.query( SQLqueries.GET_SOCIAL_STATUS_SET_OF_STUDENT_BY_USER_ID,
                ((resultSet, i) -> resultSet.getInt("id")),
                userId);

        return new HashSet<>(socialStatusList);
    }

}
