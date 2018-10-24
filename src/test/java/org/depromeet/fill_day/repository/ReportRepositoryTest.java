package org.depromeet.fill_day.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ReportRepositoryTest {
    private final String dateFormat = "yyyy-MM-dd";
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DayRepository dayRepository;

    @Test
    public void crud() {
        // create
//        Account account = Account.builder()
//                .email("c2619zz@naver.com")
//                .useful(false)
//                .build();
//        Report report = Report.builder()
//                .account(account)
//                .period("2018-09-10~2018-10-12")
//                .title("중간고사")
//                .build();
//        Report report2 = Report.builder()
//                .account(account)
//                .period("2018-09-10~2018-10-02")
//                .title("기말고사")
//                .build();
//
//        account.addReport(report);
//        account.addReport(report2);
//
//        reportRepository.saveAndFlush(report);
//        reportRepository.saveAndFlush(report2);
//
//        assertThat(reportRepository.findAll().size()).isEqualTo(1);
//
//        //update
//        reportRepository.findAll().forEach(report1 -> report1.setTitle("geonyeong"));
//        reportRepository.flush();
//        reportRepository.findAll().forEach(report1 -> assertThat(report1.getTitle()).isEqualTo("geonyeong"));
//
//        //delete
//        Report deleteReport = reportRepository.findAll().get(0);
//        account.deleteReport(deleteReport);
//        reportRepository.delete(deleteReport);
//        reportRepository.flush();
//        assertThat(reportRepository.findAll().size()).isEqualTo(1);
    }
}
