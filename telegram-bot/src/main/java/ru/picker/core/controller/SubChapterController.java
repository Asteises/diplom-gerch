package ru.picker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.picker.core.entity.SubChapter;
import ru.picker.core.mapper.SubChapterMapper;
import ru.picker.core.model.IncomeSubChapterDto;
import ru.picker.core.model.SubChapterDto;
import ru.picker.core.service.SubChapterService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bot/subchapter")
public class SubChapterController {

    private final SubChapterService subChapterService;

    @PostMapping("/add")
    public ResponseEntity<SubChapterDto> addSubChapter(@RequestBody IncomeSubChapterDto incomeSubChapterDto) {
        return ResponseEntity.ok(subChapterService.add(incomeSubChapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubChapterDto> getById(@PathVariable UUID id) {
        SubChapterDto subChapterDto = subChapterService.get(id);
        return ResponseEntity.ok(subChapterDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubChapterDto>> getAllSubChapters() {
        List<SubChapter> subChapters = subChapterService.findAll().stream().toList();
        return ResponseEntity.ok(SubChapterMapper.INSTANCE.map(subChapters));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubChapterDto> patchSubChapter(@PathVariable UUID id,
                                                         @RequestBody IncomeSubChapterDto incomeSubChapterDto) {
        SubChapterDto subChapterDto = subChapterService.renewSubChapter(id, incomeSubChapterDto);
        return ResponseEntity.ok(subChapterDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubChapter(@PathVariable UUID id) {
        subChapterService.deleteSubChapter(id);
        return ResponseEntity.ok(String.format("SubChapter with ID: %s deleted", id));
    }

}
