	package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.SelectedCases;

public interface SelectedCasesRepository extends JpaRepository<SelectedCases, Integer> {
	public List<SelectedCases> findAll();
}
