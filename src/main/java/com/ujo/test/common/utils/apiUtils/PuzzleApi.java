package com.ujo.test.common.utils.apiUtils;

import com.ujo.test.common.exception.BusinessException;
import com.ujo.test.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PuzzleApi {

    @Value("${puzzle.apiKey}")
    private final String puzzleApiKey;
    @Value("${puzzle.statisticsUrl}")
    private final String puzzleStatisticsUrl;
    @Value("${puzzle.exitUrl}")
    private final String puzzleExitUrl;
    @Value("${puzzle.meta}")
    private final String puzzleMetaUrl;

    private String requestPuzzleApi(String url,String apiName){

        return new CommonApi.ApiBuilder(url, new BusinessException("퍼즐 "+apiName+ " API 호출 중 오류 발생했습니다.", ErrorCode.PZ01))
                .setRequestMethod("GET")
                .setRequestProperty("Accept", "application/json")
                .setRequestProperty("appKey", puzzleApiKey)
                .build()
                .callApi();
    }

    public String callStaticsApi(String station, String dow, String hour){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleStatisticsUrl)
                .setPathParam(station)
                .startQueryStringParam("dow", dow)
                .setQueryStringParam("hh", hour)
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 혼잡도 통계");
    }

    public String callMetaInfoApi(){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleMetaUrl)
                .startQueryStringParam("type", "exit")
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 역 정보");
    }
}
