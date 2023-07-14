package com.ujo.test.batch.job;

import com.ujo.test.batch.entity.ExitEntity;
import com.ujo.test.batch.entity.ExitMapper;
import com.ujo.test.batch.entity.StatEntity;
import com.ujo.test.batch.entity.StatMapper;
import com.ujo.test.batch.repository.ExitRepository;
import com.ujo.test.batch.repository.RequestStatRepository;
import com.ujo.test.common.utils.apiUtils.PuzzleApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExitJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PuzzleApi puzzleApi;
    private final ExitMapper exitMapper;
    private final RequestStatRepository requestStatRepository;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job exitJob() {
        return jobBuilderFactory.get("exitJob")
                .start(insertExitStep())    //호출 결과로부터
                .build();
    }

    /**
     * STEP 1.REQUEST_STAT 조회하여 지하철 통계 API 조회 후 리스트로 변환
     */
    @Bean
    @JobScope
    public Step insertExitStep() {
        return stepBuilderFactory.get("insertStatStep")
                .<Map<String,Object>, ExitEntity>chunk(10)
                .reader(new CustomItemReader<>(exitMapper.jsonArrayToMaps(puzzleApi.callExitApi(requestStatRepository.findCodeAndDate()))))
                .processor(exitProcessor())
                .writer(exitWriter())
                .build();
    }

    /**
     * STEP 2.MAP -> ENTITY 변환
     */
    @Bean
    @StepScope
    public ItemProcessor<Map<String,Object>, ExitEntity> exitProcessor(){
        return item -> ExitEntity.from(item);
    }

    /**
     * STEP 3.STATION_EXIT_USER_COUNT 에 저장
     */
    @Bean
    @StepScope
    public MyBatisBatchItemWriter<ExitEntity> exitWriter() {
        MyBatisBatchItemWriter<ExitEntity> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.ujo.test.batch.repository.ExitRepository.save");
        return writer;
    }
}
