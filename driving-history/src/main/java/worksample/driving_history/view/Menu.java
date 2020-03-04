package worksample.driving_history.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;
	
	private static final String DATA_FILE_PATH = "Path to data file: ";
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	
	public String getDataFilePath() {
		out.print(DATA_FILE_PATH);
		out.flush();
		
		return in.nextLine();
	}

}
