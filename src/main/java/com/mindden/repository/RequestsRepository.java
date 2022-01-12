package com.mindden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindden.entity.Request;

public interface RequestsRepository extends JpaRepository<Request, Integer> {

}
