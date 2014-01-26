package svc.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.hsqldb.Server;

import util.FileProperties;
import util.TextProperties;

public class DBFactory {
	private static DBFactory dbFactory;
	private Server db;
	private Properties properties;

	private DBFactory() {
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
			db.setDatabaseName(0, getDBProperties().getProperty("db.name"));
			db.setDatabasePath(0, getDBProperties().getProperty("db.path"));

			// Start the database!
			db.start();
		}
	}

	public static DBFactory getInstance() {
		if (dbFactory == null) {
			dbFactory = new DBFactory();
		}
		return dbFactory;
	}

	public Connection getConnection() {
		Connection connection = null;

		try {
			// Getting a connection to the newly started database
			Class.forName("org.hsqldb.jdbcDriver");
			// Default user of the HSQLDB is 'sa'
			// with an empty password
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/"
							+ getDBProperties().getProperty("db.name"),
					getDBProperties().getProperty("db.user"), getDBProperties()
							.getProperty("db.pass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty(
					"err.database.notavailable"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty(
					"err.database.notavailable"));
		}

		// Return the connection
		return connection;
	}

	private Properties getDBProperties() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(new BufferedInputStream(new FileInputStream(
						FileProperties.getInstance().getResourceFile(
								"conf/db.conf"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty(
						"err.dbproperties.notavailable"));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty(
						"err.dbproperties.notavailable"));
			}
		}

		return properties;
	}

	public void resetDB() {
		Connection c = getConnection();
		List<String> sqls = FileProperties.getInstance().getQueries(
				"db/reset.sql");
		for (int i = 0; i < sqls.size(); i++) {
			try {
				c.createStatement().execute(sqls.get(i));
				c.commit();
			} catch (SQLException e) {
				System.err.println(TextProperties.getInstance().getProperty(
						"err.database.queryerror")
						+ " : " + e.getMessage());
			}
		}
	}
}
