package com.example.taskmanagment.service;

import com.example.taskmanagment.converter.UserConverter;
import com.example.taskmanagment.dto.request.AddUserRequestDto;
import com.example.taskmanagment.dto.request.ChangePasswordRequestDto;
import com.example.taskmanagment.dto.request.UpdateUserRequestDto;
import com.example.taskmanagment.dto.response.AllResponseUserDto;
import com.example.taskmanagment.dto.response.PageResponseDto;
import com.example.taskmanagment.dto.response.UserResponseDto;
import com.example.taskmanagment.entity.UserEntity;
import com.example.taskmanagment.exception.UserAlreadyDeletedException;
import com.example.taskmanagment.exception.UserAlreadyExistsException;
import com.example.taskmanagment.exception.UserNotFoundException;
import com.example.taskmanagment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    public UserService(UserRepository repository, UserConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public PageResponseDto<AllResponseUserDto> getAll(
            int pageNumber,
            int pageSize
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<UserEntity> page = repository.findAllByIsEnabledTrue(pageable);

        List<AllResponseUserDto> elements = page.getContent()
                .stream()
                .map(converter::toAllResponseUserDtoFromEntity)
                .toList();

        return new PageResponseDto<>(
                elements,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext()
        );
    }

    public UserResponseDto getById(Integer id) {
        UserEntity userEntity = getEntityById(id);
        return converter.toUserResponseDtoFromEntity(userEntity);
    }

    public void saveUser(AddUserRequestDto requestDto) {
        log.debug("User saving, username={}", requestDto.getUsername());

        if (repository.existsByUsernameIgnoreCase(requestDto.getUsername())) {
            log.warn("User already exists, username={}", requestDto.getUsername());
            throw new UserAlreadyExistsException("Username already exists");
        }

        if (repository.existsByEmailIgnoreCase(requestDto.getEmail())) {
            log.warn("Email already exists, email={}", requestDto.getEmail());
            throw new UserAlreadyExistsException("Email already exists");
        }

        UserEntity entity = repository.save(converter.toEntityFromAddUserRequestDto(requestDto));
        log.info("User created, id={}", entity.getId());
    }

    public void changePassword(Integer id, ChangePasswordRequestDto requestDto){
        UserEntity entity = getEntityById(id);
        if(!entity.getPassword().equals(requestDto.getOldPassword())){
            log.warn("Old password is incorrect");
            throw new UserNotFoundException("Old password is incorrect");

        }
        entity.setPassword(requestDto.getNewPassword());
        repository.save(entity);

    }

    public void deleteById(Integer id) {
        log.debug("User deleting, id={}", id);
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found, id={}", id);
                    return new UserNotFoundException("User not found");
                });

        if (!entity.isEnabled()) {
            log.warn("User already deleted, id={}", id);
            throw new UserAlreadyDeletedException("User already deleted");
        }

        entity.setEnabled(false);
        repository.save(entity);
        log.info("User deleted, id={}", id);
    }

    private UserEntity getEntityById(Integer id) {
        return repository.findByIdAndIsEnabledTrue(id)
                .orElseThrow(() -> {
                    log.warn("User not found, id={}", id);
                    return new UserNotFoundException("User not found");
                });
    }

    public void updateUser(Integer id, UpdateUserRequestDto requestDto){
        UserEntity entity = getEntityById(id);
        entity.setUsername(requestDto.getUsername());
        entity.setEmail(requestDto.getEmail());
        repository.save(entity);
    }



}