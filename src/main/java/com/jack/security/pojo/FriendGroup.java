package com.jack.security.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 10/11/2016.
 */
public class FriendGroup extends BaseEntity implements Serializable {

    private String groupId;
    private String groupName;
    private int groupPriority;
    private String userId;
    private SecurityUser user;
    private List<Friend> friends;

    public FriendGroup() {
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupPriority() {
        return groupPriority;
    }

    public void setGroupPriority(int groupPriority) {
        this.groupPriority = groupPriority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SecurityUser getUser() {
        return user;
    }

    public void setUser(SecurityUser user) {
        this.user = user;
    }
}
