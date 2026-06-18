package hexlet.code.mapper;

import hexlet.code.model.BaseEntity;
import hexlet.code.model.Label;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ReferenceMapper {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private LabelRepository labelRepository;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entityClass) {
        return id != null ? entityManager.find(entityClass, id) : null;
    }

    public TaskStatus toTaskStatusByName(String status) {
        if (status == null || status.isBlank()) {
            return null;
        }
        return taskStatusRepository.findBySlug(status).orElse(null);
    }
    public Set<Label> labelIdsToLabel(Set<Long> labelIds) {
        if (labelIds == null || labelIds.isEmpty()) {
            return null;
        }
        return new HashSet<>(labelRepository.findByIdIn(labelIds));
    }

    public Set<Long> labelToLabelIds(Set<Label> labels) {
        if (labels == null || labels.isEmpty()) {
            return null;
        }
        return labels.stream()
                .map(Label::getId)
                .collect(Collectors.toSet());
    }
}
