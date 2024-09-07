package ru.netunix.aopspring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.netunix.aopspring.entity.Account;

@Repository
@Slf4j
public class AccountDaoImpl implements AccountDao {
    private String name;
    private String serviceCode;
    @Override
    public void addAccount() {
        System.out.println(getClass() + " Doing job : adding an account");

    }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + " Doing job : adding an account " + account
                + " with vipFlag = " + vipFlag);
    }

    public void setName(String name) {
        System.out.println(getClass() + " Doing job : setName()");
        this.name = name;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " Doing job : setServiceCode()");
        this.serviceCode = serviceCode;
    }

    public String getName() {
        System.out.println(getClass() + " Doing job : getName()");
        return name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " Doing job : getServiceCode()");
        return serviceCode;
    }
}
