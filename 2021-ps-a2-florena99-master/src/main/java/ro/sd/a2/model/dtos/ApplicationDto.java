package ro.sd.a2.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
public class ApplicationDto {
    private String id;
    private String mail;
    private String jobname;
    private Date date;

}
