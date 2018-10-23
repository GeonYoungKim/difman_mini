//package com.mamison.difman.schedule;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.configuration.JobLocator;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.NoSuchJobException;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@Getter
//@Setter
//@RequiredArgsConstructor
//public class SpringSchedule {
//
//    private final JobLauncher jobLauncher;
//    private final JobLocator jobLocator;
//
//    @Scheduled(cron = "* * 5 * * *")
//    public void task() throws NoSuchJobException {
//        Job job = jobLocator.getJob("mamisonjob");
//
//        try{
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .toJobParameters();
//            jobLauncher.run(job, jobParameters);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//    }
//}