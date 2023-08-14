package com.ujo.gigi.common.mapper;

import com.ujo.gigi.common.exception.BusinessException;
import com.ujo.gigi.common.exception.ErrorCode;
import com.ujo.gigi.common.utils.jsonUtils.CommonJSON;
import com.ujo.gigi.common.utils.jsonUtils.CustomJSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Puzzle - 지하철 역 이용자 수 API 호출 시 응답받은 Json 데이터를 Map 으로 변환
 * */
@Component
public class ExitMapper extends BaseMapper {

    @Override
    public Map<String, Object> jsonToMap(String jsonString) {
        try{
            //Json 파싱 객체 생성
            CustomJSONParser jsonParser = new CustomJSONParser();
            //파싱한 데이터 JSOM Object 에 저장
            JSONObject jsonObject = jsonParser.parse(jsonString);

            //응답 상태 체크
            this.checkStatus(jsonObject);

            //지하철 이용자 수 정보 파싱
            JSONObject contents = new CommonJSON.CommonJSONBuilder(jsonObject)
                    .parseObject("contents")
                    .build().getJsonObject();

            //지하철 역 코드 파싱
            String stationCode = contents.get("stationCode").toString();
            //시간에 따른 지하철 이용자 수 배열 파싱
            JSONArray rawArray = (JSONArray) contents.get("raw");

            //시간에 따라 이용자 수 저장할 맵 생성
            Map<String, Object> countMap = new HashMap<>();


            //배열 순회하여 필요한 정보만 파싱
            for (int i = 0; i < rawArray.size(); i++) {

                JSONObject raw = (JSONObject) rawArray.get(i);

                //날짜, 시간 파싱
                String datetime = raw.get("datetime").toString();
                //이용자 수가 null 이면 다음으로
                if (raw.get("userCount") == null) {
                    continue;
                }

                //이용자 수 파싱
                int userCount = Integer.parseInt(raw.get("userCount").toString());

                //날짜만 다시 파싱
                String dateBefore = datetime.substring(0, 8);

                //17, 18, 19시 이외의 시간대는 패스
                if (!datetime.equals(dateBefore + "170000") && !datetime.equals(dateBefore + "180000") && !datetime.equals(dateBefore + "190000")) {
                    continue;
                }

                //맵에 이용자 수 저장할 키 생성 ex)userCount17
                String userCountKey = "userCount" + datetime.substring(8, 10);

                //d이미 맵에 키가 있으면 기존 이용자 수에서 더한 후 저장
                if(countMap.containsKey(userCountKey)){
                    countMap.put(userCountKey, (int)countMap.get(userCountKey) + userCount);
                    continue;
                }
                //날짜 저장
                countMap.put("date", dateBefore);
                //이용자 수 저장
                countMap.put(userCountKey, userCount);
            }

            //역 코드 저장
            countMap.put("stationCode", stationCode);

            return countMap;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("json 에서 맵으로 변환하는 과정에서 에러 발생", ErrorCode.E001);
        }
    }

    /**
     * json to List
     * */
    public List<Map<String, Object>> jsonArrayToMaps(List<String> jsonArray) {
        List<Map<String, Object>> exitMaps = new ArrayList<>();

        for (String json : jsonArray) {
            exitMaps.add(jsonToMap(json));
        }

        return exitMaps;
    }
}
