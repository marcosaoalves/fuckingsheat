package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TextProperties extends Properties {
	private static TextProperties text;
	
	private TextProperties(){
			try {
				load(new BufferedInputStream(new FileInputStream(
						new File("conf/text.properties"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static TextProperties getInstance(){
		if(text == null){
			text = new TextProperties();
		}
		return text;
	}

	@Override
	public String getProperty(String key) {
		String ret = super.getProperty(key);
		if(ret == null){
			ret = "Not Found";
		}
		return ret;
	}
	
}
