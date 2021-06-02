package ro.sd.a2.model.dtos;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthDto {

    private String username;
    private String password;
}
