package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.repository.CommonRepository;
import az.edu.bsu.smsproject.repository.SQLqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommonRepositoryImpl implements CommonRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getSectionList(int year) {
        List<String> sectionList;

        sectionList = jdbcTemplate.query( SQLqueries.GET_SECTION_LIST_BY_YEAR,
                ((resultSet, i) -> resultSet.getString("section")),
                year);

        return sectionList;
    }

    @Override
    public List<String> getFacultyList(int year) {
        return null;
    }

    @Override
    public List<String> getGroupList(int year) {
        return null;
    }
}
