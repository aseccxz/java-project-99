package hexlet.code.app.util.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.app.dto.statuses.TaskStatusCreateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.util.ModelGenerator;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskStatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private ModelGenerator modelGenerator;

    private TaskStatus testTaskStatus;

    @Value("/api/task_statuses")
    private String url;

    @BeforeEach
    public void setUp() {
        testTaskStatus = Instancio.of(modelGenerator.getStatusModel()).create();
        taskStatusRepository.save(testTaskStatus);

    }
    @AfterEach
    public void clear() {
        taskStatusRepository.deleteAll();
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get(url).with(jwt()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public  void testCreateTaskStatus() throws Exception {
        var newTaskStatusModel = Instancio.of(modelGenerator.getStatusModel()).create();
        TaskStatusCreateDTO dto = taskStatusMapper.mapToCreate(newTaskStatusModel);

        MockHttpServletRequestBuilder request = post(url).with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        TaskStatus taskStatus = taskStatusRepository.findById(testTaskStatus.getId())
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus not found with name: "
                        + testTaskStatus.getName()));

        assertThat(taskStatus).isNotNull();
        assertThat(taskStatus.getName()).isEqualTo(testTaskStatus.getName());
        assertThat(taskStatus.getSlug()).isEqualTo(testTaskStatus.getSlug());
    }

    @Test
    public void testShowTaskStatus() throws Exception {
        MockHttpServletRequestBuilder request = get(url + "/{id}", testTaskStatus.getId()).with(jwt());

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(testTaskStatus.getName()),
                v -> v.node("slug").isEqualTo(testTaskStatus.getSlug())
        );
    }

    @Test
    public void testUpdateTaskStatus() throws Exception {
        testTaskStatus.setName("Some Name");
        testTaskStatus.setSlug("Some Slug");

        TaskStatusCreateDTO dto = taskStatusMapper.mapToCreate(testTaskStatus);

        var request = put(url + "/{id}", testTaskStatus.getId()).with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        TaskStatus taskStatus = taskStatusRepository.findById(testTaskStatus.getId())
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus not found with name: "
                        + testTaskStatus.getName()));

        assertThat(taskStatus.getName()).isEqualTo(testTaskStatus.getName());
        assertThat(taskStatus.getSlug()).isEqualTo(testTaskStatus.getSlug());
    }

    @Test
    public void testUpdateTaskStatusPartial() throws Exception {
        testTaskStatus.setSlug("Some Slug");

        TaskStatusCreateDTO dto = taskStatusMapper.mapToCreate(testTaskStatus);

        var request = put(url + "/{id}", testTaskStatus.getId()).with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        TaskStatus taskStatus = taskStatusRepository.findById(testTaskStatus.getId())
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus not found with name: "
                        + testTaskStatus.getName()));

        assertThat(taskStatus.getName()).isEqualTo(testTaskStatus.getName());
        assertThat(taskStatus.getSlug()).isEqualTo(testTaskStatus.getSlug());
    }

    @Test
    public void testDestroy() throws Exception {
        var request = delete(url + "/{id}", testTaskStatus.getId()).with(jwt());

        mockMvc.perform(request)
                .andExpect(status().isNoContent());

        assertThat(taskStatusRepository.findById(testTaskStatus.getId())).isNotPresent();
    }
}
