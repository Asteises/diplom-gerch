package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.repository.TheoryRepository;

@Service
@RequiredArgsConstructor
public class TheoryService {

    private final TheoryRepository theoryRepository;
}
