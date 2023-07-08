package ru.picker.core.model;

import lombok.Data;

@Data
public class TaskDisplayDto {

    private String id;
    private String name;
    private String test;
    private String answers;
    private String subChapterId;
}
