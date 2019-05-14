package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Enums.Status;
import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.repository.GroupRepository;
import az.edu.bsu.smsproject.repository.SQLqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Group add(Group group) {
        return null;
    }

    @Override
    public Group getGroupById(long groupId) {
        return null;
    }

    @Override
    public List<Group> getAllGroupList() {
        String sql = "select group_id, group_name, creation_year, faculty, profession, section from groups";
        return jdbcTemplate.query(sql, groupMapper);
    }

    @Override
    public List<Group> getFilteredGroupList(int begin, int end, String name, String year, String faculty, String profession, String section) {
        String sql = "select * from( "+
                "SELECT row_number() over(order by group_id) as rownum, * FROM groups " +
                    "WHERE lower(group_name) LIKE lower(?) AND " +
                    "lower(to_char(creation_year, '9999')) LIKE lower(?) AND " +
                    "lower(faculty) LIKE lower(?) AND " +
                    "lower(profession) LIKE lower(?) AND " +
                    "lower(section) LIKE lower(?) "+
                    "ORDER BY group_id"+
                ") as sub "+
                "where sub.rownum between ? and ?";

        return jdbcTemplate.query(sql, groupMapper,
                "%"+name+"%",
                "%"+year+"%",
                "%"+faculty+"%",
                "%"+profession+"%",
                "%"+section+"%",
                begin,
                end);
    }

    @Override
    public int getNumberOfAllGroups() {
        String sql = "SELECT count(*) FROM groups";
        return jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getInt(1))).get(0);
    }

    @Override
    public int getNumberOfFilteredGroups(String name, String year, String faculty, String profession, String section) {
        String sql = "SELECT count(*) FROM groups " +
                "WHERE lower(group_name) LIKE lower(?) AND " +
                "lower(to_char(creation_year, '9999')) LIKE lower(?) AND " +
                "lower(faculty) LIKE lower(?) AND " +
                "lower(profession) LIKE lower(?) AND " +
                "lower(section) LIKE lower(?) ";

        return jdbcTemplate.query(sql,
                (resultSet, i) -> (resultSet.getInt(1)),
                "%"+name+"%",
                "%"+year+"%",
                "%"+faculty+"%",
                "%"+profession+"%",
                "%"+section+"%").get(0);
    }

    //    ***********************************
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

        List<Group> groupsList = jdbcTemplate.query(myquery, groupMapper , "%"+searchParam+"%" , startRow , endRow);

        System.out.println(groupsList);
        return groupsList;
    }

//    ***********************************

    @Override
    public Group updateGroup(Group group) {
        return null;
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return false;
    }



    private RowMapper<Group> groupMapper = (resultSet, i) -> {
        long groupId = resultSet.getLong("group_id");
        String groupName = resultSet.getString("group_name");
        int creationYear = resultSet.getInt("creation_year");
        String faculty = resultSet.getString("faculty");
        String profession = resultSet.getString("profession");
        String section = resultSet.getString("section");
        int studentNumber = resultSet.getInt("student_number"); //todo
        return new Group(groupId, groupName, Status.ACTIVE, creationYear, faculty, profession, section, studentNumber);
    };



}
