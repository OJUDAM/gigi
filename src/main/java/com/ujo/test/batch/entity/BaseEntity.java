package com.ujo.test.batch.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BaseEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
