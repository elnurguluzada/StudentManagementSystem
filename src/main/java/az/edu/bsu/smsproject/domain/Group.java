package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;

import java.io.Serializable;
import java.util.List;

public class Group extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -2309096077691341278L;


    private static final long serialVersionUID = 522454508097397247L;
    private int creationYear;
    private String faculty;
    private String profession;
    private String section;
    private int studentNumber;

    public Group(long id, String name, Status status, int creationYear, String faculty, String profession, String section, int studentNumber) {
        super(id, name, status);
        this.creationYear = creationYear;
        this.faculty = faculty;
        this.profession = profession;
        this.section = section;
        this.eduType = eduType;
        this.studentNumer = studentNumer;
    }

    public Group() {
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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

    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    public int getStudentNumer() {
        return studentNumer;
    }

    public void setStudentNumer(int studentNumer) {
        this.studentNumer = studentNumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return studentNumer == group.studentNumer &&
                Objects.equals(creationYear, group.creationYear) &&
                Objects.equals(faculty, group.faculty) &&
                Objects.equals(profession, group.profession) &&
                Objects.equals(section, group.section) &&
                Objects.equals(eduType, group.eduType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationYear, faculty, profession, section, eduType, studentNumer);
    }

    @Override
    public String toString() {
        return "Group{" +
                "creationYear='" + creationYear + '\'' +
                ", faculty='" + faculty + '\'' +
                ", profession='" + profession + '\'' +
                ", section='" + section + '\'' +
                ", eduType='" + eduType + '\'' +
                ", studentNumer=" + studentNumer +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
