package com.example.taskmanagment.converter;

import com.example.taskmanagment.dto.request.AddUserRequestDto;
import com.example.taskmanagment.dto.response.AllResponseUserDto;
import com.example.taskmanagment.dto.response.UserResponseDto;
import com.example.taskmanagment.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
public UserResponseDto toUserResponseDtoFromEntity(UserEntity userEntity) {
    return new UserResponseDto(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getName(),
            userEntity.getSurname()
    );
}
    public AllResponseUserDto toAllResponseUserDtoFromEntity(UserEntity userEntity) {
    return new AllResponseUserDto(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getName(),
            userEntity.getSurname()

    );
    }

    public UserEntity toEntityFromAddUserRequestDto(AddUserRequestDto requestDto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(requestDto.getUsername());
        entity.setEmail(requestDto.getEmail());
        entity.setEnabled(true);
        return entity;
    }





    }

