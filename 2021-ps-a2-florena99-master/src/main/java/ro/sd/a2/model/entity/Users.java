package ro.sd.a2.model.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String gender;

    @Column
    private String type;

    private String roles;

    public List<String> getRoleList(){
        if (this.roles.length() > 0)
            return Arrays.asList(this.roles.split(","));
        return new ArrayList<>();
    }


    @OneToMany(mappedBy="user")
    private List<Application> applications;


    @OneToOne(mappedBy = "users")
    private CV cv;
}
