package az.edu.bsu.smsproject.Service;

import java.util.List;
import java.util.Set;

public interface CommonService {

    public Set<String> getFacultySet(int year);
    public Set<String> getProfessionSet(int year, String faculty);
    public Set<String> getSectionSet(int year, String faculty, String profession);

}
