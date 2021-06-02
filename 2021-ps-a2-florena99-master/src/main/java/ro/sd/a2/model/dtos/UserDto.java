package ro.sd.a2.model.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String gender;
    private String type;

}
