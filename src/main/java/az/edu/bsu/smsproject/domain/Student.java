package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 3743824363989869865L;

    private String fatherName;
    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "birth date is required")
    private LocalDate birthDate;
    @NotBlank(message = "Birth place is required")
    private String birthPlace;
    @NotBlank(message = "Living place is required")
    private String livingPlace;
    @NotBlank(message = "Official home address is required")
    private String officialHome;

    @Digits(integer = 8, fraction = 0, message = "Invalid Id card number")
    private String idCardNumber;
//  @Pattern(regexp = "")         //todo learn regular expression
    private String idCardFinCode;

    private String socialStatusId;
    @Digits(integer = 10, fraction = 0, message = "Invalid telephone number")
    private String parentPhoneNumber;
    @NotBlank(message = "Graduated region id required")
    private String graduatedRegion;
    @NotBlank(message = "Graduated school id required")
    private String graduatedSchool;

    //todo compete missing @Patterns
    private int entryIdNumber;
    private int entryScore;
    private String educationType;
    private boolean presidentialScholarship;            // true -> prezident teqaudcusu
    private boolean dovletSifarisli;                    // true -> dovlet sifarisli false -> odenisli
    private LocalDate entryYear;
    private String profession;
    private String section;
    private String group;
    private int scholarshipStatus;


    public Student(long id, String name, Status status, long roleId, String surname, String email, String password, String phoneNumber, String faculty, char gender, String fatherName, @Past @NotNull(message = "birth date is required") LocalDate birthDate, @NotBlank(message = "Birth place is required") String birthPlace, @NotBlank(message = "Living place is required") String livingPlace, @NotBlank(message = "Official home address is required") String officialHome, @Digits(integer = 8, fraction = 0, message = "Invalid Id card number") String idCardNumber, String idCardFinCode, String socialStatusId, @Digits(integer = 10, fraction = 0, message = "Invalid telephone number") String parentPhoneNumber, @NotBlank(message = "Graduated region id required") String graduatedRegion, @NotBlank(message = "Graduated school id required") String graduatedSchool, int entryIdNumber, int entryScore, String educationType, boolean presidentialScholarship, boolean dovletSifarisli, LocalDate entryYear, String profession, String section, String group, int scholarshipStatus) {
        super(id, name, status, roleId, surname, email, password, phoneNumber, faculty, gender);
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.livingPlace = livingPlace;
        this.officialHome = officialHome;
        this.idCardNumber = idCardNumber;
        this.idCardFinCode = idCardFinCode;
        this.socialStatusId = socialStatusId;
        this.parentPhoneNumber = parentPhoneNumber;
        this.graduatedRegion = graduatedRegion;
        this.graduatedSchool = graduatedSchool;
        this.entryIdNumber = entryIdNumber;
        this.entryScore = entryScore;
        this.educationType = educationType;
        this.presidentialScholarship = presidentialScholarship;
        this.dovletSifarisli = dovletSifarisli;
        this.entryYear = entryYear;
        this.profession = profession;
        this.section = section;
        this.group = group;
        this.scholarshipStatus = scholarshipStatus;
    }

    public Student(long roleId, String surname, String email, String password, String phoneNumber, String faculty, char gender, String fatherName, @Past @NotNull(message = "birth date is required") LocalDate birthDate, @NotBlank(message = "Birth place is required") String birthPlace, @NotBlank(message = "Living place is required") String livingPlace, @NotBlank(message = "Official home address is required") String officialHome, @Digits(integer = 8, fraction = 0, message = "Invalid Id card number") String idCardNumber, String idCardFinCode, String socialStatusId, @Digits(integer = 10, fraction = 0, message = "Invalid telephone number") String parentPhoneNumber, @NotBlank(message = "Graduated region id required") String graduatedRegion, @NotBlank(message = "Graduated school id required") String graduatedSchool, int entryIdNumber, int entryScore, String educationType, boolean presidentialScholarship, boolean dovletSifarisli, LocalDate entryYear, String profession, String section, String group, int scholarshipStatus) {
        super(roleId, surname, email, password, phoneNumber, faculty, gender);
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.livingPlace = livingPlace;
        this.officialHome = officialHome;
        this.idCardNumber = idCardNumber;
        this.idCardFinCode = idCardFinCode;
        this.socialStatusId = socialStatusId;
        this.parentPhoneNumber = parentPhoneNumber;
        this.graduatedRegion = graduatedRegion;
        this.graduatedSchool = graduatedSchool;
        this.entryIdNumber = entryIdNumber;
        this.entryScore = entryScore;
        this.educationType = educationType;
        this.presidentialScholarship = presidentialScholarship;
        this.dovletSifarisli = dovletSifarisli;
        this.entryYear = entryYear;
        this.profession = profession;
        this.section = section;
        this.group = group;
        this.scholarshipStatus = scholarshipStatus;
    }

    public Student(String fatherName, @Past @NotNull(message = "birth date is required") LocalDate birthDate, @NotBlank(message = "Birth place is required") String birthPlace, @NotBlank(message = "Living place is required") String livingPlace, @NotBlank(message = "Official home address is required") String officialHome, @Digits(integer = 8, fraction = 0, message = "Invalid Id card number") String idCardNumber, String idCardFinCode, String socialStatusId, @Digits(integer = 10, fraction = 0, message = "Invalid telephone number") String parentPhoneNumber, @NotBlank(message = "Graduated region id required") String graduatedRegion, @NotBlank(message = "Graduated school id required") String graduatedSchool, int entryIdNumber, int entryScore, String educationType, boolean presidentialScholarship, boolean dovletSifarisli, LocalDate entryYear, String profession, String section, String group, int scholarshipStatus) {
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.livingPlace = livingPlace;
        this.officialHome = officialHome;
        this.idCardNumber = idCardNumber;
        this.idCardFinCode = idCardFinCode;
        this.socialStatusId = socialStatusId;
        this.parentPhoneNumber = parentPhoneNumber;
        this.graduatedRegion = graduatedRegion;
        this.graduatedSchool = graduatedSchool;
        this.entryIdNumber = entryIdNumber;
        this.entryScore = entryScore;
        this.educationType = educationType;
        this.presidentialScholarship = presidentialScholarship;
        this.dovletSifarisli = dovletSifarisli;
        this.entryYear = entryYear;
        this.profession = profession;
        this.section = section;
        this.group = group;
        this.scholarshipStatus = scholarshipStatus;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", livingPlace='" + livingPlace + '\'' +
                ", officialHome='" + officialHome + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", idCardFinCode='" + idCardFinCode + '\'' +
                ", socialStatusId='" + socialStatusId + '\'' +
                ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
                ", graduatedRegion='" + graduatedRegion + '\'' +
                ", graduatedSchool='" + graduatedSchool + '\'' +
                ", entryIdNumber=" + entryIdNumber +
                ", entryScore=" + entryScore +
                ", educationType='" + educationType + '\'' +
                ", presidentialScholarship=" + presidentialScholarship +
                ", dovletSifarisli=" + dovletSifarisli +
                ", entryYear=" + entryYear +
                ", profession='" + profession + '\'' +
                ", section='" + section + '\'' +
                ", group='" + group + '\'' +
                ", scholarshipStatus=" + scholarshipStatus +
                '}';
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

    public String getGraduatedRegion() {
        return graduatedRegion;
    }

    public void setGraduatedRegion(String graduatedRegion) {
        this.graduatedRegion = graduatedRegion;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
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

    public boolean isPresidentialScholarship() {
        return presidentialScholarship;
    }

    public void setPresidentialScholarship(boolean presidentialScholarship) {
        this.presidentialScholarship = presidentialScholarship;
    }

    public boolean isDovletSifarisli() {
        return dovletSifarisli;
    }

    public void setDovletSifarisli(boolean dovletSifarisli) {
        this.dovletSifarisli = dovletSifarisli;
    }

    public LocalDate getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(LocalDate entryYear) {
        this.entryYear = entryYear;
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

    public int getScholarshipStatus() {
        return scholarshipStatus;
    }

    public void setScholarshipStatus(int scholarshipStatus) {
        this.scholarshipStatus = scholarshipStatus;
    }
}
