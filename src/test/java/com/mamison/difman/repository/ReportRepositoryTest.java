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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ReportRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void crud(){
        // create
        Account account = Account.builder()
                .email("c2619zz@naver.com")
                .useful(false)
                .build();
        Report report = Report.builder()
                .account(account)
                .period("2018-09-10~2018-10-01")
                .title("중간고사")
                .build();
        Report report2 = Report.builder()
                .account(account)
                .period("2018-09-10~2018-10-02")
                .title("기말고사")
                .build();
        account.addReport(report);
        account.addReport(report2);
        Account savedAccount = accountRepository.saveAndFlush(account);

        assertThat(savedAccount.getReports().size()).isEqualTo(2);

        //update
        savedAccount.getReports().forEach(report1 -> report1.setTitle("geonyeong"));
        Account updatedAccount = accountRepository.saveAndFlush(savedAccount);

        updatedAccount.getReports().forEach(report1 -> assertThat(report1.getTitle()).isEqualTo("geonyeong"));

        //delete
        Report deleteReport = updatedAccount.getReports().get(0);
        updatedAccount.deleteReport(deleteReport);

        reportRepository.delete(deleteReport);
        reportRepository.flush();
    }
}
