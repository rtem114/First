package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "casesToFind")
public class CasesToFind {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String casesToFind;
	private String court;

	public CasesToFind() {
	}

	public CasesToFind(int id, String casesToFind, String court) {
		super();
		this.id = id;
		this.casesToFind = casesToFind;
		this.court = court;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCasesToFind() {
		return casesToFind;
	}

	public void setCasesToFind(String casesToFind) {
		this.casesToFind = casesToFind;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	@Override
	public String toString() {
		return "CasesToFind [id=" + id + ", casesToFind=" + casesToFind + ", court=" + court + "]";
	}

}
