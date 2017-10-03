package ua.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "selectedCases")
public class SelectedCases {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	@Column(length = 300)
	private String judge;
	@Column(length = 100)
	private String number;
	@Column(length = 1100)
	private String sides;
	@Column(length = 1100)
	private String type;
	private String court;
	@Column(length = 5000)
	private String memo;
	@Column(length = 3000)
	private String history;
	
	@OneToMany(mappedBy="selectedCases", orphanRemoval=true)
	List<SelectedCasesHistory> selectedCasesHistory;
	
	public SelectedCases(){}

	public SelectedCases(int id, Date date, String judge, String number, String sides, String type, String court,
			String memo, String history) {
		super();
		this.id = id;
		this.date = date;
		this.judge = judge;
		this.number = number;
		this.sides = sides;
		this.type = type;
		this.court = court;
		this.memo = memo;
		this.history = history;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSides() {
		return sides;
	}

	public void setSides(String sides) {
		this.sides = sides;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	
	
	

	@Override
	public String toString() {
		return "SelectedCases [id=" + id + ", date=" + date + ", judge=" + judge + ", number=" + number + ", sides="
				+ sides + ", type=" + type + ", court=" + court + ", memo=" + memo + ", history=" + history + "]";
	};

	

}
