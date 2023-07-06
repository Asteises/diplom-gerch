package ru.picker.core.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.picker.core.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findTaskByName(String name);

    Set<Task> findAllBySubChapter_Id(UUID id);

}
