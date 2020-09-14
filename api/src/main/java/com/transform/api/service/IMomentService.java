package com.transform.api.service;

import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentInfo;

public interface IMomentService {

    String uploadMoment(UserMomentInfoDTO userMomentInfo);

    UserMomentInfoDTO getUserMomentInfo(String id);
}
