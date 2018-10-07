package com.mamison.difman.repository;

import com.google.gson.Gson;
import com.mamison.difman.entity.Account;
import com.mamison.difman.entity.Report;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void crud(){
        Account account = Account.builder()
                .email("c2619zz@naver.com")
                .useful(false)
                .build();
        System.out.println(account.getReports().size());
        Report report = Report.builder()
                .account(account)
                .period("2018-09-10~2018-10-01")
                .title("중간고사")
                .build();


        account.getReports().add(report);
        report.setAccount(account);
        System.out.println(report.getTitle());
        System.out.println(account.getReports().get(0).getTitle());
//        reportRepository.save(report);

    }
}
