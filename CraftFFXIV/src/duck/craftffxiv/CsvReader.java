package duck.craftffxiv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

// CSV reader code from https://www.jackrutorial.com/2018/03/how-to-read-and-write-csv-files-in-java.html

public class CsvReader {
	public static List<CsvEntry> readCsv(String filePath) {
		BufferedReader reader = null;
		List<CsvEntry> entries = new ArrayList<CsvEntry>();
		
		try {
			String line = "";
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();
			
			while((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				
				if(fields.length > 0) {
					CsvEntry entry = new CsvEntry();
					entry.setItem(fields[0]);
					entry.setNeeded(Integer.parseInt(fields[3]));
					entries.add(entry);
				}
			}
			
			for(CsvEntry i: entries) {
				System.out.printf("%d units of %s are needed.\n", i.getNeeded(), i.getItem());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return entries;
	}
}
