package com.ujo.test.batch;

import com.ujo.test.batch.job.*;
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
    @Autowired
    private StatJobConfiguration statJobConfiguration;
    @Autowired
    private RequestSettingJobConfiguration requestSettingJobConfiguration;
    @Autowired
    private ResetSettingJobConfiguration resetSettingJobConfiguration;
    @Autowired
    private ExitJobConfiguration exitJobConfiguration;

    @Scheduled(cron = "0 30 2 11 * *")
    public void runStationJob(){
        //지하철 역 정보 입력 배치
        this.runJob("Start-Station-Batch", stationJobConfiguration.stationJob());
    }

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 15 1 * * *")
    public void runStatJob(){
        //지하철 역 이용 통계 자료 입력 배치
        this.runJob("Start-Stat-Batch", statJobConfiguration.statJob());
    }

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 20 1 * * *")
    public void runExitJob(){
        //지하철 역 정보 입력 배치
        this.runJob("Start-Exit-Batch", exitJobConfiguration.exitJob());
    }

   //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 10 1 * * *")
    public void runRequestSettingJob(){
        //puzzle API 요청 파라미터 입력 배치
        this.runJob("Start-Request-Setting-Batch", requestSettingJobConfiguration.requestSettingJob());
    }

//    @Scheduled(cron = "0 * * * * *")
//    @Scheduled(cron = "0 8 17 * * *")
     @Scheduled(cron = "0 30 1 * * *")
    public void runResetSettingJob(){
        //지하철 역 이용 통계 자료 입력 배치
        this.runJob("Start-Reset-Setting-Batch", resetSettingJobConfiguration.resetSettingJob());
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
