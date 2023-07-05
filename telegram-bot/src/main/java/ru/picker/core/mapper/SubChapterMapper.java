package ru.picker.core.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDto;
import ru.picker.core.service.ChapterService;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {ChapterService.class, TaskMapper.class})
public interface SubChapterMapper {

    SubChapterMapper INSTANCE = Mappers.getMapper(SubChapterMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "chapter", expression = "java(chapterService.findById(incomeSubChapterDto.getChapterId()))")
    SubChapter map(IncomeSubChapterDto incomeSubChapterDto, @Context ChapterService chapterService) throws NotFoundException;

    SubChapterDto map(SubChapter subChapter);

    Set<SubChapterDto> map(Set<SubChapter> subChapters);
}
