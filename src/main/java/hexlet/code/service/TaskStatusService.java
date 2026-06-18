package hexlet.code.service;

import hexlet.code.dto.statuses.TaskStatusCreateDTO;
import hexlet.code.dto.statuses.TaskStatusDTO;
import hexlet.code.dto.statuses.TaskStatusUpdateDTO;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatusDTO> getAll();
    TaskStatusDTO findById(Long id);
    TaskStatusDTO create(TaskStatusCreateDTO taskStatusData);
    TaskStatusDTO update(TaskStatusUpdateDTO taskStatusData, Long id);
    void delete(Long id);
}
