package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User.getByNameAndPass", query = "SELECT u FROM User u WHERE u.username=:username and u.pass=:pass")
})
public class User implements Serializable{
	
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "pass")
	private String pass;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
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
