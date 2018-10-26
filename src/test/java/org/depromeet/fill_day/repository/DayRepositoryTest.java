package org.depromeet.fill_day.repository;

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
public class DayRepositoryTest {

    @Autowired
    private DayRepository dayRepository;

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
//            report.getDailies().add(Day.builder()
//                    .date(startDate)
//                    .score(0)
//                    .report(report)
//                    .build()
//                    );
//            startDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24));
//        }

//        Day day = Day.builder()
//                .score(0)
//                .build();
//
//        dayRepository.saveAndFlush(day);
    }
}
