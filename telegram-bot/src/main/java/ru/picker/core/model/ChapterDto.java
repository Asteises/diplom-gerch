package ru.picker.core.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChapterDto {

    private String id;
    private String name;
    private Set<SubChapterDto> subChapters;
    private Set<TheoryDto> theories;
}
