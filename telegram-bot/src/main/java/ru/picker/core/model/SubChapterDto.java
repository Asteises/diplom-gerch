package ru.picker.core.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubChapterDto {

    private String id;
    private String name;
    private String chapterId;
    private Set<TaskDto> tasks;
}
