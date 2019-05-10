package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        String sql = "select group_id, group_name, creation_year, faculty, profession, ";//todo
        return jdbcTemplate.query(sql, groupMapper);
    }

    @Override
    public List<Group> getFilteredGroupList() {
        return null;
    }

    @Override
    public Group updateGroup(Group group) {
        return null;
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return false;
    }

    @Override
    public int getNumberOfAllGroups() {
        String sql = "SELECT count(*) FROM groups";
        return jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getInt(1))).get(0);
    }

    @Override
    public int getNumberOfFilteredGroups() {
        return 0;
    }

    private RowMapper groupMapper = new RowMapper() {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            long groupId = resultSet.getLong("group_id");
            String groupName = resultSet.getString("group_name");
            int creationYear = resultSet.getInt("creation_year");
            String faculty = resultSet.getString("faculty");
            String profession = resultSet.getString("profession");

            return new Group();
        }
    };

}
