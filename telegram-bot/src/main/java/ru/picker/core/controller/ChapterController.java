package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.entity.Chapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.model.ChapterDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.ChapterService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/add")
    public ResponseEntity<ChapterDto> addChapter(@RequestBody IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterService.add(incomeChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> getChapterById(@PathVariable UUID id) {
        return ResponseEntity.ok(chapterService.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ChapterDto>> getAllChapters() {
        Set<Chapter> chapters = chapterService.getAllChapters();
        return ResponseEntity.ok(ChapterMapper.INSTANCE.map(chapters));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ChapterDto> patchChapter(@PathVariable UUID id,
                                                   @RequestBody IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterService.renewChapter(id, incomeChapterDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChapter(@PathVariable UUID id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok(String.format("Chapter with ID: %s deleted", id));
    }
}
