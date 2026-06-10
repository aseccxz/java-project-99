package hexlet.code.dto.labels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class LabelUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 1000)
    private JsonNullable<String> name;

    private Long id;

    private LocalDate createdAt;
}
