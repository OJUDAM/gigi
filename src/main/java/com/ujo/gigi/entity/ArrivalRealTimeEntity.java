package com.ujo.gigi.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ArrivalRealTimeEntity extends BaseStringDateEntity {
    private String trainNo;
    private String trainName;
    private String arrivalStationCode;
    private String arrivalMessage;
    private String targetStationCode;
    private int arrivalCode;
    private int directAt;
    private int upDnLine;
    private String arrivalDate;

    public static ArrivalRealTimeEntity from(Map<String, Object> realTimeMap) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(realTimeMap, ArrivalRealTimeEntity.class);
    }
}
