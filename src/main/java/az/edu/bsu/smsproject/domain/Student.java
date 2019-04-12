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
    private int educationYear;
    private int scholarshipStatus;

    public Student(long id, String name, long roleId, String surname, String email, String password, String phoneNumber, String faculty, char gender, String idCardNumber, String idCardFinCode, String fatherName, LocalDate birthDate, String birthPlace, String livingPlace, String officialHome, String socialStatusId, String parentPhoneNumber, String gaduatedRegion, String gaduatedSchool, int entryIdNumber, int entryScore, String educationType, String profession, String section, String group, int educationYear, int scholarshipStatus) {
        super(id, name, roleId, surname, email, password, phoneNumber, faculty, gender);
        this.idCardNumber = idCardNumber;
        this.idCardFinCode = idCardFinCode;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.livingPlace = livingPlace;
        this.officialHome = officialHome;
        this.socialStatusId = socialStatusId;
        this.parentPhoneNumber = parentPhoneNumber;
        this.gaduatedRegion = gaduatedRegion;
        this.gaduatedSchool = gaduatedSchool;
        this.entryIdNumber = entryIdNumber;
        this.entryScore = entryScore;
        this.educationType = educationType;
        this.profession = profession;
        this.section = section;
        this.group = group;
        this.educationYear = educationYear;
        this.scholarshipStatus = scholarshipStatus;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "idCardNumber='" + idCardNumber + '\'' +
                ", idCardFinCode='" + idCardFinCode + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", livingPlace='" + livingPlace + '\'' +
                ", officialHome='" + officialHome + '\'' +
                ", socialStatusId='" + socialStatusId + '\'' +
                ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
                ", gaduatedRegion='" + gaduatedRegion + '\'' +
                ", gaduatedSchool='" + gaduatedSchool + '\'' +
                ", entryIdNumber=" + entryIdNumber +
                ", entryScore=" + entryScore +
                ", educationType='" + educationType + '\'' +
                ", profession='" + profession + '\'' +
                ", section='" + section + '\'' +
                ", group='" + group + '\'' +
                ", educationYear=" + educationYear +
                ", scholarshipStatus=" + scholarshipStatus +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardFinCode() {
        return idCardFinCode;
    }

    public void setIdCardFinCode(String idCardFinCode) {
        this.idCardFinCode = idCardFinCode;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    public String getOfficialHome() {
        return officialHome;
    }

    public void setOfficialHome(String officialHome) {
        this.officialHome = officialHome;
    }

    public String getSocialStatusId() {
        return socialStatusId;
    }

    public void setSocialStatusId(String socialStatusId) {
        this.socialStatusId = socialStatusId;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public String getGaduatedRegion() {
        return gaduatedRegion;
    }

    public void setGaduatedRegion(String gaduatedRegion) {
        this.gaduatedRegion = gaduatedRegion;
    }

    public String getGaduatedSchool() {
        return gaduatedSchool;
    }

    public void setGaduatedSchool(String gaduatedSchool) {
        this.gaduatedSchool = gaduatedSchool;
    }

    public int getEntryIdNumber() {
        return entryIdNumber;
    }

    public void setEntryIdNumber(int entryIdNumber) {
        this.entryIdNumber = entryIdNumber;
    }

    public int getEntryScore() {
        return entryScore;
    }

    public void setEntryScore(int entryScore) {
        this.entryScore = entryScore;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }

    public int getScholarshipStatus() {
        return scholarshipStatus;
    }

    public void setScholarshipStatus(int scholarshipStatus) {
        this.scholarshipStatus = scholarshipStatus;
    }

}
