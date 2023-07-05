package ru.picker.core.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Chapter;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.IncomeChapterDto;

import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {SubChapterMapper.class})
public interface ChapterMapper {

    ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Chapter map(IncomeChapterDto incomeChapterDto);

    ChapterDto map(Chapter chapter);
}
