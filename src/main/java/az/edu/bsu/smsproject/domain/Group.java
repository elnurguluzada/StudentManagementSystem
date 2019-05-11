package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;

import java.io.Serializable;
import java.util.Objects;

public class Group extends  BaseDomain implements Serializable {


    private static final long serialVersionUID = 522454508097397247L;
    private String creationYear;
    private String faculty;
    private String profession;
    private String section;
    private int studentNumer;


    public Group(long id, String name, Status status, String creationYear, String faculty, String profession, String section, int studentNumer) {
        super(id, name, status);
        this.creationYear = creationYear;
        this.faculty = faculty;
        this.profession = profession;
        this.section = section;
        this.studentNumer = studentNumer;
    }

    public Group() {
    }

    public String getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(String creationYear) {
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
        Group groups = (Group) o;
        return creationYear == groups.creationYear &&
                studentNumer == groups.studentNumer &&
                Objects.equals(faculty, groups.faculty) &&
                Objects.equals(profession, groups.profession) &&
                Objects.equals(section, groups.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationYear, faculty, profession, section, studentNumer);
    }


    @Override
    public String toString() {
        return "Groups{" +
                "creationYear=" + creationYear +
                ", faculty='" + faculty + '\'' +
                ", profession='" + profession + '\'' +
                ", section='" + section + '\'' +
                ", studentNumer=" + studentNumer +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

}
