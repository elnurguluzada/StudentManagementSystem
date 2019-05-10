package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.domain.Group;

import java.util.List;

public interface GroupService {
    public Group add(Group group);
    public Group getGroupById(long groupId);
    public List<Group> getAllGroupList();
    public List<Group> getFilteredGroupList();
    public Group updateGroup(Group group);
    public boolean deleteGroup(long groupId);

    public int getNumberOfAllGroups();
    public int getNumberOfFilteredGroups();
}
