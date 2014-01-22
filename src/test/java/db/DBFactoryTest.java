package db;

import java.sql.Connection;

import org.junit.Test;

public class DBFactoryTest {
	
	@Test
	public void testGetInstance() {
		DBFactory.getInstance();
	}

	@Test
	public void testGetConnection() {
		Connection c = DBFactory.getInstance().getConnection();
	}
	
	@Test
	public void testResetDB(){
		DBFactory.getInstance().resetDB();
	}

}
