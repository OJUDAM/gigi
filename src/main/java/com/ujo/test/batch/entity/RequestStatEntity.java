package com.ujo.test.batch.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestStatEntity extends BaseEntity {
    private String stationCode;
    private String requestHour;
}
