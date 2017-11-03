package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.SelectedCasesHistory;

public interface SelectedCasesHistoryRepository extends JpaRepository<SelectedCasesHistory, Integer>{
	List<SelectedCasesHistory> findAll();
}
