package ru.picker.core.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, UUID> {

    Optional<Chapter> findChapterByName(String theme);

}
