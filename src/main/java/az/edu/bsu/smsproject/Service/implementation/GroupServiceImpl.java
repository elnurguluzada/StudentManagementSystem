package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.GroupService;
import az.edu.bsu.smsproject.domain.Group;
import az.edu.bsu.smsproject.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group add(Group group) {
        return groupRepository.add(group);
    }

    @Override
    public Group getGroupById(long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    @Override
    public List<Group> getAllGroupList() {
        return groupRepository.getAllGroupList();
    }

    @Override
    public List<Group> getFilteredGroupList() {
        return groupRepository.getFilteredGroupList();
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.updateGroup(group);
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return groupRepository.deleteGroup(groupId);
    }

    @Override
    public int getNumberOfAllGroups() {
        return groupRepository.getNumberOfAllGroups();
    }

    @Override
    public int getNumberOfFilteredGroups() {
        return groupRepository.getNumberOfFilteredGroups();
    }
}
