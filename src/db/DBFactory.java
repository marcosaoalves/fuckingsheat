package db;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hsqldb.Server;

public class DBFactory {
	private static Server db;
	private Properties properties;

	private DBFactory() {

	}

	public static Connection getConnection() {
		if (db == null) {
			db = new Server();

			// HSQLDB prints out a lot of informations when
			// starting and closing, which we don't need now.
			// Normally you should point the setLogWriter
			// to some Writer object that could store the logs.
			db.setLogWriter(null);
			db.setSilent(true);

			// The actual database will be named 'xdb' and its
			// settings and data will be stored in files
			// testdb.properties and testdb.script
			db.setDatabaseName(0, "xdb");
			db.setDatabasePath(0, "file:testdb");

			// Start the database!
			db.start();
		}
		
		Connection connection = null;
		
		try {
			// Getting a connection to the newly started database
			Class.forName("org.hsqldb.jdbcDriver");
			// Default user of the HSQLDB is 'sa'
			// with an empty password
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/xdb", "sa", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Database not available");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Database not available");
		}

		// Return the connection
		return connection;
	}

	private Properties getDBProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(new BufferedInputStream(new FileInputStream(
						new File("conf/db.conf"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return properties;
	}
}
