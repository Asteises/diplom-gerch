package ru.picker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Theory;
import ru.picker.core.repository.TheoryRepository;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TheoryService {

    private final TheoryRepository theoryRepository;

    public Theory findById(UUID id) {
        return theoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Theory with ID: %s not found", id)));
    }
}
