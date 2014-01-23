package db.user;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hsqldb.util.SqlFile;
import org.hsqldb.util.SqlToolError;
import org.junit.Before;
import org.junit.Test;

import util.TextProperties;
import bean.user.User;
import db.DBFactory;

public class UserDAOTest {
	
	@Before
	public void populateDB(){
		DBFactory.getInstance().resetDB();
		SqlFile sql;
		try {
			sql = new SqlFile(new File("db/test.sql"), true, null);
			sql.execute(DBFactory.getInstance().getConnection(), true);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.notavailable"));
		} catch (SqlToolError e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.notavailable"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.notavailable"));
		}

	}
	
	@Test
	public void testGetUser() {
		UserDAO dao = new UserDAO();
		User user = dao.getUser("teste");
		if (user==null){
			fail("Usuario não encontrado");
		}
	}

	@Test
	public void testValidateUserPass() {
		UserDAO dao = new UserDAO();
		boolean ret = dao.validateUserPass("teste", "teste");
		if (ret==false){
			fail("Usuario/Senha não validados");
		}
	}

	@Test
	public void testInsertUser() {
		UserDAO dao = new UserDAO();
		User user = new User();
		user.setName("teste2");
		user.setPass("teste2");
		user.setUser("teste2");
		user.setEmail("teste@teste.com");
		
		Set<String> access = new HashSet<String>();
		access.add("tela1");
		access.add("tela2");
		access.add("tela3");
		user.setAccess(access);
		
		dao.insertUser(user);
		
		User ret = dao.getUser("teste2");
		if (ret!=null){
			if (ret.getName().equals("teste2") &&
					ret.getEmail().equals("teste@teste.com")){
				if(ret.getAccess() != null &&
						((Set<String>)ret.getAccess()).contains("tela1") &&
						((Set<String>)ret.getAccess()).contains("tela2") &&
						((Set<String>)ret.getAccess()).contains("tela3") ){
				}else{
					fail("Update falhou");
				}
			}else{
				fail("Update falhou");
			}
		}else{
			fail("Usuario não encontrado");
		}
	}
	
	@Test
	public void testUpdateUser (){
		UserDAO dao = new UserDAO();
		User user = new User();
		user.setUser("teste");
		user.setName("teste3");
		user.setPass("teste3");
		user.setEmail("teste3@teste.com");
		
		Set<String> access = new HashSet<String>();
		access.add("tela4");
		access.add("tela5");
		access.add("tela6");
		user.setAccess(access);
		
		dao.updateUser(user);
		
		User ret = dao.getUser("teste");
		if (ret!=null){
			if (ret.getName().equals("teste3") &&
					ret.getEmail().equals("teste3@teste.com")){
				if(ret.getAccess() != null &&
						((Set<String>)ret.getAccess()).contains("tela4") &&
						((Set<String>)ret.getAccess()).contains("tela5") &&
						((Set<String>)ret.getAccess()).contains("tela6") ){
				}else{
					fail("Update falhou");
				}
			}else{
				fail("Update falhou");
			}
		}else{
			fail("Usuario não encontrado");
		}
	}
	
	@Test
	public void testGetUserAll(){
		UserDAO dao = new UserDAO();
		List<User> list = dao.getUser();
		if (list==null
				|| list.size() <= 1){
			fail("Usuario não encontrado");
		}
		
	}
}
