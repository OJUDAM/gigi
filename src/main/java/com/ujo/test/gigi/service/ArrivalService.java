package com.ujo.test.gigi.service;

import com.ujo.test.common.exception.BusinessException;
import com.ujo.test.common.exception.ErrorCode;
import com.ujo.test.common.utils.DateUtils;
import com.ujo.test.common.utils.StringUtils;
import com.ujo.test.gigi.dto.request.ArrivalTimeRequestDTO;
import com.ujo.test.entity.ArrivalEntity;
import com.ujo.test.gigi.repository.ArrivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArrivalService {

    private final ArrivalRepository arrivalRepository;

    public void setArrivalTime(ArrivalTimeRequestDTO arrivalTime) {

        String infoMessage = arrivalTime.getMessage();
        //메시지 비어있을 경우
        if(StringUtils.isEmpty(infoMessage)){
            infoMessage = "-";
        }
        //DTO -> Entity 변환
        ArrivalEntity arrivalEntity = ArrivalEntity.builder()
                .stationCode(arrivalTime.getStationCode())
                .arrivalYear(DateUtils.addDate("yyyy", 0))
                .arrivalMonth(DateUtils.addDate("MM", 0))
                .arrivalDay(DateUtils.addDate("dd", 0))
                .arrivalTime(arrivalTime.getArrivalTime())
                .message(infoMessage)
                .build();

        int responseCount = arrivalRepository.save(arrivalEntity);

        if(responseCount < 1) {
            throw new BusinessException("[" + arrivalTime.getStationCode() + "]" + arrivalTime.getArrivalTime() + "도착 시간 저장에 실패하였습니다.", ErrorCode.E001);
        }
    }

}
