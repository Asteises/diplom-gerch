package ru.picker.core.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Theory;
import ru.picker.core.model.IncomeTheoryDto;
import ru.picker.core.model.TheoryDto;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.TheoryService;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {ChapterService.class, TheoryService.class, ChapterMapper.class})
public interface TheoryMapper {

    TheoryMapper INSTANCE = Mappers.getMapper(TheoryMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "chapter", expression = "java(chapterService.findById(incomeTheoryDto.getChapterId()))")
    Theory map(IncomeTheoryDto incomeTheoryDto, @Context ChapterService chapterService);

    @Mapping(target = "chapterId", source = "theory.chapter.id")
    TheoryDto map(Theory theory);

    Set<TheoryDto> map(Set<Theory> theories);
}
