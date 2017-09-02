package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.entity.Cases;

@Repository
public interface CaseRepository extends JpaRepository<Cases, Integer> {

	
	public List<Cases> findAll();
	public List<Cases> findBySidesContaining(String sides);


}
