package com.library.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;

public class Logger {

	private static Logger logger;

	public static Logger loggerInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void writeLog(String log) {
		try {
			log = log.concat(LocalTime.now().toString());
			File f = new File("myFile.txt");
			if (f.exists() && !f.isDirectory()) {
				Files.write(Paths.get("myFile.txt"), log.getBytes(), StandardOpenOption.APPEND);
			}
			else {
				f.createNewFile();
				Files.write(Paths.get("myFile.txt"), log.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}

}
