package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Courts;

public interface CourtsRepository extends JpaRepository<Courts, Integer> {
	public List<Courts> findAll();
}
