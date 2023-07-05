package ru.picker.core.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomeSubChapterDto {

    private String name;
    private UUID chapterId;
}
