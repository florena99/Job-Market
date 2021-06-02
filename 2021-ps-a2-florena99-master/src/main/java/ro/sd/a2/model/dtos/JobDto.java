package ro.sd.a2.model.dtos;

import lombok.*;
import ro.sd.a2.model.entity.Users;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class JobDto {

    private long id;
    private String name;
    private String type;
    private String description;
    private Set<Users> users;
    private String approved;


}
