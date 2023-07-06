package ru.picker.core.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeTaskDto {

    private String name;
    private String test;
    private String answers;
    private UUID subChapterId;
}
