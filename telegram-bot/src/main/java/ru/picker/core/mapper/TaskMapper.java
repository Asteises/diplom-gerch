package ru.picker.core.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Task;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TaskService;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        imports = {UUID.class},
        uses = {TaskService.class, SubChapterService.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Task map(IncomeTaskDto incomeTaskDto, @Context TaskService taskService) throws NotFoundException;

    @Mapping(target = "subChapterId", source = "subChapter.id")
    TaskDisplayDto map(Task task);

    Set<TaskDisplayDto> map(Set<Task> tasks);
}
