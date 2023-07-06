package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.picker.core.entity.Chapter;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.SubChapterDto;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TheoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bot")
public class TeleController {

    private final SubChapterService subChapterService;

    @GetMapping("/subChapter/{id}")
    public ResponseEntity<SubChapter> getById(@PathVariable UUID id) {
        SubChapter subChapter = subChapterService.findById(id);
        return ResponseEntity.ok(subChapter);
    }

    @GetMapping("/subChapter/all")
    public List<SubChapterDto> getAllSubchapters() {
        return SubChapterMapper.INSTANCE.map(subChapterService.findAll().stream().toList());
    }



}
