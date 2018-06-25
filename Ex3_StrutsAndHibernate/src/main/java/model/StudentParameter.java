package model;

import java.io.Serializable;

public class StudentParameter implements Serializable{
	private static final long serialVersionUID = 8357221602624436844L;
	
	private String id;
	private String name;
	private String mark;

	public StudentParameter() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMark() {
		return mark;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	
	
}
