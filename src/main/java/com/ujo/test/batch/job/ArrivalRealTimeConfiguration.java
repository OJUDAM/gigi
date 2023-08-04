package com.ujo.test.batch.job;

import com.ujo.test.batch.entity.ArrivalRealTimeMapper;
import com.ujo.test.batch.entity.ExitEntity;
import com.ujo.test.batch.entity.ExitMapper;
import com.ujo.test.batch.repository.RequestStatRepository;
import com.ujo.test.common.constants.PrimaryStationConstant;
import com.ujo.test.common.utils.apiUtils.PuzzleApi;
import com.ujo.test.common.utils.apiUtils.SeoulApi;
import com.ujo.test.entity.ArrivalRealTimeEntity;
import com.ujo.test.speculation.repository.ArrivalRealTimeRepository;
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
public class ArrivalRealTimeConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SeoulApi seoulApi;
    private final ArrivalRealTimeMapper arrivalRealTimeMapper;
    private final ArrivalRealTimeRepository arrivalRealTimeRepository;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job realTimeJob() {
        return jobBuilderFactory.get("realTimeJob")
                .start(insertArrivalRealTimeStep())    //호출 결과로부터
                .build();
    }

    /**
     * STEP 1.망포역 실시간 도착 API 조회
     */
    @Bean
    @JobScope
    public Step insertArrivalRealTimeStep() {
        return stepBuilderFactory.get("insertArrivalRealTimeStep")
                .<ArrivalRealTimeEntity, ArrivalRealTimeEntity>chunk(5)
                .reader(new CustomItemReader<>(arrivalRealTimeMapper.jsonToList(seoulApi.callRealArrivalTimeApi(PrimaryStationConstant.PRIMARY_STATION_MANGPO))))
                .writer(arrivalRealTimeWriter())
                .build();
    }

    /**
     * STEP 2.STATION_ARRIVAL_REALTIME 에 저장
     */
    @Bean
    @StepScope
    public MyBatisBatchItemWriter<ArrivalRealTimeEntity> arrivalRealTimeWriter() {
        MyBatisBatchItemWriter<ArrivalRealTimeEntity> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.ujo.test.batch.repository.ArrivalRealTimeBatchRepository.save");
        return writer;
    }
}
