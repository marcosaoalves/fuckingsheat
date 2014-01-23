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
	public void save(String name, String email, String username, String pass,
			String passVerify, List access) throws Exception {
		if (!pass.equals(passVerify)) {
			throw new Exception(TextProperties.getInstance().getProperty(
					"err.user.delegate.passdontmatch"));
		} else {
			UserDAO userDAO = new UserDAO();
			User user = new User();

			user.setName(name);
			user.setEmail(email);
			user.setUsername(username);
			user.setPass(pass);

			Iterator iAccess = access.iterator();
			Set<String> accessSet = new HashSet<String>();
			while (iAccess.hasNext()) {
				accessSet.add((String) iAccess.next());
			}
			user.setAccess(accessSet);

			User cons = userDAO.getUser(user.getUsername());
			if (cons == null) {
				userDAO.insertUser(user);
			} else {
				userDAO.updateUser(user);
			}
		}
	}

	public String[] getAllUsers() {
		String[] ret = null;

		UserDAO userDAO = new UserDAO();
		List<User> users = userDAO.getUser();

		if (users != null && users.size() > 0) {
			ret = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				ret[i] = ((User) users.get(i)).getUsername();
			}
		}

		return ret;
	}

	public String[] getUser(String username) {
		String[] ret = null;

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(username);

		if (user != null) {
			ret = new String[3];
			ret[0] = user.getUsername();
			ret[1] = user.getName();
			ret[2] = user.getEmail();
		}

		return ret;
	}

	public String[] getAccess(String username) {
		String[] ret = null;

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(username);

		if (user != null && user.getAccess() != null) {
			Set<String> access = user.getAccess();
			ret = new String[access.size()];

			Iterator<String> iAccess = access.iterator();
			int i = 0;
			while (iAccess.hasNext()) {
				ret[i++] = iAccess.next();
			}
		}

		return ret;
	}

}
