package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.repository.SubChapterRepository;

@Service
@RequiredArgsConstructor
public class SubChapterService {

    private final SubChapterRepository subChapterRepository;


}
