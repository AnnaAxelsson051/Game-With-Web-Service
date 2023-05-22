package se.anna.business;

import jakarta.persistence.*;


@Entity
@Table(name="results")
public class Result {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	int score;


	public Result(int result) {
		super();
		this.score = result;
	}

	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}



}
