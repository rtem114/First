package ua.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message="Please enter the name")
	@Size(min = 2, message = "name should be not lesser than 2 letters")
	private String name;
	private String lastName;
	private String gender;
	private String email;
	@NotEmpty(message="shouldn't be empty")
	@Size(min = 5, max = 10, message = "password not lesser 5, nor larger 10")
	private String password;
	
	
	private String[] hobbie;
	private String description;
	private String position;
	private String country;

	public Person() {
	}

	public Person(int id, String name, String lastName, String gender, String email, String password, String[] hobbie,
			String description, String position, String country) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.hobbie = hobbie;
		this.description = description;
		this.position = position;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getHobbie() {
		return hobbie;
	}

	public void setHobbie(String[] hobbie) {
		this.hobbie = hobbie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", gender=" + gender + ", email="
				+ email + ", password=" + password + ", hobbie=" + Arrays.toString(hobbie) + ", description="
				+ description + ", position=" + position + ", country=" + country + "]";
	}

}
