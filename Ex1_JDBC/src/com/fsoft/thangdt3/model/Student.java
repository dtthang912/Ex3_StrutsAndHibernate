package com.fsoft.thangdt3.model;

import java.io.Serializable;

public class Student implements Serializable{
	private int id;
	private String name;
	private float mark;
	
	public Student() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
	
	public float getMark(){
		return mark;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
	
}
