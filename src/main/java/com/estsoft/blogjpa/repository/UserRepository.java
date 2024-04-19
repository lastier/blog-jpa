package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //사용자에게 입력받은 email이 존재하는지 체크 (정보 가져오기)
    Optional<User> findByEmail(String email);
}
