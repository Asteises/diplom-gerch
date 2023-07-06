package ru.picker.core.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeTheoryDto {

    private String name;
    private String info;
    private UUID chapterId;
}
