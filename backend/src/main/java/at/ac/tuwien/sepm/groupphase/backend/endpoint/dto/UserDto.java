package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Validated
public class UserDto extends BaseDto {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 150)
    @Email
    private String email;

    @NotBlank
    @Size(min= 10, max = 50)
    private String password;

    private Boolean locked;

    private Boolean isEmployee;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String password, Boolean locked, Boolean isEmployee) {
        this.id =id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.isEmployee = isEmployee;
    }

    public UserDto( String firstName, String lastName, String email, String password) {
        this.id=null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.locked = false;
        this.isEmployee = false;

    }



}
