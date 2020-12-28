package com.transform.api.model.dto.custom;

public class UserMainPageInfo {

    public Object friendsList;
    public Object fansList;
    public Object userMomentInfoList;

    public UserMainPageInfo(Object friendsList, Object fansList, Object userMomentInfoList) {
        this.friendsList = friendsList;
        this.fansList = fansList;
        this.userMomentInfoList = userMomentInfoList;
    }

    public Object getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(Object friendsList) {
        this.friendsList = friendsList;
    }

    public Object getFansList() {
        return fansList;
    }

    public void setFansList(Object fansList) {
        this.fansList = fansList;
    }

    public Object getUserMomentInfoList() {
        return userMomentInfoList;
    }

    public void setUserMomentInfoList(Object userMomentInfoList) {
        this.userMomentInfoList = userMomentInfoList;
    }

}
