package ru.picker.core.repository;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.Theory;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, UUID> {

    Set<Theory> findAllByChapter_Id(UUID id);
}
