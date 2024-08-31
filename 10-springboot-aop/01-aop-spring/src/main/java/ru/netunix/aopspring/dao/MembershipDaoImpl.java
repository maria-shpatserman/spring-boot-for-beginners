package ru.netunix.aopspring.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MembershipDaoImpl implements MembershipDao {


    @Override
    public boolean addAdditionalAccount() {
        System.out.println(getClass() + " Membership doing job : adding an account");
        return true;
    }
}
