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
			text = new TextProperties();
			try {
				text.load(new BufferedInputStream(new FileInputStream(
						new File("conf/text.properties"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty("err.textproperties.notavailable"));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(TextProperties.getInstance().getProperty("err.textproperties.notavailable"));
			}
	}
	
	public static TextProperties getInstance(){
		if(text == null){
			text = new TextProperties();
		}
		return text;
	}

}
