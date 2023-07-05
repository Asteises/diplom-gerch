package ru.picker.core.model;

import lombok.*;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.Task;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubChapterDto {

    private UUID id;
    private String name;
    private Chapter chapter;
    private Set<Task> tasks;
}
