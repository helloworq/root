package com.transform.api.service;

public interface ILoginService {
    String addAccount(String userAccount,String userPassword);

    boolean checkPassword(String userAccount,String userPassword);

    String deleteAccount(String userAccount,String userPassword);

    boolean isUserAccountUnique(String userAccount);
}
