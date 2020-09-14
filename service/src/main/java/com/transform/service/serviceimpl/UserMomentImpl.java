package com.transform.service.serviceimpl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.dto.UserMomentInfoDTO;
import com.transform.api.model.entiy.UserMomentInfo;
import com.transform.api.service.IMomentService;
import com.transform.service.dao.UserMomentInfoRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class UserMomentImpl implements IMomentService {
    @Autowired
    UserMomentInfoRepositry userMomentInfoRepositry;
    /**
     * 上传动态
     * @param userMomentInfoDTO
     * @return
     */
    @Override
    public String uploadMoment(UserMomentInfoDTO userMomentInfoDTO) {
        UserMomentInfo userMomentInfo=new UserMomentInfo();
        BeanUtils.copyProperties(userMomentInfoDTO,userMomentInfo);
        userMomentInfo.setPicIds(userMomentInfoDTO.getPicIds());

        userMomentInfoRepositry.save(userMomentInfo);
        return "success";
    }

    @Override
    public UserMomentInfoDTO getUserMomentInfo(String id) {
        UserMomentInfoDTO userMomentInfoDTO=new UserMomentInfoDTO();
        BeanUtils.copyProperties(userMomentInfoRepositry.getById(id),userMomentInfoDTO);
        return userMomentInfoDTO;
    }
}
