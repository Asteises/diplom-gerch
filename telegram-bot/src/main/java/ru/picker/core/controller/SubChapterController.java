package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDisplayDto;
import ru.picker.core.service.SubChapterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/subchapter")
public class SubChapterController {

    private final SubChapterService subChapterService;

    @PostMapping("/add")
    public ResponseEntity<SubChapterDisplayDto> addSubChapter(@RequestBody IncomeSubChapterDto incomeSubChapterDto) {
        return ResponseEntity.ok(subChapterService.add(incomeSubChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubChapterDisplayDto> getById(@PathVariable String id) {
        SubChapterDisplayDto subChapterDisplayDto = subChapterService.get(id);
        return ResponseEntity.ok(subChapterDisplayDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubChapterDisplayDto>> getAllSubChapters() {
        List<SubChapter> subChapters = subChapterService.findAll().stream().toList();
        return ResponseEntity.ok(SubChapterMapper.INSTANCE.map(subChapters));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubChapterDisplayDto> putSubChapter(@PathVariable String id,
                                                              @RequestBody IncomeSubChapterDto incomeSubChapterDto) {
        SubChapterDisplayDto subChapterDisplayDto = subChapterService.renewSubChapter(id, incomeSubChapterDto);
        return ResponseEntity.ok(subChapterDisplayDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubChapter(@PathVariable String id) {
        subChapterService.deleteSubChapter(id);
        return ResponseEntity.ok(String.format("SubChapter with ID: %s deleted", id));
    }

}
