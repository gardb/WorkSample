package worksample.driving_history;

import worksample.driving_history.driverdata.filereader.HistoryReader;
import worksample.driving_history.driverdata.filereader.TxtHistoryReader;
import worksample.driving_history.view.Menu;

public class DrivingHistoryCLI {
	
	private Menu menu;
	
	public DrivingHistoryCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
			String dataFilePath = menu.getDataFilePath();
			
			while(true) {
				HistoryReader reader = new TxtHistoryReader(dataFilePath);
				
				reader.read();
				
				break;
			}
		}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		
		DrivingHistoryCLI cli = new DrivingHistoryCLI(menu);

		cli.run();

	}

}
