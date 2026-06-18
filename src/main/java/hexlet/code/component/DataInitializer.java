package hexlet.code.component;


import hexlet.code.dto.labels.LabelCreateDTO;
import hexlet.code.dto.statuses.TaskStatusCreateDTO;
import hexlet.code.model.User;
import hexlet.code.service.CustomUserDetailsService;
import hexlet.code.service.LabelService;
import hexlet.code.service.TaskStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class DataInitializer implements ApplicationRunner {

    private final TaskStatusService taskStatusService;
    private final CustomUserDetailsService userService;
    private final LabelService labelService;

    @Override
    public void run(ApplicationArguments args) {
        var email = "hexlet@example.com";
        var user = new User();
        user.setEmail(email);
        user.setPasswordDigest("qwerty");
        userService.createUser(user);

        var defaultStatuses = List.of(
              new TaskStatusCreateDTO("Draft", "draft"),
              new TaskStatusCreateDTO("ToReview", "to_review"),
              new TaskStatusCreateDTO("ToBeFixed", "to_be_fixed"),
              new TaskStatusCreateDTO("ToPublish", "to_publish"),
              new TaskStatusCreateDTO("Published", "published")
        );
        defaultStatuses.forEach(taskStatusService::create);

        var defaultLabels = List.of(
                new LabelCreateDTO("bug"),
                new LabelCreateDTO("feature")
        );
        defaultLabels.forEach(labelService::create);
    }
}
