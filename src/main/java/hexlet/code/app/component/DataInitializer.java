package hexlet.code.app.component;


import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        var email = "hexlet@example.com";
        var user = new User();
        user.setEmail(email);
        user.setPasswordDigest("qwerty");
        userRepository.save(user);
    }
}
