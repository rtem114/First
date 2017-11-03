package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.CasesToFind;

public interface CasesToFindRepository extends JpaRepository<CasesToFind, Integer> {
	public List<CasesToFind> findAll();
}
