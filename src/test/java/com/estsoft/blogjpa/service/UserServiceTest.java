package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.dto.AddUserRequest;
import com.estsoft.blogjpa.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository; //가짜 객체, stub 처리. (실제 메소드를 호출하지 않기 때문)
    @Spy
    BCryptPasswordEncoder encoder; //가짜 객체, encoder.encode(); (실제 메소드를 호출 해줄 수 있다.)

    @InjectMocks
    UserService userService; //가짜 객체를 주입 시켜준다.

    @DisplayName("사용자 정보 저장")
    @Test
    void testSave(){
        //given
        AddUserRequest request = new AddUserRequest("mock_email@1", "pw");
        String encodePassword = encoder.encode(request.getPassword());

        doReturn(new User(request.getEmail(), encodePassword))
                .when(userRepository).save(any(User.class)); //stub 처리, 가짜로 행위를 할 것이다.

        //when
        User returnUser = userService.save(request);

        //then
        assertThat(returnUser.getEmail()).isEqualTo(request.getEmail());
        assertThat(returnUser.getPassword()).isEqualTo(encodePassword);

        verify(encoder, times(2)).encode(any(String.class));
        verify(userRepository, times(1)).save(any(User.class));
    }
}