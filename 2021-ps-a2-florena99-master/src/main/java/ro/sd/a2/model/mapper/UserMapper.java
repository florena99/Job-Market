package ro.sd.a2.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.sd.a2.model.dtos.UserDto;

import ro.sd.a2.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target="password", ignore = true)
    @Mapping(target="applications", ignore = true)
    @Mapping(target="cv", ignore = true)
    Users fromDto(UserDto userDto);

    UserDto toDto(Users users);
}
