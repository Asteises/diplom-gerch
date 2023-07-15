package ru.picker.core.model;

import lombok.Data;

import java.util.Set;

@Data
public class SubChapterDisplayDto {

    private String id;
    private String name;
    private String chapterId;
    private Set<TaskDisplayDto> tasks;
}
