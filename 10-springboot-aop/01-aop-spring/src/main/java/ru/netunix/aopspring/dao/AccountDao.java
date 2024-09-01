package ru.netunix.aopspring.dao;

import ru.netunix.aopspring.entity.Account;

public interface AccountDao {
    void addAccount();
    void addAccount(Account account, boolean vipFlag);
}
