package ru.picker.core.model;

import lombok.Data;

import java.util.Set;

@Data
public class ChapterDto extends IncomeChapterDto {

    private String id;
    private Set<SubChapterDto> subChapters;
    private Set<TheoryDto> theories;
}
