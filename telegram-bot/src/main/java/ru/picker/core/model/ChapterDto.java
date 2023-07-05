package ru.picker.core.model;

import lombok.*;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Theory;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChapterDto {

    private UUID id;
    private String name;
    private Set<SubChapter> subChapters;
    private Set<Theory> theories;
}
