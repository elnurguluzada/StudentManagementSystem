package az.edu.bsu.smsproject.domain;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private static final long serialVersionUID = -2309096077691341278L;

    private long groupId;
    private String groupName;
    private int creationYear;
    private String faculty;
    private String profession;
    private List<String> sectionList;

    public Group(long groupId, String groupName, int creationYear, String faculty, String profession, List<String> sectionList) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.creationYear = creationYear;
        this.faculty = faculty;
        this.profession = profession;
        this.sectionList = sectionList;
    }

    public Group() {
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<String> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<String> sectionList) {
        this.sectionList = sectionList;
    }
}
