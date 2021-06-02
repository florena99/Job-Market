package ro.sd.a2.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private Date birthdate;

    @Column
    private String experience;


    @OneToOne(mappedBy="cv", cascade=CascadeType.ALL)
    private Education education;

    @OneToOne()
    @JoinColumn(name = "id_users")
    private Users users;


}
