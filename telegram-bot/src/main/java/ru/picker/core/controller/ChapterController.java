package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.model.IncomeChapterDto;
import ru.picker.core.service.impl.ChapterServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/chapter")
public class ChapterController {

    private final ChapterServiceImpl chapterServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<ChapterDisplayDto> addChapter(@RequestBody @NotNull IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterServiceImpl.addChapter(incomeChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDisplayDto> getChapterById(@PathVariable String id) {
        return ResponseEntity.ok(chapterServiceImpl.getChapterById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChapterDisplayDto>> getAllChapters() {
        return ResponseEntity.ok(chapterServiceImpl.getAllChapters());
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ChapterDisplayDto> putChapter(@PathVariable String id,
                                                        @RequestBody @NotNull IncomeChapterDto incomeChapterDto) {
        return ResponseEntity.ok(chapterServiceImpl.renewChapter(id, incomeChapterDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChapter(@PathVariable String id) {
        chapterServiceImpl.deleteChapter(id);
        return ResponseEntity.ok(String.format("Chapter with ID: %s deleted", id));
    }
}
