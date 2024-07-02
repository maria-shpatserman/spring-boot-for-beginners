package ru.netunix.spingboot.thymeleafapp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.netunix.spingboot.thymeleafapp.annotations.PrefixCourseCode;

@Data
@NoArgsConstructor
@ToString
public class Customer {
    private String firstName;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;
    @NotNull(message = "is required")
    @Min(value = 0, message = "must be greater or equal to zero")
    @Max(value = 10, message = "must be less or equal to 10")
    private Integer freePasses;
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;
    @PrefixCourseCode(value = "Java", message = "must start with Java")
    private String courseCode;
}
