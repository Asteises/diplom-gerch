package ru.picker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.Chapter;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, UUID> {

    Optional<Chapter> findChapterByName(String theme);

}
