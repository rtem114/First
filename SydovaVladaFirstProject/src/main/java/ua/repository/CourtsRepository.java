package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ua.entity.Courts;

@Repository
public interface CourtsRepository  extends JpaRepository<Courts, Integer>{

	public List<Courts> findAll();
	
	
}
