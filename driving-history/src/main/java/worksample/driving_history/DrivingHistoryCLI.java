package worksample.driving_history;

import worksample.driving_history.driverdata.filereader.HistoryReader;
import worksample.driving_history.driverdata.filereader.TxtHistoryReader;
import worksample.driving_history.view.Menu;

public class DrivingHistoryCLI {
	private Menu menu;
	private HistoryReader reader;
	private String dataFilePath;

	public DrivingHistoryCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		dataFilePath = menu.getDataFilePath();

		reader = new TxtHistoryReader(dataFilePath);

		reader.read();
		reader.printErrorReport();
		reader.printHistoryReport();
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		DrivingHistoryCLI cli = new DrivingHistoryCLI(menu);

		cli.run();

	}

}
