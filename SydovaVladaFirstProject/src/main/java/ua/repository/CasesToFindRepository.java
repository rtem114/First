package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.entity.CasesToFind;

@Repository
public interface CasesToFindRepository extends JpaRepository<CasesToFind, Integer> {

	public List<CasesToFind> findAll();

}
