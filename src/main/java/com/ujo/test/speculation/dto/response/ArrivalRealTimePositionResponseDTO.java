package com.ujo.test.speculation.dto.response;

import com.ujo.test.common.constants.BundangLine;
import com.ujo.test.common.constants.SeoulData;
import com.ujo.test.entity.ArrivalRealTimePositionEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArrivalRealTimePositionResponseDTO {
    private String trainNo;
    private String arrivalStationCode;
    private String arrivalCode;
    private String createdAt;

    public ArrivalRealTimePositionResponseDTO(ArrivalRealTimePositionEntity arrivalRealTimePosition) {
        this.trainNo = arrivalRealTimePosition.getTrainNo();
        this.arrivalStationCode = BundangLine.valueOfCode(arrivalRealTimePosition.getArrivalStationCode()).koreanName() ;
        this.arrivalCode = SeoulData.valueOfCode(arrivalRealTimePosition.getArrivalCode()).message();
        this.createdAt = arrivalRealTimePosition.getCreatedAt();
    }
}
