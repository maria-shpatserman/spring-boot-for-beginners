package ru.netunix.aopspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.aopspring.dao.AccountDao;
import ru.netunix.aopspring.dao.MembershipDao;
import ru.netunix.aopspring.entity.Account;

@SpringBootApplication
public class AopspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopspringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao) {
        return runner -> {
            demoTheBeforeAdvice(accountDao);
            demoTheBeforeAdvice2(membershipDao);
            Account account = new Account();
            account.setAmount(500);
            account.setName("Mariia");
            demoTheBeforeAdvice3(accountDao, account);
            demoTheBeforeAdvice4(membershipDao, account);

        };
    }

    private void demoTheBeforeAdvice4(MembershipDao membershipDao, Account account) {
        membershipDao.addAdditionalAccount(account);
    }

    private void demoTheBeforeAdvice3(AccountDao accountDao, Account account) {
        accountDao.addAccount(account);
    }


    private void demoTheBeforeAdvice2(MembershipDao membershipDao) {
        membershipDao.addAdditionalAccount();
    }

    private void demoTheBeforeAdvice(AccountDao accountDao) {
        accountDao.addAccount();
    }
}
