package models;

import java.util.ArrayList;
import java.util.List;

import ua.entity.Country;
import ua.entity.Hobbie;

public class CountryModel {

	
	public List<Country> findAll() {
		List <Country> listCountry = new ArrayList<Country>();
		listCountry.add(new Country("Ukraine", "Ukraine"));
		listCountry.add(new Country("Poland", "Poland"));
		listCountry.add(new Country("Hungary", "Hungary"));
		listCountry.add(new Country("Germany", "Germany"));
		listCountry.add(new Country("Italy", "Italy"));
		return listCountry;
	}
	
	
	
}
