package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courts")
public class Courts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String region;
	private String adress;
	
	@OneToMany
//	@JoinColumn(name="id")
	private List<Cases> cases = new ArrayList<Cases>();
	
	
	public Courts(){}


	public Courts(int id, String name, String region, String adress, List<Cases> cases) {
		super();
		this.id = id;
		this.name = name;
		this.region = region;
		this.adress = adress;
		this.cases = cases;
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


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public List<Cases> getCases() {
		return cases;
	}


	public void setCases(List<Cases> cases) {
		this.cases = cases;
	}


	@Override
	public String toString() {
		return "Courts [id=" + id + ", name=" + name + ", region=" + region + ", adress=" + adress + ", cases=" + cases
				+ "]";
	};

	
	
	

}
