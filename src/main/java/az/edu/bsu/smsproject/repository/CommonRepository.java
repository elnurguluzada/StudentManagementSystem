package az.edu.bsu.smsproject.repository;

import java.util.List;

public interface CommonRepository {

    public List<String> getSectionList(int year);
    public List<String> getFacultyList(int year);
    public List<String> getGroupList(int year);

}
