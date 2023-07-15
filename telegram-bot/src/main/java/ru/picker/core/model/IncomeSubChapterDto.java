package ru.picker.core.model;

import lombok.Data;
import ru.picker.core.entity.Chapter;

import javax.validation.constraints.NotNull;

@Data
public class IncomeSubChapterDto {

    private String name;
    @NotNull
    private Chapter chapter;
}
