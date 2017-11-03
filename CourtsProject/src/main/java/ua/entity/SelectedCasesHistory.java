package ua.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "selectedCasesHistory")
public class SelectedCasesHistory {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	public String history;
	@Column(length = 1100)
	private String sides;
	private Date date;


	public SelectedCasesHistory() {
	}

	public SelectedCasesHistory(String id, String history, String sides, Date date) {
		super();
		this.id = id;
		this.history = history;
		this.sides = sides;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getSides() {
		return sides;
	}

	public void setSides(String sides) {
		this.sides = sides;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SelectedCasesHistory [id=" + id + ", history=" + history + ", sides=" + sides + ", date=" + date + "]";
	}

}
