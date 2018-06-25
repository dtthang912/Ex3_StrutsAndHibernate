package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import model.Student;

public class StudentHDAO extends AbstractDAO<Student>{
	public StudentHDAO(){
		super(Student.class);
	}
	public int countAllStudents(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return (Integer)session.getNamedQuery("Student.countAll").getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}
}
