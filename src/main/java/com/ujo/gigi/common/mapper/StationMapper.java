package com.ujo.gigi.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujo.gigi.common.exception.BusinessException;
import com.ujo.gigi.common.exception.ErrorCode;
import com.ujo.gigi.common.utils.jsonUtils.CommonJSON;
import com.ujo.gigi.common.utils.jsonUtils.CustomJSONParser;
import com.ujo.gigi.entity.StationEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Puzzle API 호출 가능한 지하철 역 응답 받은 JSON 데이터 Map 으로 변환하는 클래스
 * */
@Component
public class StationMapper extends BaseMapper{

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

            return super.jsonToMap(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("json 에서 맵으로 변환하는 과정에서 에러 발생", ErrorCode.E001);
        }
    }

    public List<StationEntity> jsonToList(String jsonString) {
        try {
            //json 파싱 객체 생성
            CustomJSONParser jsonParser = new CustomJSONParser();
            JSONObject jsonObject = jsonParser.parse(jsonString);

            String contents = new CommonJSON.CommonJSONBuilder(jsonObject)
                    .parseArray("contents")
                    .build().getJsonArray().toString();

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(contents, new TypeReference<>() {});
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("json 에서 리스트로 변환하는 과정에서 에러 발생", ErrorCode.E001);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException("json 에서 리스트로 변환하는 과정에서 에러 발생", ErrorCode.E001);
        }
    }
}
