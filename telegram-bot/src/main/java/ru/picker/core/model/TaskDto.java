package ru.picker.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {

    private String id;
    private String name;
    private String test;
    private String answers;
    private String subChapterId;
}
