package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileProperties {
	private static FileProperties instance;

	private FileProperties() {

	}

	public File getFile(InputStream inStream) throws Exception {
		File file = new File("file");
		OutputStream outStream = null;
		outStream = new FileOutputStream(file);

		byte[] buffer = new byte[1024];

		int length;
		while ((length = inStream.read(buffer)) > 0) {
			outStream.write(buffer, 0, length);
		}

		outStream.close();
		return file;
	}

	public File getResourceFile(String path) {
		try {
			if (path != null) {
				return getFile(getClass().getClassLoader().getResourceAsStream(
						path));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getQueries(String filePath) {
		List<String> ret = null;
		File file = getResourceFile(filePath);

		BufferedReader fileReader;
		try {
			fileReader = new BufferedReader(new FileReader(file));

			String line;
			StringBuffer sb = new StringBuffer();
			ret = new ArrayList<String>();
			while ((line = fileReader.readLine()) != null) {
				sb.append(line.trim());

				if (line.trim().endsWith(";")) {
					ret.add(sb.toString());
					sb = new StringBuffer();
				} else {
					sb.append(" ");
				}
			}

			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static FileProperties getInstance() {
		if (instance == null) {
			instance = new FileProperties();
		}
		return instance;
	}

}
