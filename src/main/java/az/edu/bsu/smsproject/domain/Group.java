package az.edu.bsu.smsproject.domain;

import az.edu.bsu.smsproject.domain.Enums.Status;

import java.io.Serializable;
import java.util.List;

public class Group extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -2309096077691341278L;

    private int creationYear;
    private String faculty;
    private String profession;
    private String section;

    public Group(long id, String name, Status status, int creationYear, String faculty, String profession, String section) {
        super(id, name, status);
        this.creationYear = creationYear;
        this.faculty = faculty;
        this.profession = profession;
        this.section = section;
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

    @Override
    public String toString() {
        return "Group{" +
                "creationYear=" + creationYear +
                ", faculty='" + faculty + '\'' +
                ", profession='" + profession + '\'' +
                ", section='" + section + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
