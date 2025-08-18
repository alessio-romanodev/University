package is.esercitazione.controller;

public class NotSingleException extends Exception {

	public NotSingleException() {
		super();
	}

	public NotSingleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotSingleException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSingleException(String message) {
		super(message);
	}

	public NotSingleException(Throwable cause) {
		super(cause);
	}
	
}
