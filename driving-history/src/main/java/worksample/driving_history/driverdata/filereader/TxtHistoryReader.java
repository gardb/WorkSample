package worksample.driving_history.driverdata.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import worksample.driving_history.driverdata.Driver;
import worksample.driving_history.driverdata.Trip;
import worksample.driving_history.exception.HistoryReadException;

public class TxtHistoryReader extends HistoryReader{
	
	private String txtFilePath;
	
	public TxtHistoryReader(String historyFilePath) {
		this.txtFilePath = historyFilePath;
	}
	
	public Map<Driver, Trip> read() {
		return loadHistoryFromDelimitedLines(getLinesFromTxtFile(), "\\s");
	}
	
	private List<String> getLinesFromTxtFile(){
		List<String> lines = new ArrayList<String>();
		
		File inputFile = new File(txtFilePath);
		
		if (!inputFile.isFile()) {
			throw new HistoryReadException(txtFilePath + " file not found.", this.getClass().getName());
		}
		
		try(Scanner fileScanner = new Scanner(inputFile)) {
			while(fileScanner.hasNextLine()) {
				lines.add(fileScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			throw new HistoryReadException(e, "Failed to read history file", this.getClass().getName());
		}
		
		return lines;
	}

}