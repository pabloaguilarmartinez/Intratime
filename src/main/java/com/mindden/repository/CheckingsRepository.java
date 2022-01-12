package com.mindden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindden.entity.Checking;

public interface CheckingsRepository extends JpaRepository<Checking, Integer> {

}
