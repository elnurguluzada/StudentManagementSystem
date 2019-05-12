package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Group;
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

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
        long userId = insertIntoUserTable( student );
        student.setId( userId );

        return insertIntoStudentTable(student, userId) == 1 &&
                insertIntoStudentSocialStatusTable(student) == student.getSocialStatusSet().size();
    }

    private long insertIntoUserTable(Student student ){
        int roleIdOfStudent = roleRepository.getRoleIdByName("student");

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQLqueries.INSERT_STUDENT_INTO_BDU_USER_TABLE ,Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR);
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator creator = factory.newPreparedStatementCreator( new Object[]{
                roleIdOfStudent,
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

        return jdbcTemplate.update( SQLqueries.INSERT_STUDENT_INTO_STUDENT_TABLE,
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

    private int insertIntoStudentSocialStatusTable( Student student ) {

        int numOfInserts = 0;
        for (int socialStatusId : student.getSocialStatusSet()) {
            numOfInserts += jdbcTemplate.update(SQLqueries.INSERT_INTO_STUDENT_SOCIAL_STATUS_TABLE, student.getId(), socialStatusId);
        }
        return numOfInserts;
    }
//------------------------------------------------------------------------------------------------------
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
                "%"+searchValue+"%",
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
                        "/*lower(s.group_id) like lower(?) and*/ " +
                        "lower(s.section) like lower(?)" +
                ") as sub " +
                "where sub.rownum between ? and ?";

        return jdbcTemplate.query(sql,
                new StudentMapper(),
                "%"+searchValueForName+"%",
                "%"+searchValueForSurname+"%",
                "%"+searchValueForFatherName+"%",
                "%"+searchValueForBirthDate+"%",
                "%"+searchValueForBirthPlace+"%",
                "%"+searchValueForLivingPlace+"%",
                "%"+searchValueForEntryYear+"%",
                "%"+searchValueForGraduationRegion+"%",
                "%"+searchValueForEntryScore+"%",
                "%"+searchValueForFaculty+"%",
                "%"+searchValueForProfession+"%",
//                "%"+searchValueForGroup+"%",
                "%"+searchValueForSection+"%",
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
                        "/*lower(s.group_id) like lower(?) and*/ " +
                        "lower(s.section) like lower(?)";

        return jdbcTemplate.query(sql,
                ((resultSet, i)-> resultSet.getInt(1)),
                "%"+searchValueForName+"%",
                "%"+searchValueForSurname+"%",
                "%"+searchValueForFatherName+"%",
                "%"+searchValueForBirthDate+"%",
                "%"+searchValueForBirthPlace+"%",
                "%"+searchValueForLivingPlace+"%",
                "%"+searchValueForEntryYear+"%",
                "%"+searchValueForGraduationRegion+"%",
                "%"+searchValueForEntryScore+"%",
                "%"+searchValueForFaculty+"%",
                "%"+searchValueForProfession+"%",
//                "%"+searchValueForGroup+"%",
                "%"+searchValueForSection+"%").get(0);
    }

//------------------------------------------------------------------------------------------------------
    @Override
    public int updateStudent(Student student) {

        if ( updateStudentInBduUser( student ) == 1 && updateStudentInStudent( student ) == 1 &&
                updateStudentInStudentSocialStatus( student ) == 1 )
            return 1;
        else
            return 0;
    }

    private int updateStudentInBduUser( Student student ){
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

    private int updateStudentInStudent( Student student ){

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

    private int updateStudentInStudentSocialStatus( Student student ){
        Set<Integer> updatedStatus = student.getSocialStatusSet();
        Set<Integer> currentStatusInDB = getSocialStatusSetById(student.getId());
        Set<Integer> statusToAdd = new HashSet<>();
        Set<Integer> statusToRemove = new HashSet<>();

//which statuses will be added
        for (Integer i: updatedStatus){
            boolean flag=false;
            for (Integer j: currentStatusInDB) {
                if (i.equals(j)) flag = true;
            }
            if (!flag)
                statusToAdd.add(i);
        }
//which statuses will be removed
        for (Integer i: currentStatusInDB){
            boolean flag=false;
            for (Integer j: updatedStatus) {
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
    public Set<String> getFacultySet(int creationYear){
        String sql = "SELECT distinct(faculty) FROM groups WHERE creation_year = ?";

        List<String> facultyList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString(1)),
                creationYear);

        return new HashSet<>(facultyList);
    }

    public Set<String> getProfessionSet(int creationYear, String faculty){
        String sql = "SELECT distinct(profession) FROM groups WHERE creation_year = ? and faculty=?";

        List<String> professionList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString(1)),
                creationYear, faculty);

        return new HashSet<>(professionList);
    }

    public Set<String> getSectionSet(int creationYear, String faculty, String profession){
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
            student.setGroupId(resultSet.getInt("group_id"));
            student.setScholarshipStatus(resultSet.getInt("scholarship_status"));
            student.setEntryYear(resultSet.getInt("entry_year"));
            return student;
        }
    }


    private class GroupMapper implements RowMapper<Group> {


        @Override
        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            Group group = new Group();
            group.setId(resultSet.getLong("group_id"));
            group.setName(resultSet.getString("group_name"));
            group.setCreationYear(resultSet.getInt("creation_year"));
            group.setFaculty(resultSet.getString("faculty"));
            group.setProfession(resultSet.getString("profession"));
            group.setSection(resultSet.getString("section"));
            group.setStudentNumer(resultSet.getInt("student_number"));

            return group;
        }
    }


    private Set<Integer> getSocialStatusSetById( long userId ){
        List<Integer> socialStatusList = jdbcTemplate.query( SQLqueries.GET_SOCIAL_STATUS_SET_OF_STUDENT_BY_USER_ID,
                ((resultSet, i) -> resultSet.getInt(1)),
                userId);
        return new HashSet<>(socialStatusList);
    }



    @Override
    public int getNumberOfAllGroups() {
        System.out.println("getNumberOfAllGroups");
        return jdbcTemplate.query(SQLqueries.GET_THE_NUMBER_OF_ALL_GROUPS,
                ((resultSet, i) -> resultSet.getInt(1))
        ).get(0);

    }


    @Override
    public int getNumberOfFilteredGroups(String searchParam) {
        String myQuery = " SELECT count(*) FROM groups  WHERE LOWER(group_name) like LOWER (?)";
        return jdbcTemplate.query(myQuery, (resultSet , i) -> resultSet.getInt(1), "%"+searchParam+"%").get(0);
    }


    @Override
    public List<Group> getFilteredGroupList(String searchParam, int startRow, int endRow) {

        String myquery = "select * from(" +
                "select row_number() over(order by group_id) as rownum, * " +
                "from groups " +
                "where lower(group_name) like lower(?) " +
                ") as sub  " +
                "where sub.rownum between ? and ? ";

        List<Group> groupsList = jdbcTemplate.query(myquery, new GroupMapper() , "%"+searchParam+"%" , startRow , endRow);

        System.out.println(groupsList);
        return groupsList;
    }





//-----------------------------------------------------------------------------------------------


    @Override
    public int getNumberOfStudentsOfIdenticalGroup(long groupId) {

        return jdbcTemplate.query(SQLqueries.GET_THE_NUMBER_OF_STUDENTS_OF_IDENTICAL_GROUP,
                (resultSet, i) -> resultSet.getInt(1),
                groupId).get(0);

    }


    @Override
    public int getNumberOfFilteredStudentsOfIdenticalGroup(String searchValue , long groupId) {
        String sql = "select count(*) " +
                "from bdu_user bu join student s on bu.user_id = s.user_id " +
                "where lower(bu.name) like lower(?) and group_id = ? ";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getInt(1),
                "%"+searchValue+"%",
                groupId
        ).get(0);
    }



    @Override
    public List<Student> getStudentsOfIdenticalGroup(long groupId , String searchParam, int startRow, int endRow) {

        String sql = " select * from( " +
                "select row_number() over(order by s.user_id) as rownum, * " +
                "from bdu_user bu join student s on bu.user_id = s.user_id " +
                "where lower(bu.name) like lower(?) and s.group_id = ? " +
                ") as sub " +
                "where sub.rownum between ? and ?";

        List<Student> studentList = jdbcTemplate.query(sql, new StudentMapper(), "%"+searchParam+"%",
                groupId,
                startRow,
                endRow);

        System.out.println("in getStudentsOfIdenticalGroup metod " + studentList);

        return  studentList;
    }


//---------------------------------------------------------------------------------------------------


    // to create a group
    @Override
    public int getNumberOfAllStudentsNotGrouped() {
        return jdbcTemplate.query(SQLqueries.GET_THE_NUMBER_OF_STUDENTS_NOT_GROUPED,
                ((resultSet, i) -> resultSet.getInt(1))
        ).get(0);
    }

    @Override
    public int getNumberOfFilteredStudentsNotGrouped(String searchValueForName, String searchValueForSurname, String searchValueForFatherName,
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
                        "/*lower(s.group_id) like lower(?) and*/ " +
                        "lower(s.section) like lower(?) and s.group_id is null ";


        return jdbcTemplate.query(sql,
                ((resultSet, i)-> resultSet.getInt(1)),
                "%"+searchValueForName+"%",
                "%"+searchValueForSurname+"%",
                "%"+searchValueForFatherName+"%",
                "%"+searchValueForBirthDate+"%",
                "%"+searchValueForBirthPlace+"%",
                "%"+searchValueForLivingPlace+"%",
                "%"+searchValueForEntryYear+"%",
                "%"+searchValueForGraduationRegion+"%",
                "%"+searchValueForEntryScore+"%",
                "%"+searchValueForFaculty+"%",
                "%"+searchValueForProfession+"%",
//                "%"+searchValueForGroup+"%",
                "%"+searchValueForSection+"%").get(0);

    }


    @Override
    public List<Student> getFilteredStudentListNotGrouped(int beginRow, int endRow,
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
                        "/*lower(s.group_id) like lower(?) and*/ " +
                        "lower(s.section) like lower(?) and s.group_id is null " +
                        ") as sub " +
                        "where sub.rownum between ? and ?";

        List<Student> studentList = jdbcTemplate.query(sql,
                new StudentMapper(),
                "%"+searchValueForName+"%",
                "%"+searchValueForSurname+"%",
                "%"+searchValueForFatherName+"%",
                "%"+searchValueForBirthDate+"%",
                "%"+searchValueForBirthPlace+"%",
                "%"+searchValueForLivingPlace+"%",
                "%"+searchValueForEntryYear+"%",
                "%"+searchValueForGraduationRegion+"%",
                "%"+searchValueForEntryScore+"%",
                "%"+searchValueForFaculty+"%",
                "%"+searchValueForProfession+"%",
//                "%"+searchValueForGroup+"%",
                "%"+searchValueForSection+"%",
                beginRow,
                endRow);


        return studentList;
    }


//    @Override
//    public List<Group> groupStudents(List<Student> studentList, List<Long> groupIdList) {
////            long firstId = idList.get(0);
////
////            String queryForProfession = "select profession " +
////                    "from student where user_id = ? ";
////
////
////
////            String queryForSection = "select section " +
////                    " from student where user_id = ? " ;
//
//        Student student = new Student();
//        List<Student> higherScoredStudentList = new ArrayList<>();
//        List<Student> middleScoredStudentList = new ArrayList<>();
//        List<Student> lowerScoredStudentList = new ArrayList<>();
//
//
//        int counter = 0;
//        int groupCount =  groupIdList.size();
//        int groupCapacity;
//        int totalStudentCount = studentList.size();
//
//        Collections.sort(studentList , new Student.SortbyEntryScore());
//
//
//
//        if(totalStudentCount % groupCount == 0){
//
//            groupCapacity = totalStudentCount / groupCount;
//
//
//            while( counter < groupCapacity){
//                higherScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//            while(counter < (studentList.size()-groupCapacity)){
//                middleScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//
//            while(counter < studentList.size()){
//
//                lowerScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//
//
//            for (int j = 0; j < groupCount; j++){
//
//                counter = 0;
//                int currentMemberCount = 0;
//
//                while(counter != groupCapacity){
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//                    higherScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//                    middleScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//
//                    lowerScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//                    counter++;
//                }
//
//            }
//
//
//        } else {
//
//            int increment;
//            int increasedStudentCount  = 0;
//            int i = 1;
//            while(totalStudentCount % groupCount != 0) {
//                i++;
//                increasedStudentCount = totalStudentCount + i;
//            }
//
//
//            increment = i;
//
//            Student student1 = new Student();
//
//            while(i != 0){
//                studentList.add(student1);
//                i--;
//            }
//
//
//            groupCapacity = totalStudentCount / groupCount;
//
//
//            while( counter < groupCapacity){
//
//                while(totalStudentCount == increasedStudentCount){
//                    studentList.remove(increasedStudentCount);
//                    increasedStudentCount--;
//                }
//                higherScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//            while(counter < (studentList.size()-groupCapacity)){
//                middleScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//
//            while(counter < studentList.size()){
//
//                lowerScoredStudentList.add(studentList.get(counter));
//                counter++;
//            }
//
//
//            for (int j = 0; j < groupCount; j++){
//
//                counter = 0;
//                int currentMemberCount = 0;
//
//                while(counter != groupCapacity){
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//                    higherScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//                    middleScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//                    ++currentMemberCount;
//                    if(currentMemberCount > groupCapacity)
//                        break;
//
//                    lowerScoredStudentList.get(counter).setGroupId(groupIdList.get(j));
//
//                    counter++;
//                }
//
//            }
//
//
//
//        }
//
//
//        return null;
//    }


    //---------------------------------------------------------------------------------------------------



    //TODO 1) qrup cedvelinde yeni qrup yarat ve hemen yaradilan
    //TODO    qrupun id'sini gotur metod vasitesi ile return et.
    public long createGroup(String profession , String section , String eduType, int year , int studentCount){

        String sql = " INSERT INTO public.groups(" +
                "group_id, group_name, creation_year, faculty, profession, section, student_number, education_type)" +
                "VALUES (nextval('group_sequence'), null , ? , 'Physics', ? , ? , ? , ?) " +
                "returning group_id ";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setString(2, profession);
            ps.setString(3,section);
            ps.setInt(4, studentCount);
            ps.setString(5,eduType);
            return ps;
        } , keyHolder);


        System.out.println(keyHolder.getKey());
        return (long) keyHolder.getKey();
    }

    @Override
    public List<Group> groupStudents(String profession, String section, String eduType, int year, int groupCnt) {

        String sql = " select * " +
                "from student s full outer join bdu_user bu on s.user_id = bu.user_id\n" +
                "where s.profession = ? and s.section = ? and s.education_type = ? " ;
        List<Student>  studentList = new ArrayList<>();
                studentList = jdbcTemplate.query(sql,
                new StudentMapper(),
                profession,
                section,
                eduType);





        Student student = new Student();
        List<Student> higherScoredStudentList = new ArrayList<>();
        List<Student> middleScoredStudentList = new ArrayList<>();
        List<Student> lowerScoredStudentList = new ArrayList<>();
        List<Group> newGroups = new ArrayList<>();
        Group group = new Group();

        int counter = 0;
        int groupCount = groupCnt;
        int groupCapacity;
        int totalStudentCount = studentList.size();
        long groupId;
        int currentMemberCount = 0;

        Collections.sort(studentList , new Student.SortbyEntryScore());



        if(totalStudentCount % groupCount == 0){

            groupCapacity = totalStudentCount / groupCount;


            while( counter < groupCapacity){
                higherScoredStudentList.add(studentList.get(counter));
                counter++;
            }

            while(counter < (studentList.size()-groupCapacity)){
                middleScoredStudentList.add(studentList.get(counter));
                counter++;
            }


            while(counter < studentList.size()){

                lowerScoredStudentList.add(studentList.get(counter));
                counter++;
            }



            for (int j = 0; j < groupCount; j++){

                groupId = createGroup(profession , section  , eduType , year , groupCapacity);
                group.setId(groupId);
                group.setProfession(profession);
                group.setSection(section);
                group.setEduType(eduType);
                group.setCreationYear(year);
                group.setStudentNumer(groupCapacity);
                newGroups.add(group);
                counter = 0;



                while(counter != groupCapacity){

                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;
                    higherScoredStudentList.get(counter).setGroupId(groupId);



                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;
                    middleScoredStudentList.get(counter).setGroupId(groupId);

                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;

                    lowerScoredStudentList.get(counter).setGroupId(groupId);

                    counter++;
                }

            }

            return newGroups;

        } else {

            int increment;
            int increasedStudentCount  = 0;
            int i = 1;
            while(totalStudentCount % groupCount != 0) {
                i++;
                increasedStudentCount = totalStudentCount + i;
            }


            increment = i;

            Student student1 = new Student();

            while(i != 0){
                studentList.add(student1);
                i--;
            }


            groupCapacity = totalStudentCount / groupCount;


            while( counter < groupCapacity){

                while(totalStudentCount == increasedStudentCount){
                    studentList.remove(increasedStudentCount);
                    increasedStudentCount--;
                }
                higherScoredStudentList.add(studentList.get(counter));
                counter++;
            }

            while(counter < (studentList.size()-groupCapacity)){
                middleScoredStudentList.add(studentList.get(counter));
                counter++;
            }


            while(counter < studentList.size()){

                lowerScoredStudentList.add(studentList.get(counter));
                counter++;
            }


            for (int j = 0; j < groupCount; j++){

                groupId = createGroup(profession , section  , eduType , year , groupCapacity);
                group.setId(groupId);
                group.setProfession(profession);
                group.setSection(section);
                group.setEduType(eduType);
                group.setCreationYear(year);
                group.setStudentNumer(groupCapacity);
                newGroups.add(group);
                counter = 0;



                while(counter != groupCapacity){

                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;
                    higherScoredStudentList.get(counter).setGroupId(groupId);


                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;
                    middleScoredStudentList.get(counter).setGroupId(groupId);

                    ++currentMemberCount;
                    if(currentMemberCount > groupCapacity)
                        break;

                    lowerScoredStudentList.get(counter).setGroupId(groupId);

                    counter++;
                }

            }


            return null;
        }

    }







}
