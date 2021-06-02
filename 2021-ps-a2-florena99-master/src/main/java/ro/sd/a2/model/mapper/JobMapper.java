package ro.sd.a2.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.sd.a2.model.dtos.JobDto;
import ro.sd.a2.model.entity.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobDto toDto(Job job);

    Job fromDto(JobDto jobDto);
}
