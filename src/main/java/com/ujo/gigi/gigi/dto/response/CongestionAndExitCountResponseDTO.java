package com.ujo.gigi.gigi.dto.response;

import com.ujo.gigi.entity.CongestionCountEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CongestionAndExitCountResponseDTO {
    private String stationCode;
    private String stationName;
    private String hour;
    private String day;
    private int userCountDate;
    private String directAt;
    private int congestionMin00;
    private int congestionMin10;
    private int congestionMin20;
    private int congestionMin30;
    private int congestionMin40;
    private int congestionMin50;
    private double userCountPerHour;

    public CongestionAndExitCountResponseDTO(CongestionCountEntity congestionCountEntity) {
        this.stationCode = congestionCountEntity.getStationCode();
        this.stationName = congestionCountEntity.getStationName();
        this.hour = congestionCountEntity.getHour();
        this.day = congestionCountEntity.getDay();
        this.userCountDate=congestionCountEntity.getUserCountDate();
        this.directAt = "일반";
        if(congestionCountEntity.getDirectAt() == 1) {
            this.directAt = "급행";
        }
        this.congestionMin00 = congestionCountEntity.getCongestionMin00();
        this.congestionMin10 = congestionCountEntity.getCongestionMin10();
        this.congestionMin20 = congestionCountEntity.getCongestionMin20();
        this.congestionMin30 = congestionCountEntity.getCongestionMin30();
        this.congestionMin40 = congestionCountEntity.getCongestionMin40();
        this.congestionMin50 = congestionCountEntity.getCongestionMin50();
        this.userCountPerHour = congestionCountEntity.getUserCount() * 0.01;
    }
}
