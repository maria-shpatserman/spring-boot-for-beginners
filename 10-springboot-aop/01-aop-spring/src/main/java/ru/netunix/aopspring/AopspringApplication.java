package ru.netunix.aopspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.netunix.aopspring.dao.AccountDao;
import ru.netunix.aopspring.dao.MembershipDao;

@SpringBootApplication
public class AopspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopspringApplication.class, args);
	}
    @Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao){
		return runner->{
			demoTheBeforeAdvice(accountDao);
			demoTheBeforeAdvice2(membershipDao);
		};
}

	private void demoTheBeforeAdvice2(MembershipDao membershipDao) {
		membershipDao.addAccount();
	}

	private void demoTheBeforeAdvice(AccountDao accountDao) {
		accountDao.addAccount();
	}
}
