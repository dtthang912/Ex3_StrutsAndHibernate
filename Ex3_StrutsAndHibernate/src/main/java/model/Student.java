package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(name = "Student.countAll", query = "SELECT count(*) FROM Student s"),
	@NamedQuery(name = "Student.countByName", query = "SELECT count(*) FROM Student s WHERE s.name like :name"),
	@NamedQuery(name = "Student.getAll", query = "SELECT s FROM Student s"),
	@NamedQuery(name = "Student.getByName", query = "SELECT s FROM Student s WHERE s.name like :name"),
	@NamedQuery(name = "Student.getByIds", query = "SELECT s FROM Student s WHERE s.id IN :ids")
})
public class Student implements Serializable{
	
	@Id
	private int id;
	
	@Column(name = "`name`")
	private String name;
	
	@Column(name = "`mark`")
	private float mark;
	
	public Student() {
	}

	public Student(int id, String name, float mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
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
