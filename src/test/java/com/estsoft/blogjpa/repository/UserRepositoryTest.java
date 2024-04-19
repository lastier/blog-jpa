package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("이메일로 사용자 조회")
    @Test
    void testFindByEmail(){
        //given
        User user = new User("mock_email", "mock_pw");
        userRepository.save(user);

        //when
        User returnUser = userRepository.findByEmail(user.getEmail()).orElseThrow();

        //then
        assertEquals(returnUser.getEmail(), user.getEmail());
        assertEquals(returnUser.getPassword(), user.getPassword());
    }

    @DisplayName("전체 사용자 조회")
    @Test
    void testFindAll(){
        User user = new User("mock_email", "mock_pw");
        userRepository.save(user);

        List<User> all = userRepository.findAll();

        assertEquals(1, all.size());
    }

    @DisplayName("사용자 정보 저장 userRepository.save()")
    @Test
    void testUserSave(){
        User user = new User("mock_email", "password");

        userRepository.save(user);

        User returnUser = userRepository.save(user);

        assertEquals(user.getEmail(), returnUser.getEmail());
        assertEquals(user.getPassword(), returnUser.getPassword());

        System.out.println("사용자 이메일 : "+returnUser.getEmail());
        System.out.println("사용자 비밀번호 : "+returnUser.getPassword());
    }
}