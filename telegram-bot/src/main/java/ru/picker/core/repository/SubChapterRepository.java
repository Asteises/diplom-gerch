package ru.picker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.SubChapter;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface SubChapterRepository extends JpaRepository<SubChapter, UUID> {

    Optional<SubChapter> findSubChapterByName(String theme);

    Set<SubChapter> findAllByChapter_Id(UUID id);

}
