package com.ujo.gigi.speculation.dto.response;

import com.ujo.gigi.entity.ArrivalRealTimeEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArrivalRealTimeResponseDTO {
    private String trainNo;
    private String trainName;
    private String arrivalMessage;

    public ArrivalRealTimeResponseDTO(ArrivalRealTimeEntity arrivalRealTime) {
        this.trainNo = arrivalRealTime.getTrainNo();
        this.trainName = arrivalRealTime.getTrainName();
        this.arrivalMessage = arrivalRealTime.getArrivalMessage();
    }
}
