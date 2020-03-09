package worksample.driving_history.exception;

@SuppressWarnings("serial")
public class HistoryReadException extends RuntimeException{
	
	private String source;
	
	public HistoryReadException(String message, String source) {
		super(message);
		this.source = source;
	}
	
	public HistoryReadException(Throwable rootException, String message, String source) {
		super(message, rootException);
		this.source = source;
		System.out.println("File not found");
	}
	
	public String getSource() {
		return source;
	}
	
	public String getErrorMessage() {
		return "Failed to load history from " + source + " : " + super.getMessage();
	}

}