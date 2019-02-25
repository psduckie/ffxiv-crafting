package duck.craftffxiv;

import java.util.List;
import java.util.Scanner;

public class CsvListParser {
	private List<CsvEntry> list;
	
	public CsvListParser(String filePath) {
		list = CsvReader.readCsv(filePath);
	}
	
	public List<CsvEntry> breakCsvList() {
		for(CsvEntry i: list) {
			System.out.printf("%i: %s x%i%n", i.getIndex(), i.getItem(), i.getNeeded());
		}
		int breakpoint;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of the last raw material: ");
		breakpoint = scanner.nextInt();
		
		for(CsvEntry i: list) {
			if(i.getIndex() > breakpoint) {
				list.remove(i);
			}
		}
		
		return list;
	}
}
