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
	}
	
	public String getSource() {
		return source;
	}
}