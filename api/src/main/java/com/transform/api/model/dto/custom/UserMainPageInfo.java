package com.transform.api.model.dto.custom;

import com.transform.api.model.dto.UserBaseInfoDTO;
import lombok.Data;

@Data
public class UserMainPageInfo {

    public Object friendsList;
    public Object fansList;
    public Object userMomentInfoList;
    public UserBaseInfoDTO userBaseInfoDTO;

    public UserMainPageInfo(Object friendsList, Object fansList, Object userMomentInfoList, UserBaseInfoDTO userBaseInfoDTO) {
        this.friendsList = friendsList;
        this.fansList = fansList;
        this.userMomentInfoList = userMomentInfoList;
        this.userBaseInfoDTO = userBaseInfoDTO;
    }

}
