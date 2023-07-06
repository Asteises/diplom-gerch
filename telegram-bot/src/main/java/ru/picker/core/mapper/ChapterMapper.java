package ru.picker.core.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Chapter;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TheoryService;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {SubChapterService.class, TheoryService.class})
public interface ChapterMapper {

    ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Chapter map(IncomeChapterDto incomeChapterDto);

    @Mapping(target = "subChapters", expression = "java(SubChapterMapper.INSTANCE.map(subChapterService.findAllByChapterId(chapter.getId())))")
    @Mapping(target = "theories", expression = "java(TheoryMapper.INSTANCE.map(theoryService.findAllByChapterId(chapter.getId())))")
    ChapterDto map(Chapter chapter, @Context TheoryService theoryService, @Context SubChapterService subChapterService);

    Set<ChapterDto> map(Set<Chapter> chapters);
}
