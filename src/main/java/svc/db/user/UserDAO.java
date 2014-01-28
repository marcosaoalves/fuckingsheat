package svc.db.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import svc.bean.user.User;
import svc.db.CommonDAO;
import svc.db.DBFactory;
import util.TextProperties;

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
				ret.setUsername(rs.getString("username"));
				ret.setAccess(getAccess(user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		return ret;
	}
	
	public List<User> getUser(){
		List<User> ret = null;
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.select.all"));
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				if(ret==null){
					ret = new ArrayList<User>();
				}
				User retUser = new User();
				retUser.setName(rs.getString("name"));
				retUser.setEmail(rs.getString("email"));
				retUser.setUsername(rs.getString("username"));
				retUser.setAccess(getAccess(retUser.getUsername()));
				ret.add(retUser);
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
			try{
				DBFactory.getInstance().resetDB();
				return validateUserPass(user, pass);
			}catch(Exception e1){
				e.printStackTrace();
				e1.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
			}
		}
		
		return false;
	}
	
	public void insertUser(User user){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.insert"));
			statement.setString(1, user.getUsername());
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
	
	public void updateUser (User user){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user.update"));
			statement.setString(4, user.getUsername());
			statement.setString(1, user.getPass());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			
			statement.executeUpdate();
			deleteAccess(user);
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
			
			Set<String> access = user.getAccess();
			if(access != null){
				Iterator<String> iAccess = access.iterator();
				while (iAccess.hasNext()){
					statement.setString(1, user.getUsername());
					statement.setString(2, iAccess.next());
					
					statement.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		
	}
	
	private void deleteAccess(User user){
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("user_access.delete"));
			statement.setString(1, user.getUsername());
			
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		
	}
}
