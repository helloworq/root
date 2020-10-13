package com.transform.api.service;

import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentInfo;

import java.util.List;

public interface IMomentService {

    String uploadMoment(UserMomentInfoDTO userMomentInfo);

    UserMomentInfoDTO getUserMomentInfo(String id);

    List<UserMomentInfo> getAllUserMomentInfo();

    String deleteUserMoment(String id);
}
