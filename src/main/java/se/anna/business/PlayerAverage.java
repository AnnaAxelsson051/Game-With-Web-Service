package se.anna.business;

public class PlayerAverage {
	String player;
	double average;
	
	public PlayerAverage() {
		super();
	}
	public PlayerAverage(String player, double average) {
		super();
		this.player = player;
		this.average = average;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
}
