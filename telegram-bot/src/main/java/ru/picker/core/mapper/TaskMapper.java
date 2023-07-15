package ru.picker.core.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Task;
import ru.picker.core.model.IncomeTaskDto;
import ru.picker.core.model.TaskDisplayDto;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        imports = {UUID.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Task map(IncomeTaskDto incomeTaskDto) throws NotFoundException;

    @Mapping(target = "subChapterId", source = "subChapter.id")
    TaskDisplayDto map(Task task);

    Set<TaskDisplayDto> map(Set<Task> tasks);

    List<TaskDisplayDto> map(List<Task> tasks);
}
