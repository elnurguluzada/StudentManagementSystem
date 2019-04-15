package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    private final JdbcTemplate jdbcTemplate;
    private final CommonRepository commonRepository;

    @Autowired
    public CommonServiceImpl(CommonRepository commonRepository, JdbcTemplate jdbcTemplate) {
        this.commonRepository = commonRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<String> getSectionList(int year) {
        return commonRepository.getSectionList( year );
    }

    @Override
    public List<String> getFacultyList(int year) {
        return commonRepository.getFacultyList( year );
    }

    @Override
    public List<String> getGroupList(int year) {
        return commonRepository.getGroupList( year );
    }
}
