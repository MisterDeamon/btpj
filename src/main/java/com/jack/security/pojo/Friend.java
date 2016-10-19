package com.jack.security.pojo;

import java.io.Serializable;

/**
 * Created by wajiangk on 10/11/2016.
 */
public class Friend extends BaseEntity implements Serializable {

    private String userId;
    private SecurityUser userFriend;
    private String friendCommentName;
    private FriendGroup friendGroup;

    public Friend() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SecurityUser getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(SecurityUser userFriend) {
        this.userFriend = userFriend;
    }

    public String getFriendCommentName() {
        return friendCommentName;
    }

    public void setFriendCommentName(String friendCommentName) {
        this.friendCommentName = friendCommentName;
    }

    public FriendGroup getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(FriendGroup friendGroup) {
        this.friendGroup = friendGroup;
    }
}
