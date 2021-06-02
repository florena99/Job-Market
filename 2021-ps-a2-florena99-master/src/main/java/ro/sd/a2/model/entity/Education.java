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
@Setter
@Getter
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_CV")
    private CV cv;


}
