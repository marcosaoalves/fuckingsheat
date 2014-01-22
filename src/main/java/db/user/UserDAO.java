package db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.TextProperties;
import bean.user.User;
import db.CommonDAO;
import db.DBFactory;

public class UserDAO extends CommonDAO{
	public User getUser(String user){
		User ret = null;
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.select"));
			statement.setString(1, user);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				ret = new User();
				ret.setName(rs.getString("name"));
				ret.setEmail(rs.getString("email"));
				ret.setUser(rs.getString("username"));
				ret.setAccess(getAccess(user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		return ret;
	}
	
	public boolean validateUserPass(String user, String pass){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.validateuserpass"));
			statement.setString(1, user);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		
		return false;
	}
	
	public void insertUser(User user){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.insert"));
			statement.setString(1, user.getUser());
			statement.setString(2, user.getPass());
			statement.setString(3, user.getName());
			statement.setString(4, user.getEmail());
			
			statement.executeUpdate();
			insertAccess(user);
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
	}
	
	private Set<String> getAccess(String user){
		Set<String> ret = null;
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user_access.select"));
			statement.setString(1, user);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				if(ret == null){
					ret = new HashSet<String>();
				}
				ret.add(rs.getString("access"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		return ret;
	}
	
	private void insertAccess(User user){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user_access.insert"));
			
			Set access = user.getAccess();
			if(access != null){
				Iterator<String> iAccess = access.iterator();
				while (iAccess.hasNext()){
					statement.setString(1, user.getUser());
					statement.setString(2, iAccess.next());
					
					statement.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		
	}
}