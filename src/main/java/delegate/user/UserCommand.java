package delegate.user;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import util.TextProperties;
import bean.user.User;
import db.user.UserDAO;

public class UserCommand {
	public void save(String name, String email, String username, String pass, String passVerify, List access) throws Exception{
		if (!pass.equals(passVerify)){
			throw new Exception (TextProperties.getInstance().getProperty("err.user.delegate.passdontmatch"));
		}else{
			UserDAO userDAO = new UserDAO();
			User user = new User();
			
			user.setName(name);
			user.setEmail(email);
			user.setUser(username);
			user.setPass(pass);
			
			Iterator iAccess = access.iterator();
			Set<String> accessSet = new HashSet<String>();
			while (iAccess.hasNext()){
				accessSet.add((String)iAccess.next());
			}
			user.setAccess(accessSet);
			
			User cons = userDAO.getUser(user.getUser());
			if(cons == null){
				userDAO.insertUser(user);
			}else{
				userDAO.updateUser(user);
			}
		}
	}
	
	public String[] getAllUsers(){
		String[] ret = null;
		
		UserDAO userDAO = new UserDAO();
		List<User> users = userDAO.getUser();
		
		if (users != null &&
				users.size()>0){
			ret = new String[users.size()];
			for (int i=0; i<users.size(); i++){
				ret[i] = ((User)users.get(i)).getName();
			}
		}
		
		return ret;
	}

}
