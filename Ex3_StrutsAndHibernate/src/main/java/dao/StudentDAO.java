package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(DBUtil.DB_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudentsByIds(List<Integer> ids) {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < ids.size(); i++) {
				builder.append("?,");
			}
			pm = con.prepareStatement("select * from student where id in ("
					+ builder.deleteCharAt(builder.length() - 1).toString() + ")");
			for (int i = 0; i < ids.size(); i++) {
				pm.setInt(i + 1, ids.get(i));
			}
			rs = pm.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setMark(rs.getFloat("mark"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentList;
	}

	public List<Student> getStudentsByName(String name, int offset, int limit) {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();

		if (name.equals("")) {
			return getAllStudents(offset, limit);
		}

		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("select * from student where name like ? order by id limit ? offset ?");
			pm.setString(1, "%" + name + "%");
			pm.setInt(2, limit);
			pm.setInt(3, offset);
			rs = pm.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setMark(rs.getFloat("mark"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentList;
	}

	public List<Student> getAllStudents(int offset, int limit) {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("select * from student order by id limit ? offset ?");
			pm.setInt(1, limit);
			pm.setInt(2, offset);
			rs = pm.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setMark(rs.getFloat("mark"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentList;
	}

	public int countAllStudents() {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("select count(*) from student");
			rs = pm.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int countStudentsByName(String name) {
		Connection con = null;
		PreparedStatement pm = null;
		ResultSet rs = null;
		if (name.equals("")) {
			return countAllStudents();
		}
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("select count(*) from student where name like ?");
			pm.setString(1, "%" + name + "%");
			rs = pm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int createStudent(Student student) {
		Connection con = null;
		PreparedStatement pm = null;
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("insert into student(name,mark) values(?,?)");
			pm.setString(1, student.getName());
			pm.setFloat(2, student.getMark());
			return pm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public int updateStudent(List<Student> studentList) {
		Connection con = null;
		PreparedStatement pm = null;
		int counter = 0;
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			pm = con.prepareStatement("update student set name = ?, mark = ? where id = ?");
			for (Student student : studentList) {
				pm.setString(1, student.getName());
				pm.setFloat(2, student.getMark());
				pm.setInt(3, student.getId());
				counter += pm.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return counter;
	}

	public int deleteStudent(List<Student> studentList) {
		Connection con = null;
		PreparedStatement pm = null;
		try {
			con = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER_NAME, DBUtil.PASS);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < studentList.size(); i++) {
				builder.append("?,");
			}
			pm = con.prepareStatement(
					"delete from student where id in (" + builder.deleteCharAt(builder.length() - 1).toString() + ")");
			for (int i = 0; i < studentList.size(); i++) {
				pm.setInt(i + 1, studentList.get(i).getId());
			}
			return pm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pm != null) {
				try {
					pm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
