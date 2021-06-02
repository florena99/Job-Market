package ro.sd.a2.model.dtos;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {



    private String name;
    private String email;
    private String password;
}
