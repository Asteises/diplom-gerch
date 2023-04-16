package ru.picker.core.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.SubChapter;

@Repository
public interface SubChapterRepository extends JpaRepository<SubChapter, UUID> {
    Optional<SubChapter> findSubChapterByName(String theme);
}
