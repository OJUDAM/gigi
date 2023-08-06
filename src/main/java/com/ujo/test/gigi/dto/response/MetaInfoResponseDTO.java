package com.ujo.test.gigi.dto.response;

import com.ujo.test.entity.BaseStationEntity;
import com.ujo.test.entity.BundangLineEntity;
import com.ujo.test.entity.CongestionCountEntity;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MetaInfoResponseDTO {
    private String stationCode;
    private String stationName;
    private String subwayLine;
    public MetaInfoResponseDTO(BaseStationEntity baseStationEntity) {
        this.stationCode = baseStationEntity.getStationCode();
        this.stationName = baseStationEntity.getStationName();
        this.subwayLine = baseStationEntity.getSubwayLine();
    }

    public MetaInfoResponseDTO(CongestionCountEntity congestionCountEntity) {
        this.stationCode = congestionCountEntity.getStationCode();
        this.stationName = congestionCountEntity.getStationName();
        this.subwayLine = congestionCountEntity.getSubwayLine();
    }

    public MetaInfoResponseDTO(BundangLineEntity bundangLineEntity) {
        this.stationCode = bundangLineEntity.getStationCode();
        this.stationName = bundangLineEntity.getStationName();
        this.subwayLine = "신분당선";
    }
}
