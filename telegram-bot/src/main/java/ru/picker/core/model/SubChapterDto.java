package ru.picker.core.model;

import lombok.Data;

import java.util.Set;

@Data
public class SubChapterDto extends IncomeSubChapterDto {

    private String id;
    private String chapterId;
    private Set<TaskDto> tasks;
}
