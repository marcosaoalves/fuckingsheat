package db;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import util.TextProperties;

public class CommonDAO {
	private Properties properties;
	
	public String getSql(String key){
		return getProperties().getProperty(key);
	}
	
	private Properties getProperties(){
		if(properties==null){
			properties = new Properties();
			try {
				properties.load(new BufferedInputStream(new FileInputStream(new File("db/queries.sql"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty("err.queriesproperties.notavailable"));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty("err.queriesproperties.notavailable"));
			}
		}
		
		return properties;
	}
}
