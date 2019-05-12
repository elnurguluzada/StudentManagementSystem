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

    //------------------------------------------------------------------------------------------------------
    @Override
    public boolean addStudent(Student student) {
        long userId = insertIntoUserTable(student);
        student.setId(userId);

        return insertIntoStudentTable(student, userId) == 1 &&
                insertIntoStudentSocialStatusTable(student) == student.getSocialStatusSet().size();
    }

    private long insertIntoUserTable(Student student) {
        int roleIdOfStudent = roleRepository.getRoleIdByName("student");

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQLqueries.INSERT_STUDENT_INTO_BDU_USER_TABLE, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR);
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator creator = factory.newPreparedStatementCreator(new Object[]{
                roleIdOfStudent,
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPassword(),
                student.getPhoneNumber(),
                student.getFaculty(),
                student.getGender()
        });

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(creator, keyHolder);
        long userId = (long) keyHolder.getKeyList().get(0).get("user_id");
        return userId;
    }

    private int insertIntoStudentTable(Student student, long userId) {

        return jdbcTemplate.update(SQLqueries.INSERT_STUDENT_INTO_STUDENT_TABLE,
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
                student.getGroupId(),
                student.getEntryYear(),
                student.getScholarshipStatus());

    }

    private int insertIntoStudentSocialStatusTable(Student student) {

        int numOfInserts = 0;
        for (int socialStatusId : student.getSocialStatusSet()) {
            numOfInserts += jdbcTemplate.update(SQLqueries.INSERT_INTO_STUDENT_SOCIAL_STATUS_TABLE, student.getId(), socialStatusId);
        }
        return numOfInserts;
    }

    //------------------------------------------------------------------------------------------------------
    @Override
    public Student getStudentById(long studentId) {

        Student studentUltimate = jdbcTemplate.query(SQLqueries.GET_STUDENT_INFO_BY_ID,
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
                , new Object[]{studentId}).get(0);

        return studentUltimate;
    }

    @Override
    public List<Student> getStudentList() {

        return jdbcTemplate.query(SQLqueries.GET_STUDENT_LIST,
                new StudentMapper()
        );
    }

    @Override
    public int getNumberOfAllStudents() {

        return jdbcTemplate.query(SQLqueries.GET_THE_NUMBER_OF_ALL_STUDENTS,
                ((resultSet, i) -> resultSet.getInt(1))
        ).get(0);

    }

    @Override
    public List<Student> getFilteredStudentList(String searchValue, int beginRow, int endRow) {
        String sql = "select * from( " +
                "select row_number() over(order by s.user_id) as rownum, * " +
                "from bdu_user bu join student s on bu.user_id = s.user_id " +
                "where lower(bu.name) like lower(?) " +
                ") as sub " +
                "where sub.rownum between ? and ?";

        return jdbcTemplate.query(sql,
                new StudentMapper(),
                "%" + searchValue + "%",
                beginRow,
                endRow);
    }

    @Override
    public List<Student> getFilteredStudentList(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    ) {
        String sql =
                "select * from( " +
                        "select row_number() over(order by s.user_id) as rownum, * " +
                        "from bdu_user bu join student s on bu.user_id = s.user_id " +
                        "where lower(bu.name) like lower(?) and " +
                        "lower(bu.surname) like lower(?) and " +
                        "lower(s.father_name) like lower(?) and " +
                        "to_char(s.birth_date, 'yyyy-mm-dd') like ? and  " +
                        "lower(s.birth_place) like lower(?) and " +
                        "lower(s.living_place) like lower(?) and " +
                        "to_char(s.entry_year, '9999') like ? and " +
                        "lower(s.graduation_region) like lower(?) and " +
                        "to_char(s.entry_score, '999') like ? and " +
                        "lower(bu.faculty) like lower(?) and " +
                        "lower(s.profession) like lower(?) and " +
                        "lower(to_char( s.group_id, '99999')) like lower(?) and " +
                        "lower(s.section) like lower(?)" +
                        ") as sub " +
                        "where sub.rownum between ? and ?";

        return jdbcTemplate.query(sql,
                new StudentMapper(),
                "%" + searchValueForName + "%",
                "%" + searchValueForSurname + "%",
                "%" + searchValueForFatherName + "%",
                "%" + searchValueForBirthDate + "%",
                "%" + searchValueForBirthPlace + "%",
                "%" + searchValueForLivingPlace + "%",
                "%" + searchValueForEntryYear + "%",
                "%" + searchValueForGraduationRegion + "%",
                "%" + searchValueForEntryScore + "%",
                "%" + searchValueForFaculty + "%",
                "%" + searchValueForProfession + "%",
                "%" + searchValueForGroup + "%",
                "%" + searchValueForSection + "%",
                beginRow,
                endRow);
    }


    public int getNumberOfFilteredStudents(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, String searchValueForGroup, String searchValueForSection
    ) {
        String sql =
                "select count(*) " +
                        "from bdu_user bu join student s on bu.user_id = s.user_id " +
                        "where lower(bu.name) like lower(?) and " +
                        "lower(bu.surname) like lower(?) and " +
                        "lower(s.father_name) like lower(?) and " +
                        "to_char(s.birth_date, 'yyyy-mm-dd') like ? and " +
                        "lower(s.birth_place) like lower(?) and " +
                        "lower(s.living_place) like lower(?) and " +
                        "to_char(s.entry_year, '9999') like ? and " +
                        "lower(s.graduation_region) like lower(?) and " +
                        "to_char(s.entry_score, '999') like ? and " +
                        "lower(bu.faculty) like lower(?) and " +
                        "lower(s.profession) like lower(?) and " +
                        "lower( to_char(s.group_id, '999')) like lower(?) and " +
                        "lower(s.section) like lower(?)";

        return jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getInt(1)),
                "%" + searchValueForName + "%",
                "%" + searchValueForSurname + "%",
                "%" + searchValueForFatherName + "%",
                "%" + searchValueForBirthDate + "%",
                "%" + searchValueForBirthPlace + "%",
                "%" + searchValueForLivingPlace + "%",
                "%" + searchValueForEntryYear + "%",
                "%" + searchValueForGraduationRegion + "%",
                "%" + searchValueForEntryScore + "%",
                "%" + searchValueForFaculty + "%",
                "%" + searchValueForProfession + "%",
                "%" + searchValueForGroup + "%",
                "%" + searchValueForSection + "%").get(0);
    }

    @Override
    public List<Student> getFilteredStudentListOfSelectedGroup(
            int beginRow, int endRow,
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection
    ) {
        String sql =
                    "SELECT * FROM ( " +
                            "SELECT row_number() OVER (ORDER BY s.user_id) AS rownum, * " +
                            "FROM bdu_user bu JOIN student s ON bu.user_id = s.user_id " +
                            "WHERE lower(bu.name) LIKE lower(?) AND " +
                            "lower(bu.surname) like lower(?) AND " +
                            "lower(s.father_name) LIKE lower(?) AND " +
                            "to_char(s.birth_date, 'yyyy-mm-dd') LIKE ? AND  " +
                            "lower(s.birth_place) LIKE lower(?) AND " +
                            "lower(s.living_place) LIKE lower(?) AND " +
                            "to_char(s.entry_year, '9999') LIKE ? AND " +
                            "lower(s.graduation_region) LIKE lower(?) AND " +
                            "to_char(s.entry_score, '999') LIKE ? AND " +
                            "lower(bu.faculty) LIKE lower(?) AND " +
                            "lower(s.profession) LIKE lower(?) AND " +
                            "s.group_id = ? AND " +
                            "lower(s.section) LIKE lower(?)" +
                            "ORDER BY s.user_id"+
                    ") AS sub " +
                    "WHERE sub.rownum BETWEEN ? AND ?";

        return jdbcTemplate.query(sql,
                new StudentMapper(),
                "%" + searchValueForName + "%",
                "%" + searchValueForSurname + "%",
                "%" + searchValueForFatherName + "%",
                "%" + searchValueForBirthDate + "%",
                "%" + searchValueForBirthPlace + "%",
                "%" + searchValueForLivingPlace + "%",
                "%" + searchValueForEntryYear + "%",
                "%" + searchValueForGraduationRegion + "%",
                "%" + searchValueForEntryScore + "%",
                "%" + searchValueForFaculty + "%",
                "%" + searchValueForProfession + "%",
                        groupId,
                "%" + searchValueForSection + "%",
                beginRow,
                endRow);

    }

    @Override
    public int getNumberOfFilteredStudentsOfSelectedGroup(
            String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
            String searchValueForBirthDate, String searchValueForBirthPlace, String searchValueForLivingPlace,
            String searchValueForEntryYear, String searchValueForGraduationRegion, String searchValueForEntryScore,
            String searchValueForFaculty, String searchValueForProfession, int groupId, String searchValueForSection
    ) {
        String sql =
                "select count(*) " +
                        "from bdu_user bu join student s on bu.user_id = s.user_id " +
                        "where lower(bu.name) LIKE lower(?) AND " +
                        "lower(bu.surname) LIKE lower(?) AND " +
                        "lower(s.father_name) LIKE lower(?) AND " +
                        "to_char(s.birth_date, 'yyyy-mm-dd') LIKE ? AND " +
                        "lower(s.birth_place) LIKE lower(?) AND " +
                        "lower(s.living_place) LIKE lower(?) AND " +
                        "to_char(s.entry_year, '9999') LIKE ? AND " +
                        "lower(s.graduation_region) LIKE lower(?) AND " +
                        "to_char(s.entry_score, '999') LIKE ? AND " +
                        "lower(bu.faculty) LIKE lower(?) AND " +
                        "lower(s.profession) LIKE lower(?) AND " +
                        "s.group_id = ? AND " +
                        "lower(s.section) LIKE lower(?)";

        return jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getInt(1)),
                "%" + searchValueForName + "%",
                "%" + searchValueForSurname + "%",
                "%" + searchValueForFatherName + "%",
                "%" + searchValueForBirthDate + "%",
                "%" + searchValueForBirthPlace + "%",
                "%" + searchValueForLivingPlace + "%",
                "%" + searchValueForEntryYear + "%",
                "%" + searchValueForGraduationRegion + "%",
                "%" + searchValueForEntryScore + "%",
                "%" + searchValueForFaculty + "%",
                "%" + searchValueForProfession + "%",
                            groupId ,
                "%" + searchValueForSection + "%").get(0);
    }

    //------------------------------------------------------------------------------------------------------
    @Override
    public int updateStudent(Student student) {

        if (updateStudentInBduUser(student) == 1 && updateStudentInStudent(student) == 1 &&
                updateStudentInStudentSocialStatus(student) == 1)
            return 1;
        else
            return 0;
    }

    private int updateStudentInBduUser(Student student) {
//        "update bdu_user set name = ?, surname = ?,email = ?,phone_num = ?,faculty = ?,gender = ? where user_id = ?"
        return jdbcTemplate.update(SQLqueries.UPDATE_STUDENT_IN_BDU_USER_TABLE,
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getFaculty(),
                student.getGender(),
                student.getId());
    }

    private int updateStudentInStudent(Student student) {

        return jdbcTemplate.update(SQLqueries.UPDATE_STUDENT_IN_STUDENT_TABLE,
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
                student.getGroupId(),
                student.getScholarshipStatus(),
                student.getEntryYear(),
                student.getId()); //todo social_status_id
    }

    private int updateStudentInStudentSocialStatus(Student student) {
        Set<Integer> updatedStatus = student.getSocialStatusSet();
        Set<Integer> currentStatusInDB = getSocialStatusSetById(student.getId());
        Set<Integer> statusToAdd = new HashSet<>();
        Set<Integer> statusToRemove = new HashSet<>();

//which statuses will be added
        for (Integer i : updatedStatus) {
            boolean flag = false;
            for (Integer j : currentStatusInDB) {
                if (i.equals(j)) flag = true;
            }
            if (!flag)
                statusToAdd.add(i);
        }
//which statuses will be removed
        for (Integer i : currentStatusInDB) {
            boolean flag = false;
            for (Integer j : updatedStatus) {
                if (i.equals(j)) flag = true;
            }
            if (!flag)
                statusToRemove.add(i);
        }

//add new statuses
        String sql = "INSERT INTO student_social_status VALUES (nextval('student_social_status_sequence'), ?, ?)";
        for (Integer status : statusToAdd) {
            jdbcTemplate.update(sql,
                    student.getId(),
                    status
            );
        }

//remove old statuses
        sql = "DELETE FROM student_social_status WHERE user_id=? AND social_status_id=? ";
        for (Integer status : statusToRemove) {
            jdbcTemplate.update(sql,
                    student.getId(),
                    status
            );
        }

        return 1;
    }

    //------------------------------------------------------------------------------------------------------
    public Set<String> getFacultySet(int creationYear) {
        String sql = "SELECT distinct(faculty) FROM groups WHERE creation_year = ?";

        List<String> facultyList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString(1)),
                creationYear);

        return new HashSet<>(facultyList);
    }

    public Set<String> getProfessionSet(int creationYear, String faculty) {
        String sql = "SELECT distinct(profession) FROM groups WHERE creation_year = ? and faculty=?";

        List<String> professionList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString(1)),
                creationYear, faculty);

        return new HashSet<>(professionList);
    }

    public Set<String> getSectionSet(int creationYear, String faculty, String profession) {
        String sql = "SELECT distinct(section) FROM groups WHERE creation_year = ? and faculty=? and profession=?";

        List<String> sectionList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString(1)),
                creationYear, faculty, profession);

        return new HashSet<>(sectionList);
    }


    private class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getLong("user_id"));
            student.setSurname(resultSet.getString("surname"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));
            student.setRoleId(resultSet.getInt("role_id"));
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
            student.setSocialStatusSet(getSocialStatusSetById(resultSet.getLong("user_id")));
            student.setParentPhoneNumber(resultSet.getString("parent_num"));
            student.setGraduatedRegion(resultSet.getString("graduation_school"));
            student.setEntryIdNumber(resultSet.getInt("entry_id_num"));
            student.setEntryScore(resultSet.getInt("entry_score"));
            student.setEducationType(resultSet.getString("education_type"));
            student.setProfession(resultSet.getString("profession"));
            student.setSection(resultSet.getString("section"));
            student.setGroupId(resultSet.getInt("group_id"));
            student.setScholarshipStatus(resultSet.getInt("scholarship_status"));
            student.setEntryYear(resultSet.getInt("entry_year"));
            return student;
        }
    }

    private Set<Integer> getSocialStatusSetById(long userId) {
        List<Integer> socialStatusList = jdbcTemplate.query(SQLqueries.GET_SOCIAL_STATUS_SET_OF_STUDENT_BY_USER_ID,
                ((resultSet, i) -> resultSet.getInt(1)),
                userId);
        return new HashSet<>(socialStatusList);
    }


}
