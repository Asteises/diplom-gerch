package ru.picker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.entity.Task;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
import ru.picker.core.repository.SubChapterRepository;
import ru.picker.core.service.SubChapterService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubChapterServiceImpl implements SubChapterService {

    private final SubChapterRepository subChapterRepository;

    @Override
    public SubChapterDisplayDto addSubChapter(IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = SubChapterMapper.INSTANCE.map(incomeSubChapterDto);
        return SubChapterMapper.INSTANCE.map(subChapterRepository.save(subChapter));
    }

    @Override
    public SubChapterDisplayDto getSubChapterById(String id) {
        SubChapter subChapter = findById(id);
        return SubChapterMapper.INSTANCE.map(subChapter);
    }

    @Override
    public List<Task> getAllTasksBySubChapter(String theme) {
        SubChapter subChapter = subChapterRepository.findSubChapterByName(theme).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with THEME: %s not found", theme)));

        return new ArrayList<>(subChapter.getTasks());
    }

    @Override
    public List<SubChapterDisplayDto> findAll() {
        List<SubChapter> subChapters = subChapterRepository.findAll();
        return SubChapterMapper.INSTANCE.map(subChapters);
    }

    @Override
    public SubChapterDisplayDto renewSubChapter(String id, IncomeSubChapterDto incomeSubChapterDto) {
        SubChapter subChapter = findById(id);
        if (incomeSubChapterDto.getName() != null && !incomeSubChapterDto.getName().isBlank()) {
            subChapter.setName(incomeSubChapterDto.getName());
        }
        if (incomeSubChapterDto.getChapter() != null) {
            subChapter.setChapter(incomeSubChapterDto.getChapter());
        }
        subChapterRepository.save(subChapter);
        return SubChapterMapper.INSTANCE.map(subChapter);
    }

    @Override
    public void deleteSubChapter(String id) {
        SubChapter subChapter = findById(id);
        if (subChapter != null) {
            subChapterRepository.delete(subChapter);
        }
    }

    private SubChapter findById(String id) {
        return subChapterRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new NotFoundException(String.format("SubChapter with ID: %s not found", id)));
    }
}
