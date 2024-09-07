package ru.netunix.aopspring.dao;

import ru.netunix.aopspring.entity.Account;

public interface AccountDao {
    void addAccount();

    void addAccount(Account account, boolean vipFlag);

    void setName(String name);
    void setServiceCode(String serviceCode);
    String getName();
    String getServiceCode();
}
