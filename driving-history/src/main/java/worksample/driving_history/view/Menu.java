package worksample.driving_history.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;
	
	private static final String DATA_FILE_PATH = "Path to data file: ";
	private static final String GET_ERROR_REPORT = "Print with error report? (Y/N): ";
	private static final String FILE_NOT_FOUND = "File not found";
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public String getDataFilePath() {
		out.println(DATA_FILE_PATH);
		out.flush();
		
		return in.nextLine();
	}
	
	public String getErrorReport() {
		out.println(GET_ERROR_REPORT);
		out.flush();
		
		return in.nextLine();
	}
	
	public void fileNotFound() {
		out.println(FILE_NOT_FOUND);
		out.flush();
	}

}
