package hexlet.code.repository;

import hexlet.code.model.Task;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @EntityGraph(attributePaths = {"taskStatus", "assignee", "labels"})
    List<Task> findAll(Specification<Task> spec);

    Optional<Task> findByName(String name);
}
