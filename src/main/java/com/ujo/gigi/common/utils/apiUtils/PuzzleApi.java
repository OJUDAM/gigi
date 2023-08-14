package com.ujo.gigi.common.utils.apiUtils;

import com.ujo.gigi.common.exception.BusinessException;
import com.ujo.gigi.common.exception.ErrorCode;
import com.ujo.gigi.entity.RequestStatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Puzzle API 호출 클래스
 * */
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

    /**
     * Puzzle API 호출 후 응답 결과 String 으로 반환
     * 오류 발생시 예외 반환
     * */
    private String requestPuzzleApi(String url,String apiName){
        //Puzzle API 호출 양식에 따라 헤더 정보 세팅 후 호출
        return new CommonApi.ApiBuilder(url, new BusinessException("퍼즐 "+apiName+ " API 호출 중 오류 발생했습니다.", ErrorCode.PZ01))
                .setRequestMethod("GET")
                .setRequestProperty("Accept", "application/json")
                .setRequestProperty("appKey", puzzleApiKey)
                .build()
                .callApi();
    }

    /**
     * 혼잡도 통계 API 역 코드, 시간, 요일을 파라미터로 호출
     * */
    public String callStaticsApi(String station, String hour , String dow){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleStatisticsUrl)
                .setPathParam(station)
                .startQueryStringParam("hh", hour)
                .setQueryStringParam("dow", dow)
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 혼잡도 통계");
    }

    /**
     * 혼잡도 통계 API 역 코드, 시간을 파라미터로 호출하여 String 반환
     * */
    public String callStaticsApi(RequestStatEntity requestStat){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleStatisticsUrl)
                .setPathParam(requestStat.getStationCode())
                .startQueryStringParam("hh", requestStat.getRequestHour())
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 혼잡도 통계");
    }

    /**
     * 혼잡도 통계 API 요청 파라미터 테이블있는 데이터로 세팅하여 호출하며 리스트로 반환
     * */
    public List<String> callStaticsApi(List<RequestStatEntity> requestStats){

        List<String> statList = new ArrayList<>();
        for (RequestStatEntity requestStat : requestStats) {
            statList.add(this.callStaticsApi(requestStat));
        }

        return statList;
    }

    /**
     * 지하철 역 이용자 수 API 역 코드, 요청 날짜를 파라미터로 호출하여  Json 데이터를 String 으로 반환
     * */
    public String callExitApi(String station, String date){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleExitUrl)
                .setPathParam(station)
                .startQueryStringParam("date", date)
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 출구 이용 통계");
    }

    /**
     * 지하철 역 이용자 수 API 요청 파라미터 테이블있는 데이터로 세팅 후 호출하여  Json 데이터를 String 으로 반환
     * */
    public String callExitApi(RequestStatEntity requestStat){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleExitUrl)
                .setPathParam(requestStat.getStationCode())
                .startQueryStringParam("date", requestStat.getRequestDate())
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 출구 이용 통계");
    }

    /**
     * 지하철 역 이용자 수 API 요청 파라미터 테이블있는 데이터로 세팅 후 호출하여 리스트로 반환
     * */
    public List<String> callExitApi(List<RequestStatEntity> requestStats){

        List<String> exitList = new ArrayList<>();
        for (RequestStatEntity requestStat : requestStats) {
            exitList.add(this.callExitApi(requestStat));
        }

        return exitList;
    }

    /**
     * 호출 가능한 지하철 역 정보 API 호출하여 String 으로 반환
     * */
    public String callMetaInfoApi(){
        CommonUrl url = new CommonUrl.UrlBuilder(puzzleMetaUrl)
                .startQueryStringParam("type", "exit")
                .build();

        return requestPuzzleApi(url.getUrl(), "지하철 역 정보");
    }
}
