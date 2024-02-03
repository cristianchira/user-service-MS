package com.codedecode.userinfo.repo;

import com.codedecode.userinfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
 List<User> findByUserName(String userName);
}
