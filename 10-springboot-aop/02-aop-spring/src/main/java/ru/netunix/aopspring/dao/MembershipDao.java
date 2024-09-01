package ru.netunix.aopspring.dao;

import ru.netunix.aopspring.entity.Account;

public interface MembershipDao {
    public boolean addAdditionalAccount();
    public boolean addAdditionalAccount(Account account);
}
