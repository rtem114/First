package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "casesToFindTable")
public class CasesToFind {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	private String casesToFind;
	private String court;

	public CasesToFind() {
	};

	public CasesToFind(String id, String casesToFind, String court) {
		super();
		this.id = id;
		this.casesToFind = casesToFind;
		this.court = court;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
