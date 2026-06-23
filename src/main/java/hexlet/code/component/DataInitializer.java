package hexlet.code.component;


import hexlet.code.dto.labels.LabelCreateDTO;
import hexlet.code.dto.statuses.TaskStatusCreateDTO;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.impl.CustomUserDetailsService;
import hexlet.code.service.impl.LabelServiceImpl;
import hexlet.code.service.impl.TaskStatusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class DataInitializer implements ApplicationRunner {

    private final TaskStatusServiceImpl taskStatusService;
    private final CustomUserDetailsService userService;
    private final LabelServiceImpl labelService;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {

        var email = "hexlet@example.com";
        Optional<User> firstUser = userRepository.findByEmail(email);

        if(firstUser.isPresent()) {
            return;
        }

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
