package az.edu.bsu.smsproject.Service;

import java.util.Set;

public interface CommonService {

    Set<String> getFacultySet(int year);
    Set<String> getProfessionSet(int year, String faculty);
    Set<String> getSectionSet(int year, String faculty, String profession);

    String getDefaultControllerNameByEmail(String email);
    String getDefaultControllerNameByRoleName(String roleName);
}
