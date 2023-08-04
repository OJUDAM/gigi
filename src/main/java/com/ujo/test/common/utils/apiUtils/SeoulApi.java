package com.ujo.test.common.utils.apiUtils;

import com.ujo.test.common.exception.BusinessException;
import com.ujo.test.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeoulApi {

    @Value("${seoul-data.apiKey}")
    private final String seoulDataApiKey;
    @Value("${seoul-data.realTimeUrl}")
    private final String seoulDataRealTimesUrl;
    @Value("${seoul-data.realTimePositionUrl}")
    private final String seoulDataRealTimePositionUrl;

    private String requestSeoulApi(String url,String apiName){

        return new CommonApi.ApiBuilder(url, new BusinessException("서울 공공데이터"+apiName+ " API 호출 중 오류 발생했습니다.", ErrorCode.SD01))
                .setRequestMethod("GET")
                .setRequestProperty("Content-type","application/json")
                .build()
                .callApi();
    }

    public String callRealArrivalTimeApi(String stationName) {
        CommonUrl url = new CommonUrl.UrlBuilder(seoulDataRealTimesUrl)
                .setPathParam(seoulDataApiKey)
                .setPathParam("json")
                .setPathParam("realtimeStationArrival")
                .setPathParam("0")
                .setPathParam("6")
                .setPathParam(stationName)
                .build();

        return requestSeoulApi(url.getUrl(), "실시간 지하철 도착 시간");
    }

    public String seoulRealTimePositionApi(String SubwayLine) {

        CommonUrl url = new CommonUrl.UrlBuilder(seoulDataRealTimePositionUrl)
                .setPathParam(seoulDataApiKey)
                .setPathParam("json")
                .setPathParam("realtimePosition")
                .setPathParam("0")
                .setPathParam("50")
                .setPathParam(SubwayLine)
                .build();

        return requestSeoulApi(url.getUrl(), "실시간 지하철 위치");
    }
}
