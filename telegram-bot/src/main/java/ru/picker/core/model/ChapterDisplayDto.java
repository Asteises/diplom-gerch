package ru.picker.core.model;

import lombok.Data;

import java.util.Set;

@Data
public class ChapterDisplayDto {

    private String id;
    private String name;
    private Set<SubChapterDisplayDto> subChapters;
    private Set<TheoryDisplayDto> theories;
}
