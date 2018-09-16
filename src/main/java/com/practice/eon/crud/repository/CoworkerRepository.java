package com.practice.eon.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.eon.crud.model.Coworker;

@Repository
public interface CoworkerRepository extends JpaRepository<Coworker, Long>{

	public List<Coworker> findByFirstName(String firstName);
}
