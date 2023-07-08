package ru.picker.core.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
import ru.picker.core.service.TaskService;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        imports = {UUID.class},
        uses = {TaskService.class})
public interface SubChapterMapper {

    SubChapterMapper INSTANCE = Mappers.getMapper(SubChapterMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    SubChapter map(IncomeSubChapterDto incomeSubChapterDto) throws NotFoundException;

    @Mapping(target = "chapterId", source = "subChapter.id")
    SubChapterDisplayDto map(SubChapter subChapter, @Context TaskService taskService);

    Set<SubChapterDisplayDto> map(Set<SubChapter> subChapters);

    List<SubChapterDisplayDto> map(List<SubChapter> subChapters);
}
