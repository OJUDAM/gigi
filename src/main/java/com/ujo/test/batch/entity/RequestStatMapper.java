package com.ujo.test.batch.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.test.common.constants.StatConstant;
import com.ujo.test.common.exception.BusinessException;
import com.ujo.test.common.exception.ErrorCode;
import com.ujo.test.common.utils.jsonUtils.CommonJSON;
import com.ujo.test.common.utils.jsonUtils.CustomJSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RequestStatMapper extends BaseMapper{

    /**ㅇ
     * StationEntity 리스트로 받아 17~19 시간 설정 후 RequestStatEntity 리스트로 반환
     * */
    public List<RequestStatEntity> stationsToRequestList(List<StationEntity> stations) {
        List<RequestStatEntity> requestList = new ArrayList<>();

        for (StationEntity station : stations) {
            requestList.addAll(stationToRequestList(station));
        }

        return requestList;
    }

    /**
     * StationEntity 받아 17~19 시간 설정 후 RequestStatEntity 리스트로 반환
     * */
    public List<RequestStatEntity> stationToRequestList(StationEntity station) {
        try {
            List<RequestStatEntity> requestList = new ArrayList<>();

            for (String hour : StatConstant.STAT_TIME_3_HOUR) {
                RequestStatEntity requestStat = new RequestStatEntity();
                requestStat.setStationCode(station.getStationCode());
                requestStat.setRequestHour(hour);

                requestList.add(requestStat);
            }

            return requestList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("StationEntity 에서 StatRequestEntity 변환하는 과정에서 에러 발생", ErrorCode.E001);
        }
    }
}
