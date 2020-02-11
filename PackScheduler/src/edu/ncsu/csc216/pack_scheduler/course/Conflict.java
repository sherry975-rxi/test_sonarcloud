/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This interface outlines the behavior of a scheduling conflict and will be implemented
 * in the Activity class. Essentially, this interface tells Activity how to handle a scheduling
 * conflict if it identifies that there is a scheduling conflict.
 * @author Brian Alonso
 *
 */
public interface Conflict {
	
	/**
	 * Method to use when checking for a conflict in scheduling Activities
	 * @param possibleConflictingActivity an Activity which needs to be checked 
	 * for scheduling conflicts
	 * @throws ConflictException if there is a Conflict, this exception which must be checked
	 * for will be thrown
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
