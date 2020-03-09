package worksample.driving_history;

import worksample.driving_history.driverdata.filereader.HistoryReader;
import worksample.driving_history.driverdata.filereader.TxtHistoryReader;
import worksample.driving_history.exception.HistoryReadException;
import worksample.driving_history.view.Menu;

public class DrivingHistoryCLI {
	private Menu menu;
	private HistoryReader reader;
	private String dataFilePath;
	private String errorReport;

	public DrivingHistoryCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			dataFilePath = menu.getDataFilePath();
			errorReport = menu.getErrorReport();

			try {
				reader = new TxtHistoryReader(dataFilePath);

				reader.read();
				reader.printErrorReport(errorReport);
				reader.printHistoryReport();
			} catch (HistoryReadException e) {
				menu.fileNotFound();
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);

		DrivingHistoryCLI cli = new DrivingHistoryCLI(menu);

		cli.run();

	}

}
