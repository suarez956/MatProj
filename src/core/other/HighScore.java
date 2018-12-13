package core.other;
//qrEiva3Lvp6mS8PX

import java.sql.*;
@SuppressWarnings("unused") 
public interface HighScore {
	/**
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * 
	 * */
	public static void db_write(String host, String username, String password, String time, int level, String name) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement();
			String sql= "INSERT INTO `highscore`(`level_id`, `name`, `time`) VALUES ("+level+",\""+name+"\",\""+time+"\")";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Bylo zapsáno do databáze.");
			
		} catch (SQLException e) {
		System.err.println(e.getMessage());
		}
	}
	public static void db_read(String host, String username, String password, int level) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM HIGHSCORE WHERE level_id="+level;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				String time = rs.getString("time");
				System.out.println(name+" "+time);
			}
			
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public static void db_alter(String host, String username, String password) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement();
		} catch (SQLException e) {
		System.err.println(e.getMessage());
		}
		
	}
	
	
	
	
	
}
