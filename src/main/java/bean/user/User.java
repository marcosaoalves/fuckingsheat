package bean.user;

import java.util.Set;

public class User {
	private String name;
	private String user;
	private String email;
	private String pass;
	private Set<String> access;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String login) {
		this.user = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<String> getAccess() {
		return access;
	}

	public void setAccess(Set<String> access) {
		this.access = access;
	}

}
