package com.myProject.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	List<UserModel> findAll(Sort sort);
}
