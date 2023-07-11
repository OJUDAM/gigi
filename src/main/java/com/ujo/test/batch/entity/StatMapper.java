package com.ujo.test.batch.entity;

import com.ujo.test.common.exception.BusinessException;
import com.ujo.test.common.exception.ErrorCode;
import com.ujo.test.common.utils.jsonUtils.CommonJSON;
import com.ujo.test.common.utils.jsonUtils.CustomJSONParser;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class StatMapper extends BaseMapper{

    /**
     * puzzle api 응답 파싱하여 map 으로 변환하는 메서드
     * */
    @Override
    public Map<String, Object> jsonToMap(String jsonString) {
        try {
            //반환할 맵 생성
            Map<String, Object> statResult = new HashMap<>(12);

            //json 파싱 객체 생성
            CustomJSONParser jsonParser = new CustomJSONParser();
            JSONObject jsonObject = jsonParser.parse(jsonString);

            //응답 상태 체크
            this.checkStatus(jsonObject);

            //응답 내용 파싱
            CommonJSON.CommonJSONBuilder commonJSONBuilder = new CommonJSON.CommonJSONBuilder(jsonObject)
                    .parseObject("contents");

            //통계 날짜 매칭
            String startDate = commonJSONBuilder
                    .build()
                    .getJsonObject()
                    .get("statStartDate")
                    .toString();

            String endDate = commonJSONBuilder
                    .build()
                    .getJsonObject()
                    .get("statEndDate")
                    .toString();

            String stationCode = commonJSONBuilder
                    .build()
                    .getJsonObject()
                    .get("stationCode")
                    .toString();

            //통계 데이터 파싱
            commonJSONBuilder = commonJSONBuilder.parseArray("stat")
                    .parseArrayMember(1)
                    .parseArray("data");

            JSONObject contents;
            String hour = "";
            String day = "";

            //00 10 20 30 40 50
            for (int i = 0; i < 6; i++) {
                //시간대별 혼잡도 파싱
                contents = commonJSONBuilder
                        .parseArrayMember(i)
                        .build()
                        .getJsonObject();

                day = contents.get("dow").toString();
                hour = contents.get("hh").toString();
                String mm = contents.get("mm").toString();
                int congestion = Integer.parseInt(contents.get("congestionTrain").toString());

                statResult.put("congestionMin" + mm, congestion);
            }

            statResult.put("day", day);
            statResult.put("startDate", startDate);
            statResult.put("endDate", endDate);
            statResult.put("stationCode", stationCode);
            statResult.put("hour", hour);

            return statResult;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("json 에서 맵으로 변환하는 과정에서 에러 발생", ErrorCode.E001);
        }
    }
}
