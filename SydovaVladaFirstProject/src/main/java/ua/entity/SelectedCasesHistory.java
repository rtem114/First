package ua.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "selectedCasesHistory")
public class SelectedCasesHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public String history;
	@Column(length = 1100)
	private String sides;
	private Date date;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "selCasJointCol")
	// private SelectedCases selectedCases;

	public SelectedCasesHistory() {
	}

	public SelectedCasesHistory(int id, String history, String sides, Date date) {
		super();
		this.id = id;
		this.history = history;
		this.sides = sides;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
