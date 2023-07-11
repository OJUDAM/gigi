package com.ujo.test.batch;

import com.ujo.test.batch.job.StationJobConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class SubwayScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private StationJobConfiguration stationJobConfiguration;

    @Scheduled(cron = "0 30 2 11 * *")
    public void runStationJob(){
        //지하철 역 정보 입력 배치
        this.runJob("Start-Station-Batch", stationJobConfiguration.stationJob());
    }

    private void runJob(String message, Job job){
        //job parameter 설정
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate(message, new Date())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("ERROR TIME : %s", LocalDateTime.now().toString()));;
        }
    }
}