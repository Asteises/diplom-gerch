package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
import ru.picker.core.service.impl.SubChapterServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/subchapter")
public class SubChapterController {

    private final SubChapterServiceImpl subChapterServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<SubChapterDisplayDto> addSubChapter(@RequestBody @NotNull IncomeSubChapterDto incomeSubChapterDto) {
        return ResponseEntity.ok(subChapterServiceImpl.addSubChapter(incomeSubChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubChapterDisplayDto> getById(@PathVariable String id) {
        SubChapterDisplayDto subChapterDisplayDto = subChapterServiceImpl.getSubChapterById(id);
        return ResponseEntity.ok(subChapterDisplayDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubChapterDisplayDto>> getAllSubChapters() {
        return ResponseEntity.ok(subChapterServiceImpl.findAll().stream().toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubChapterDisplayDto> putSubChapter(@PathVariable String id,
                                                              @RequestBody @NotNull IncomeSubChapterDto incomeSubChapterDto) {
        SubChapterDisplayDto subChapterDisplayDto = subChapterServiceImpl.renewSubChapter(id, incomeSubChapterDto);
        return ResponseEntity.ok(subChapterDisplayDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubChapter(@PathVariable String id) {
        subChapterServiceImpl.deleteSubChapter(id);
        return ResponseEntity.ok(String.format("SubChapter with ID: %s deleted", id));
    }

}
