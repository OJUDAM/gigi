package com.ujo.gigi.common.mapper;

import com.ujo.gigi.common.constants.StatConstant;
import com.ujo.gigi.common.exception.BusinessException;
import com.ujo.gigi.common.exception.ErrorCode;
import com.ujo.gigi.entity.RequestStatEntity;
import com.ujo.gigi.entity.StationEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * API 호출 횟수가 100회로 제한되어있는데 400여개 되는 지하철 역들을 하루에 20개 씩 17, 18, 19 시 각 3번 총 60번 호출하기 위해
 * 요청 파라미터 테이블에 저장하기 위한 클래스
 * */
@Component
public class RequestStatMapper extends BaseMapper{

    /**
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
