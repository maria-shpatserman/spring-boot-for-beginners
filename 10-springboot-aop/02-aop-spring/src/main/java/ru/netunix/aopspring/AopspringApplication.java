package ru.netunix.aopspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.aopspring.dao.AccountDao;
import ru.netunix.aopspring.dao.MembershipDao;
import ru.netunix.aopspring.entity.Account;
import ru.netunix.aopspring.service.TrafficFortuneService;

import java.util.List;

@SpringBootApplication
public class AopspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopspringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDao accountDao,
                                               MembershipDao membershipDao,
                                               TrafficFortuneService trafficFortuneService
    ) {
        return runner -> {
            demoTheAroundAdvice(trafficFortuneService);
//            demoTheAfterAdvice(accountDao);
//            demoTheAfterThrowingFindAccountsAdvice(accountDao);
//            demoTheAfterFindAccountsAdvice(accountDao);
//            demoTheBeforeAdvice(accountDao);
//            demoTheBeforeAdvice2(membershipDao);
//            Account account = new Account();
//            account.setAmount(500);
//            account.setName("Mariia");
//            demoTheBeforeAdvice3(accountDao, account);
//            demoTheBeforeAdvice4(membershipDao, account);
        };
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDao accountDao) {
        try {
            List<Account> accounts = accountDao.findAccounts();
            System.out.println("accounts = " + accounts);
        } catch (Exception e) {
            System.out.println("The Exception " + e.getMessage() + " happened");
        }
    }

    private void demoTheAfterThrowingFindAccountsAdvice(AccountDao accountDao) {

        try {
            List<Account> accounts = accountDao.findAccounts();
        } catch (Exception e) {
            System.out.println("The Exception " + e.getMessage() + " happened");
        }
    }

    private void demoTheAfterFindAccountsAdvice(AccountDao accountDao) throws Exception {
        List<Account> accounts = accountDao.findAccounts();
        System.out.println("\n\tMain program demoTheAfterFindAccountsAdvice()");
        System.out.println("Raw accounts " + accounts);
    }

    private void demoTheBeforeAdvice4(MembershipDao membershipDao, Account account) {
        membershipDao.addAdditionalAccount(account);
    }

    private void demoTheBeforeAdvice3(AccountDao accountDao, Account account) {
        accountDao.addAccount(account, true);
    }


    private void demoTheBeforeAdvice2(MembershipDao membershipDao) {
        membershipDao.addAdditionalAccount();
    }

    private void demoTheBeforeAdvice(AccountDao accountDao) {
        accountDao.addAccount();
        accountDao.setName("Name");
        accountDao.setServiceCode("ServiceCode");
        accountDao.getName();
        accountDao.getServiceCode();
        accountDao.addAccount(new Account("MyAccout", 500), true);

    }
}
