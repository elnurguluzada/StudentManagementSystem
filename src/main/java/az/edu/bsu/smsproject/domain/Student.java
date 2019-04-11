package az.edu.bsu.smsproject.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 3743824363989869865L;

    private String idCardNumber;
    private String idCardFinCode;
    private String fatherName;
    private LocalDate birthDate;
    private String birthPlace;
    private String livingPlace;
    private String officialHome;
    private String socialStatusId;
    private String parentPhoneNumber;
    private String gaduatedRegion;
    private String gaduatedSchool;
    private int entryIdNumber;
    private int entryScore;
    private String educationType;
    private String profession;
    private String section;
    private String group;
//    private int educationYear;
    private int scholarshipStatus;

}
