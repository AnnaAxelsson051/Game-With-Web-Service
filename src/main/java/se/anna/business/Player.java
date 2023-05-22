package se.anna.business;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="players")
public class Player {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	String name;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	List<Result> results;
	
	public void addResult(int nGuesses) {
		results.add(new Result(nGuesses));
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
	public Player(String name) {
		super();
		this.name = name;
		results = new ArrayList<Result>();
	}
	public Player() {
		super();
		results = new ArrayList<Result>();
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	
}
