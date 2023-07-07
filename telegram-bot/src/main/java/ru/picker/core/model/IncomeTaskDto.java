package ru.picker.core.model;

import lombok.Data;
import ru.picker.core.entity.SubChapter;

import javax.validation.constraints.NotNull;

@Data
public class IncomeTaskDto {

    private String name;
    private String test;
    private String answers;
    @NotNull
    private SubChapter subChapter;
}
