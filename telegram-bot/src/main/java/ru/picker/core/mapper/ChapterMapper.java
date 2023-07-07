package ru.picker.core.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Chapter;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.ChapterService;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {ChapterService.class})
public interface ChapterMapper {

    ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Chapter map(IncomeChapterDto incomeChapterDto);

    @Mapping(target = "subChapters", expression = "java(SubChapterMapper.INSTANCE.map(chapterService.setSubChapters(chapter)))")
    @Mapping(target = "theories", expression = "java(TheoryMapper.INSTANCE.map(chapterService.setTheories(chapter)))")
    ChapterDto map(Chapter chapter, @Context ChapterService chapterService);

    Set<ChapterDto> map(Set<Chapter> chapters);
}
