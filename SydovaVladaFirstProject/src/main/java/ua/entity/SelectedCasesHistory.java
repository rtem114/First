package ua.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "selectedCasesHistory")
public class SelectedCasesHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String history;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "selCasJointCol")
	private SelectedCases selectedCases;

	public SelectedCasesHistory() {
	}

	public SelectedCasesHistory(int id, String history, SelectedCases selectedCases) {
		super();
		this.id = id;
		this.history = history;
		this.selectedCases = selectedCases;
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

	public SelectedCases getSelectedCases() {
		return selectedCases;
	}

	public void setSelectedCases(SelectedCases selectedCases) {
		this.selectedCases = selectedCases;
	}

	@Override
	public String toString() {
		return "SelectedCasesHistory [id=" + id + ", history=" + history + ", selectedCases=" + selectedCases + "]";
	}

}
