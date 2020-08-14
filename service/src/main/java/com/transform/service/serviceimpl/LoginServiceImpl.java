package com.transform.service.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.transform.api.model.entiy.UserAccount;
import com.transform.api.service.ILoginService;
import com.transform.service.dao.UserAccountRepositry;
import com.transform.service.util.EncodeString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Component
public class LoginServiceImpl implements ILoginService {
    @Autowired
    UserAccountRepositry userAccountRepositry;
    @Autowired
    EncodeString encodeString;
    /**
     * 根据输入的账户密码添加进数据库，密码使用SHA算法加密
     * @param userAccount
     * @param userPassword
     * @return
     */
    @Override
    public String addAccount(String userAccount, String userPassword){
        UserAccount userAccountEntiy=new UserAccount();
        userAccountEntiy.setUseraAcount(userAccount);
        userAccountEntiy.setUserPassword(encodeString.encodePasswordWithSHA(userPassword));
        userAccountRepositry.save(userAccountEntiy);
        return "success";
    }

    /**
     * 删除账户，用户是否为本人的判断在控制器中完成，尽量减少访问数据库的次数
     * @param userAccount
     * @param userPassword
     * @return
     */
    @Override
    public String deleteAccount(String userAccount, String userPassword) {
        String id=userAccountRepositry.findPasswordbyUserAccount(userAccount);
        userAccountRepositry.deleteById(id);
        return "success";
    }

    /**
     * 根据传入的账号密码判断是否与数据库中的数据一致，密码需要使用SHA加密进行判断
     * @param userAccount
     * @param userPassword
     * @return
     */
    @Override
    public boolean checkPassword(String userAccount, String userPassword){
        String password=userAccountRepositry.findPasswordbyUserAccount(userAccount);
        return encodeString.encodePasswordWithSHA(userPassword).equals(password);
    }

}
