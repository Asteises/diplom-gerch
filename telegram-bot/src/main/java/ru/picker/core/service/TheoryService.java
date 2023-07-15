package ru.picker.core.service;

import ru.picker.core.model.TheoryDisplayDto;

import java.util.List;

public interface TheoryService {

    TheoryDisplayDto findTheoryById(String id);
    List<TheoryDisplayDto> findAllByChapterId(String chapterId);
}
