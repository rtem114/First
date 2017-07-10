package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Memo;


@Repository
public interface MemoRepository extends JpaRepository<Memo, Integer>{

	Memo findById(int id);
	List <Memo> findAll();
	
	List <Memo> getMemoByText(String text);
}
