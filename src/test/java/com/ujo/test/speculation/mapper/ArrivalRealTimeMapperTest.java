package com.ujo.test.speculation.mapper;

import com.ujo.test.common.mapper.ArrivalRealTimeMapper;
import com.ujo.test.common.utils.apiUtils.SeoulApi;
import com.ujo.test.entity.ArrivalRealTimeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ArrivalRealTimeMapperTest {

    private String seoulDataApiKey;
    private String seoulDataRealTimesUrl;
    private String seoulDataRealTimePositionUrl;
    private SeoulApi seoulApi;
    private ArrivalRealTimeMapper arrivalRealTimeMapper;
    @BeforeEach
    public void setUp() {
        seoulDataApiKey = "5157746d71646877313231714c435359";
        seoulDataRealTimesUrl = "http://swopenAPI.seoul.go.kr/api/subway/";
        seoulDataRealTimePositionUrl = "http://swopenAPI.seoul.go.kr/api/subway/";
        seoulApi = new SeoulApi(seoulDataApiKey, seoulDataRealTimesUrl, seoulDataRealTimePositionUrl);
        arrivalRealTimeMapper = new ArrivalRealTimeMapper();
    }

    @Test
    void jsonToList() {
        String response = seoulApi.callRealArrivalTimeApi("망포");
        List<ArrivalRealTimeEntity> list = arrivalRealTimeMapper.jsonToList(response);

        for (ArrivalRealTimeEntity entity : list) {

            System.out.println(entity.toString());
        }

        Assertions.assertEquals(3, list.size());
    }
}