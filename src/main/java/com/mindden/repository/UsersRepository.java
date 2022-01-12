package com.mindden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindden.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

}
