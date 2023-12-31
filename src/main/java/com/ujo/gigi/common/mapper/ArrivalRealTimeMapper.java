package com.ujo.gigi.common.mapper;

import com.ujo.gigi.common.constants.BundangLine;
import com.ujo.gigi.common.constants.TrainDirectInfo;
import com.ujo.gigi.common.constants.TrainUpDownInfo;
import com.ujo.gigi.common.exception.BusinessException;
import com.ujo.gigi.common.exception.ErrorCode;
import com.ujo.gigi.common.utils.DateUtils;
import com.ujo.gigi.common.utils.jsonUtils.CommonJSON;
import com.ujo.gigi.common.utils.jsonUtils.CustomJSONParser;
import com.ujo.gigi.entity.ArrivalRealTimeEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 서울 열린 데이터 실시간 도착 정보 API 호출 시 응답 받은 Json 데이터를 ArrivalRealTimeEntity 객체로 변환하는 클래스
 * */
@Component
public class ArrivalRealTimeMapper {
    public List<ArrivalRealTimeEntity> jsonToList(String responseJson) {

        CustomJSONParser jsonParser = new CustomJSONParser();
        JSONObject jsonObject = null;
        List<ArrivalRealTimeEntity> realTimeList = new ArrayList<>();

        try {
            jsonObject = jsonParser.parse(responseJson);

            JSONArray realtimeArrivalList = new CommonJSON.CommonJSONBuilder(jsonObject)
                    .parseArray("realtimeArrivalList")
                    .build().getJsonArray();
            for (Object arrivalTime : realtimeArrivalList) {
                JSONObject arrivalInfo = (JSONObject) arrivalTime;

                String updnLine = arrivalInfo.get("updnLine").toString();
                if (updnLine.equals("상행")) {
                    continue;
                }

                String trainNo = arrivalInfo.get("btrainNo").toString();
                String trainName = arrivalInfo.get("bstatnNm").toString();
                String arrivalStation = arrivalInfo.get("arvlMsg3").toString() + "역";
                String arrivalMessage = arrivalInfo.get("arvlMsg2").toString();
                String targetStation = arrivalInfo.get("statnNm").toString() + "역";
                String direct = arrivalInfo.get("btrainSttus").toString();
                String arrivalCode = arrivalInfo.get("arvlCd").toString();

                String createTime = arrivalInfo.get("recptnDt").toString();


                //맵에 저장
                Map<String, Object> realTimeMap = new HashMap<>();
                realTimeMap.put("trainNo", trainNo);
                realTimeMap.put("trainName", trainName);
                realTimeMap.put("arrivalStationCode", BundangLine.valueOfName(arrivalStation).code());
                realTimeMap.put("arrivalMessage", arrivalMessage);
                realTimeMap.put("targetStationCode", BundangLine.valueOfName(targetStation).code());
                realTimeMap.put("directAt", TrainDirectInfo.valueOfMessage(direct).code());
                realTimeMap.put("arrivalCode", arrivalCode);
                realTimeMap.put("arrivalDate", DateUtils.addDate("yyyyMMdd",0));
                realTimeMap.put("createdAt", createTime);
                realTimeMap.put("upDnLine", TrainUpDownInfo.valueOfMessage(updnLine).code());
                //MAP -> ENTITY 변환 후 리스트에 추가
                realTimeList.add(ArrivalRealTimeEntity.from(realTimeMap));
            }

            return realTimeList;
        } catch (ParseException e) {
            throw new BusinessException("json -> list 과정 중 에러 발생", ErrorCode.SD01);
        }
    }

}
