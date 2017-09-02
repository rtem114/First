package models;

import java.util.ArrayList;
import java.util.List;

import ua.entity.Hobbie;

public class HobbieModel {

	public List<Hobbie> findAll() {
		List <Hobbie> listHobbies = new ArrayList<Hobbie>();
		listHobbies.add(new Hobbie("Singing", "Singing"));
		listHobbies.add(new Hobbie("Football", "Football"));
		listHobbies.add(new Hobbie("Climbing", "Climbing"));
		listHobbies.add(new Hobbie("Gym", "Gym"));
		listHobbies.add(new Hobbie("Reading", "Reading"));
		return listHobbies;
	}

}
