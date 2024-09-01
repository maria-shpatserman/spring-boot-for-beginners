package ru.netunix.aopspring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.netunix.aopspring.entity.Account;

@Repository
@Slf4j
public class MembershipDaoImpl implements MembershipDao {


    @Override
    public boolean addAdditionalAccount() {
        System.out.println(getClass() + " Membership doing job : adding an account");
        return true;
    }

    @Override
    public boolean addAdditionalAccount(Account account) {
        System.out.println(getClass() + " Membership doing job : adding an account "+account);
        return true;
    }
}
