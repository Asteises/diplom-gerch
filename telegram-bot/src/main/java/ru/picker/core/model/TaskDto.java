package ru.picker.core.model;

import lombok.*;
import ru.picker.core.entity.SubChapter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {

    private UUID id;
    private String name;
    private String test;
    private String answers;
    private SubChapter subChapter;
}
