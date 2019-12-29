package com.qf.model;

public class ScoreBean {
	private String name;
	private String score;
	public ScoreBean(String name, String score) {
		super();
		this.name = name;
		this.score = score;
	}
	public ScoreBean() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "ScoreBean [name=" + name + ", score=" + score + "]";
	}
	
}

