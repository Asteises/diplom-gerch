package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.entity.Chapter;
import ru.picker.core.mapper.ChapterMapper;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.ChapterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/add")
    public ResponseEntity<ChapterDisplayDto> addChapter(@RequestBody IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterService.add(incomeChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDisplayDto> getChapterById(@PathVariable String id) {
        return ResponseEntity.ok(chapterService.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChapterDisplayDto>> getAllChapters() {
        List<Chapter> chapters = chapterService.getAllChapters();
        return ResponseEntity.ok(ChapterMapper.INSTANCE.map(chapters));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ChapterDisplayDto> putChapter(@PathVariable String id,
                                                        @RequestBody IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterService.renewChapter(id, incomeChapterDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChapter(@PathVariable String id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok(String.format("Chapter with ID: %s deleted", id));
    }
}
