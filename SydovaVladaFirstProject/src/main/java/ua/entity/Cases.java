package ua.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cases")
public class Cases {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private Date date;
	private String judge;
	private String number;
	@Column(length = 500)
	private String sides;
	@Column(length = 500)
	private String type;


	public Cases(int id, Date date, String judge, String number, String sides, String type) {
		super();
		this.id = id;
		this.date = date;
		this.judge = judge;
		this.number = number;
		this.sides = sides;
		this.type = type;
	}
	public Cases() {};

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

	@Override
	public String toString() {
		return "Cases [id=" + id + ", date=" + date + ", judge=" + judge + ", number=" + number + ", sides=" + sides
				+ ", type=" + type + "]";
	}

}
