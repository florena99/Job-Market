package ro.sd.a2.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Application
{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="id_user")
    private Users user;

    @ManyToOne
    @JoinColumn(name="id_job")
    private Job job;

    @Column
    private Date date;
}
