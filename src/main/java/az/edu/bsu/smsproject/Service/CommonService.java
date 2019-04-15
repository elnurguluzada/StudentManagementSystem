package az.edu.bsu.smsproject.Service;

import java.util.List;

public interface CommonService {

    public List<String> getSectionList(int year);

    public List<String> getFacultyList(int year);

    public List<String> getGroupList(int year);

}
