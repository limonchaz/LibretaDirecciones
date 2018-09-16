package com.practice.eon.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.eon.crud.model.Relative;

@Repository
public interface RelativeRepository extends JpaRepository<Relative, Long> {

	public List<Relative> findByFirstName(String firstName);
}
