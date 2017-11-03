package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.TemporaryCases;

public interface TemporaryCasesRepository extends JpaRepository<TemporaryCases, Integer>{
	public List<TemporaryCases> findAll();
}
