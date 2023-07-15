package ru.picker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.Theory;
import ru.picker.core.mapper.TheoryMapper;
import ru.picker.core.model.TheoryDisplayDto;
import ru.picker.core.repository.TheoryRepository;
import ru.picker.core.service.TheoryService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TheoryServiceImpl implements TheoryService {

    private final TheoryRepository theoryRepository;

    @Override
    public TheoryDisplayDto findTheoryById(String id) {
        Theory theory = theoryRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("Theory with ID: %s not found", id)));
        return TheoryMapper.INSTANCE.map(theory);
    }

    @Override
    public List<TheoryDisplayDto> findAllByChapterId(String chapterId) {
        Set<Theory> theoryes = theoryRepository.findAllByChapter_Id(UUID.fromString(chapterId));
        return new ArrayList<>(TheoryMapper.INSTANCE.map(theoryes));
    }
}
