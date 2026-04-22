package com.howard.store.mappers;

import com.howard.store.dto.RegisterUserRequest;
import com.howard.store.dto.UpdateUserRequest;
import com.howard.store.dto.UserDto;
import com.howard.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest userDto);
    void update(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}
