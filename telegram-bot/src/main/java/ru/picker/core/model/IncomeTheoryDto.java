package ru.picker.core.model;

import lombok.Data;
import ru.picker.core.entity.Chapter;

@Data
public class IncomeTheoryDto {

    private String name;
    private String info;
    private Chapter chapter;
}
