package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.entity.Memo;


@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer>{

	Memo findMemoById(Integer id);
	List <Memo> findAll();
	
	List <Memo> getMemoByText(String text);
}
