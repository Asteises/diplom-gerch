package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.entity.Chapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TheoryService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapter")
public class ChapterController {

    private final SubChapterService subChapterService;
    private final ChapterService chapterService;
    private final TheoryService theoryService;

    @PostMapping("/add")
    public ResponseEntity<ChapterDto> addChapter(@RequestBody IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(ChapterMapper.INSTANCE.map(
                chapterService.add(incomeChapterDto),
                theoryService,
                subChapterService));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> getChapterById(@PathVariable UUID id) {
        Chapter chapter = chapterService.findById(id);
        return ResponseEntity.ok(ChapterMapper.INSTANCE.map(chapter, theoryService, subChapterService));
    }
}
