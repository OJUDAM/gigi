package com.ujo.gigi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseStringDateEntity {
    private String createdAt;
    protected String updatedAt;
}
