package com.ujo.gigi.speculation.service;

import com.ujo.gigi.common.constants.BundangLine;
import com.ujo.gigi.common.utils.DateUtils;
import com.ujo.gigi.entity.ArrivalRealTimeEntity;
import com.ujo.gigi.entity.ArrivalRealTimePositionEntity;
import com.ujo.gigi.speculation.dto.response.ArrivalRealTimePositionResponseDTO;
import com.ujo.gigi.speculation.dto.response.ArrivalRealTimeResponseDTO;
import com.ujo.gigi.speculation.repository.ArrivalRealTimePositionRepository;
import com.ujo.gigi.speculation.repository.ArrivalRealTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealTimeService {

    private final ArrivalRealTimeRepository arrivalRealTimeRepository;
    private final ArrivalRealTimePositionRepository arrivalRealTimePositionRepository;

    public List<ArrivalRealTimeResponseDTO> getArrivalTimeList() {
        List<ArrivalRealTimeEntity> arrivalRealTimeEntities = arrivalRealTimeRepository.findToDay();

        return arrivalRealTimeEntities.stream().map(ArrivalRealTimeResponseDTO::new).collect(Collectors.toList());
    }

    public List<ArrivalRealTimePositionResponseDTO> getArrivalTimePositionList() {
        //화연에 표시할 역기준 도착 정보에 따른 열차 위치 정보담을 리스트 생성
        List<ArrivalRealTimePositionResponseDTO> arrivalRealTimeDTOList = new ArrayList<>();

        //화면에 표시할 역 기준 도착 정보 조회
        List<ArrivalRealTimeResponseDTO> realtimeList = getArrivalTimeList();

        for (ArrivalRealTimeResponseDTO dto : realtimeList) {
            //열차 위지 정보 세팅
            ArrivalRealTimePositionEntity arrivalRealTimePosition =  arrivalRealTimePositionRepository.findByTrainNoToday(dto.getTrainNo());
            //현재 도착, 진입한 시간과 가장 가까운 시간대 조회
            ArrivalRealTimePositionEntity arrivalNearTime = arrivalRealTimePositionRepository.findNearDate(arrivalRealTimePosition.getTrainNo()
                    , arrivalRealTimePosition.getArrivalStationCode()
                    , arrivalRealTimePosition.getArrivalCode()
                    , DateUtils.dateToTime(arrivalRealTimePosition.getCreatedAt()));

            //남은 시간 초기화
            long remainTime = 0;
            if (arrivalNearTime != null) {
                //가장 가까운 시간대의 망포역 도착, 진입 시간 조회
                ArrivalRealTimePositionEntity arrivalNextTime = arrivalRealTimePositionRepository.findNextStation(arrivalRealTimePosition.getTrainNo()
                        , arrivalNearTime.getArrivalDate()
                        , arrivalRealTimePosition.getArrivalCode()
                        , BundangLine.valueOfName("망포역").code());

                if (arrivalNextTime != null && arrivalNextTime.getCreatedAt() != null) {
                    //가까운 시간대와 현재 도착 시간대의 차이를 도착 예상 시간에서 빼줌
                    remainTime = DateUtils.timeDiff(arrivalNextTime.getCreatedAt(), DateUtils.addDate("yyyy-MM-dd HH:mm:ss",0)) - arrivalNearTime.getTimeDifferent();
                }
            }

            ArrivalRealTimePositionResponseDTO arrivalDto = new ArrivalRealTimePositionResponseDTO(arrivalRealTimePosition.getTrainNo()
                    , arrivalRealTimePosition.getArrivalStationCode()
                    , arrivalRealTimePosition.getArrivalCode()
                    , arrivalRealTimePosition.getCreatedAt()
                    , remainTime);

            arrivalRealTimeDTOList.add(arrivalDto);
        }

        return arrivalRealTimeDTOList;
    }


}
