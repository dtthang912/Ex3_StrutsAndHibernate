package dao;

import model.Student;

public class ThuDAO {
	public static void main(String[] args){
		StudentHDAO st = new StudentHDAO();
		st.create(new Student(133,"a",5));
	}
}
