package ru.picker.core.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Task;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDto;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TaskService;

import javax.ws.rs.NotFoundException;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {UUID.class},
        uses = {TaskService.class, SubChapterService.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Task map(IncomeTaskDto incomeTaskDto, @Context TaskService taskService) throws NotFoundException;

    @Mapping(target = "subChapterId", source = "task.subChapter.id")
    TaskDto map(Task task);

    Set<TaskDto> map(Set<Task> tasks);
}
