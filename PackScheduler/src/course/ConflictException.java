/**
 * 
 */
package course;

/**
 * This extended exception is intended to be used when there are scheduling conflicts
 * in Activities
 * @author Brian Alonso
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/** The default message for a ConflictException */
	private static final String DEFAULT_MESSAGE = "Schedule conflict.";
	
	/**
	 * Constructor to create a ConflictException using a user specified message
	 * @param message the message associated with the exception
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructor to create a ConflictException using the default message, calls main
	 * constructor.
	 */
	public ConflictException() {
		this(DEFAULT_MESSAGE);
	}	

}
