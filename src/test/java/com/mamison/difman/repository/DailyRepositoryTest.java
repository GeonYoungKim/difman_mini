package com.mamison.difman.repository;

import com.mamison.difman.entity.Daily;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class DailyRepositoryTest {

    @Autowired
    private Dailyrepository dailyrepository;

    @Test
    public void crud() {
//        int diffDay = 0;
//        Date startDate = null;
//        try{
//            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//            String[] dateSplit = report.getPeriod().split("~");
//            startDate = sdf.parse(dateSplit[0]);
//            Date endDate = sdf.parse(dateSplit[1]);
//            diffDay = (int) ((endDate.getTime() - startDate.getTime()) / (24*60*60*1000));
//        }catch (ParseException pe){
//            System.out.println("파싱 에러");
//        }
//        for (int i=0;i<= diffDay;i++) {
//            report.getDailies().add(Daily.builder()
//                    .date(startDate)
//                    .score(0)
//                    .report(report)
//                    .build()
//                    );
//            startDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24));
//        }

        Daily daily = Daily.builder()
                .score(0)
                .build();

        dailyrepository.saveAndFlush(daily);
    }
}
