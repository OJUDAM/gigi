package com.ujo.test.batch.job;

import com.ujo.test.batch.entity.*;
import com.ujo.test.batch.repository.RequestStatRepository;
import com.ujo.test.batch.repository.StatRepository;
import com.ujo.test.batch.repository.StationRepository;
import com.ujo.test.common.utils.StringUtils;
import com.ujo.test.common.utils.apiUtils.PuzzleApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StatJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PuzzleApi puzzleApi;
    private final StatMapper statMapper;
    private final RequestStatRepository requestStatRepository;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job statJob() {
        return jobBuilderFactory.get("statJob")
                .start(insertStatStep())    //호출 결과로부터
                .build();
    }

    /**
     * STEP 2.호출한 데이터 DB에 입력
     */
    @Bean
    public Step insertStatStep() {
        return stepBuilderFactory.get("insertStatStep")
                .<RequestStatEntity, StatEntity>chunk(10)
                .reader(new CustomItemReader<>(requestStatRepository.findAll()))
                .processor(statProcessor())
                .writer(statWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<RequestStatEntity, StatEntity> statProcessor(){
        return item ->
                StatEntity.from(statMapper.jsonToMap(puzzleApi.callStaticsApi(item.getStationCode(), item.getRequestHour(), null)));
    }

    @Bean
    @StepScope
    public MyBatisBatchItemWriter<StatEntity> statWriter() {
        MyBatisBatchItemWriter<StatEntity> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.ujo.test.batch.repository.StatRepository.save");
        return writer;
    }
}
