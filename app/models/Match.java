package models;

import java.util.Date;

public class Match {
	private int id;
	private String homeTeam;
	private String awayTeam;
	private String date;
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int value) {
		id = value;
	}
	
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String value) {
		homeTeam = value;
	}
	
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String value) {
		awayTeam = value;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String value) {
		date = value;
	}
}