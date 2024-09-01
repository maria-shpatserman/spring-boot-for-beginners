package ru.netunix.aopspring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.netunix.aopspring.entity.Account;

@Repository
@Slf4j
public class AccountDaoImpl implements AccountDao{
    @Override
    public void addAccount() {
        System.out.println(getClass() + " Doing job : adding an account");

    }

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + " Doing job : adding an account "+account);
    }
}
