package com.fsoft.thangdt3.model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String username;
	private String pass;
	private Role role;
	
	public User() {
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPass() {
		return pass;
	}
	public Role getRole(){
		return role;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
