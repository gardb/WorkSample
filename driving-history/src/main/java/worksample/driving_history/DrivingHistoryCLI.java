package worksample.driving_history;

import worksample.driving_history.view.Menu;

public class DrivingHistoryCLI {
	
	private Menu menu;
	
	public DrivingHistoryCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		while(true) {
			String dataFilePath = menu.getDataFilePath();
			
			break;
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		
		DrivingHistoryCLI cli = new DrivingHistoryCLI(menu);

		cli.run();

	}

}
