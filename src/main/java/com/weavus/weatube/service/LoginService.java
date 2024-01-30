package com.weavus.weatube.service;

import com.weavus.weatube.dto.UserDto;
import com.weavus.weatube.entity.User;
import com.weavus.weatube.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepo userRepo;
    public boolean signup(UserDto userDto) {

        User user = User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword())
                .userName(userDto.getUserName())
                .build();
        try {
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
