package com.ujo.gigi.speculation.dto.response;

import com.ujo.gigi.common.constants.BundangLine;
import com.ujo.gigi.common.constants.SeoulData;
import com.ujo.gigi.entity.ArrivalRealTimePositionEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArrivalRealTimePositionResponseDTO {
    private String trainNo;
    private String arrivalStationCode;
    private String arrivalCode;
    private String createdAt;

    private long remainTime;
    private String expectedArrivalTime;

    public ArrivalRealTimePositionResponseDTO(ArrivalRealTimePositionEntity arrivalRealTimePosition) {
        this.trainNo = arrivalRealTimePosition.getTrainNo();
        this.arrivalStationCode = BundangLine.valueOfCode(arrivalRealTimePosition.getArrivalStationCode()).koreanName() ;
        this.arrivalCode = SeoulData.valueOfCode(arrivalRealTimePosition.getArrivalCode()).message();
        this.createdAt = arrivalRealTimePosition.getCreatedAt();
    }

    public ArrivalRealTimePositionResponseDTO(String trainNo, String arrivalStationCode, int arrivalCode, String createAt, long remainTime) {
        this.trainNo = trainNo;
        this.arrivalStationCode = BundangLine.valueOfCode(arrivalStationCode).koreanName() ;
        this.arrivalCode = SeoulData.valueOfCode(arrivalCode).message();
        this.createdAt = createAt;
        this.remainTime = remainTime;
    }
    public ArrivalRealTimePositionResponseDTO(String trainNo, String arrivalStationCode
            , int arrivalCode, String createAt, long remainTime, String expectedArrivalTime) {
        this.trainNo = trainNo;
        this.arrivalStationCode = BundangLine.valueOfCode(arrivalStationCode).koreanName() ;
        this.arrivalCode = SeoulData.valueOfCode(arrivalCode).message();
        this.createdAt = createAt;
        this.remainTime = remainTime;
        this.expectedArrivalTime = expectedArrivalTime;
    }
}
