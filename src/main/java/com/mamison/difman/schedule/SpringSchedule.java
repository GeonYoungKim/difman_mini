package com.mamison.difman.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SpringSchedule {

    private final JobLauncher jobLauncher;
    private final Job mamisonjob;

    @Scheduled(cron = "* * 5 * * *")
    public void task(){
        try{
            JobParameters jobParameters = new JobParametersBuilder()
                    .toJobParameters();
            jobLauncher.run(mamisonjob, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
