package hexlet.code.service;

import hexlet.code.dto.tasks.TaskCreateDTO;
import hexlet.code.dto.tasks.TaskDTO;
import hexlet.code.dto.tasks.TaskParamsDTO;
import hexlet.code.dto.tasks.TaskUpdateDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAll(TaskParamsDTO params);
    TaskDTO findById(Long id);
    TaskDTO create(TaskCreateDTO taskData);
    TaskDTO update(TaskUpdateDTO taskData, Long id);
    void delete(Long id);
}

