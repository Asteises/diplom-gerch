package ru.picker.core.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.picker.core.entity.Theory;
import ru.picker.core.model.IncomeTheoryDto;
import ru.picker.core.model.TheoryDisplayDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        imports = {UUID.class})
public interface TheoryMapper {

    TheoryMapper INSTANCE = Mappers.getMapper(TheoryMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    Theory map(IncomeTheoryDto incomeTheoryDto);

    @Mapping(target = "chapterId", source = "theory.chapter.id")
    TheoryDisplayDto map(Theory theory);

    Set<TheoryDisplayDto> map(Set<Theory> theories);

    List<TheoryDisplayDto> map(List<Theory> theories);
}
