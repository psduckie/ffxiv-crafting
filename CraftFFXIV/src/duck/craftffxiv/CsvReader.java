package duck.craftffxiv;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

// CSV reader code based on code from https://www.jackrutorial.com/2018/03/how-to-read-and-write-csv-files-in-java.html

public class CsvReader {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private CsvReader() {
		// Empty private constructor, since this class is just going to contain static methods
	}
	
	public static List<CsvEntry> readCsv(String filePath) {
		LOGGER.setLevel(Level.INFO);
		List<CsvEntry> entries = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line = "";
			int count = 0;
			
			while((line = reader.readLine()) != null) {
				if(count != 0) {
					String[] fields = line.split(",");
					
					if(fields.length > 0) {
						CsvEntry entry = new CsvEntry();
						entry.setIndex(count);
						entry.setItem(fields[0]);
						entry.setNeeded(Integer.parseInt(fields[3]));
						entries.add(entry);
					}					
				}
				count++;
			}
			
/*			for(CsvEntry i: entries) {
				LOGGER.info(i.getNeeded() + " units of " + i.getItem() + " are needed.");
			}*/
		}
		catch(Exception e) {
			LOGGER.severe(e.getMessage());
		}
		return entries;
	}
	
/*	public static void main(String[] args) {
		String filePath = new File("").getAbsolutePath();
		String fullFilePath = filePath.concat("/src/list.csv");
		readCsv(fullFilePath);
	}*/
}
