package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ua.entity.Person;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
	List <Person> findAll();
	Person getPersonById(Integer id);
}
