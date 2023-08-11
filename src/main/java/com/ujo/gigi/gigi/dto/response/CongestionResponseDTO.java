package com.ujo.gigi.gigi.dto.response;

import com.ujo.gigi.entity.StatAndStationEntity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CongestionResponseDTO {

    private String stationCode;
    private String stationName;
    private String subwayLine;
    private String day;
    private String hour;
    private int congestionMin00;
    private int congestionMin10;
    private int congestionMin20;
    private int congestionMin30;
    private int congestionMin40;
    private int congestionMin50;
    private int upDnLine;
    private int directAt;

    public CongestionResponseDTO(StatAndStationEntity entity) {
        this.stationCode = entity.getStationCode();
        this.stationName = entity.getStationName();
        this.subwayLine = entity.getSubwayLine();
        this.day = entity.getDay();
        this.hour = entity.getHour();
        this.congestionMin00 = entity.getCongestionMin00();
        this.congestionMin10 = entity.getCongestionMin10();
        this.congestionMin20 = entity.getCongestionMin20();
        this.congestionMin30 = entity.getCongestionMin30();
        this.congestionMin40 = entity.getCongestionMin40();
        this.congestionMin50 = entity.getCongestionMin50();
        this.upDnLine = entity.getUpDnLine();
        this.directAt = entity.getDirectAt();

    }
}
