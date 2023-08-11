package com.ujo.gigi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BaseDateEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
