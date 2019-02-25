package duck.craftffxiv;

//import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CsvListParser {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private List<CsvEntry> list;
	
	public CsvListParser(String filePath) {
		list = CsvReader.readCsv(filePath);
	}
	
	public List<CsvEntry> breakCsvList() {
		LOGGER.setLevel(Level.INFO);
		for(CsvEntry i: list) {
			System.out.println(i.getIndex() + ": " + i.getItem() + " x" + i.getNeeded());
		}
		int breakpoint;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of the last raw material: ");
		breakpoint = scanner.nextInt();

		List<CsvEntry> brokenList = new ArrayList<>();
		
		for(CsvEntry i: list) {
			if(i.getIndex() <= breakpoint) {
				brokenList.add(i);
//				LOGGER.info(i.getItem() + " kept.");
			}
/*			else {
				LOGGER.info(i.getItem() + " cut.");
			}*/
		}
		
		list = brokenList;
		return list;
	}
	
/*	public static void main(String[] args) {
		String filePath = new File("").getAbsolutePath();
		String fullFilePath = filePath.concat("/src/list.csv");
		CsvListParser parser = new CsvListParser(fullFilePath);
		parser.breakCsvList();
	}*/
}
