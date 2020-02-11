package course;

/**
 * Parent class for Course and Activity.
 * This class represents an activitie's Title, and the days/times the activity meets
 * @author Brian Alonso
 *
 */
public abstract class Activity implements Conflict {

	/** Activities title. */
	private String title;
	/** Activities meeting days */
	private String meetingDays;
	/** Activities starting time */
	private int startTime;
	/** Activities ending time */
	private int endTime;
	/** The upper bound for time in military time */
	private static final int UPPER_TIME = 2400;
	/** The upper bound for an hour in minutes */
	private static final int UPPER_HOUR = 60;

	/**
	 * Constructor method for making an activity which takes the following parameters and checks
	 * their validity
	 * @param title the title of the activity
	 * @param meetingDays the days of the week the activity meets
	 * @param startTime the time the activity starts each meeting time
	 * @param endTime the time the activity ends each meeting time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Gets the title of the activity
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the activity checking that the title is not null of of 0 length
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException();
		}
		if (title.length() == 0) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Gets the days of the week the activity meets
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the days of the week the activity meets
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if meetingDays is null or not equal to 
	 * ‘M’, ‘T’, ‘W’, ‘H’, ‘F’, 'S', 'U', or ‘A’ depending on if the Activity is 
	 * an event or course
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Gets the time the activity starts each day it meets
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the time the activity ends each day it meets
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Method for setting the activity start and end times and checking that they are valid military
	 * times
	 * @param startTime the time the activity starts, should be a valid military time
	 * @param endTime the time the activity ends, should be a valid military time
	 */
	public void setActivityTime(int startTime, int endTime) {
		int startHour = startTime / 100;
		int startMinute = startTime - (startHour * 100);
		int endHour = endTime / 100;
		int endMinute = endTime - (endHour * 100);
		if (startTime < 0000 || startMinute >= UPPER_HOUR || startTime >= UPPER_TIME) { //bad military time
			throw new IllegalArgumentException();
		}
		if (endTime < 0000 || endMinute >= UPPER_HOUR || endTime >= UPPER_TIME) { //bad military time
			throw new IllegalArgumentException();
		}
		if ((startTime % (UPPER_HOUR * 10) == 0) && !(startTime % 100 == 0)) { //checking that the minute never equals 60
			throw new IllegalArgumentException();
		}
		if ((endTime % (UPPER_HOUR * 10) == 0) && !(endTime % 100 == 0)) { //checking that the minute never equals 60
			throw new IllegalArgumentException();
		}
		if (this.getMeetingDays().equals("A") && startTime != 0000 && endTime != 0000) {
			throw new IllegalArgumentException();
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Method for returning a string containing the days and times an activity meets
	 * @return output a string with the activity meeting formatted DDD - HH:MMAM-HH:MMPM
	 */
	public String getMeetingString() {
		String output = "";
		if (!this.getMeetingDays().contentEquals("A")) {
			int intStartTime = this.getStartTime();
			int intEndTime = this.getEndTime();
			int midDay = UPPER_TIME / 2;
			int startHour = intStartTime / 100;
			int startMinute = intStartTime - (startHour * 100);
			String startAMPM = "";
			if (intStartTime >= midDay) {
				if (!(intStartTime < (midDay + UPPER_HOUR))) {
					intStartTime = intStartTime - midDay;
					startHour = intStartTime / 100;
				}
				startAMPM = "PM";
			} else {
				if (startHour == 0) {
					startHour = 12;
				}
				startAMPM = "AM";
			}
			String stringStartTime = "";
			if (startMinute == 0) {
				stringStartTime = "" + startHour + ":" + "0" + startMinute + startAMPM;
			} else {
				stringStartTime = "" + startHour + ":" + startMinute + startAMPM;
			}
			int endHour = intEndTime / 100;
			int endMinute = intEndTime - (endHour * 100);
			String endAMPM = "";
			if (intEndTime >= midDay) {
				if (!(intEndTime < (midDay + UPPER_HOUR))) {
					intEndTime = intEndTime - midDay;
					endHour = intEndTime / 100;
				}
				endAMPM = "PM";
			} else {
				if (endHour == 0) {
					endHour = 12;
				}
				endAMPM = "AM";
			}
			String stringEndTime = "";
			if (endMinute == 0) {
				stringEndTime = "" + endHour + ":" + "0" + endMinute + endAMPM;
			} else {
				stringEndTime = "" + endHour + ":" + endMinute + endAMPM;
			}
			output = this.getMeetingDays() + " " + stringStartTime + "-" + stringEndTime;
		} else {
			output = "Arranged";
		}

		return output;
	}

	/**
	 * Method to get a string array which will be used to display an Activity in its short form.
	 * @return a string array containing an Activity in its short form
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Method to get a string array which will be used to display an Activity in its long form.
	 * @return a string array containing an Activity in its long form
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Method to determine the hash code for activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Method to determine if two activities are equal
	 * @param obj the object to be compared
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	/**
	 * Abstraction method to check if an activity is a duplicate
	 * @param activity the activity to be compared to Activity
	 * @return true if the activities are duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);


	/**
	 * Method to check if there is a possible conflict in scheduling between activities.
	 * The method compares the passed in activity to this and throws a ConflictException if there
	 * is a conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String a1Days = this.getMeetingDays();
		String a2Days = possibleConflictingActivity.getMeetingDays();

		//checking to see if the activities meet on the same days. If they do not, it is not
		//possible to be in conflict

		boolean overlapDays = false;

		if (!a1Days.equals("A") || !a2Days.equals("A")) {
			for (int i = 0; i < a1Days.length(); i++) {
				String a = "" + a1Days.charAt(i);
				for (int j = 0; j < a2Days.length(); j++) {
					String b = "" + a2Days.charAt(j);
					if (a.equals(b)) {
						overlapDays = true;
					}
				}
			} 
		}
		if (overlapDays) {

			int a1Start = this.getStartTime();
			int a1End = this.getEndTime();
			int a2Start = possibleConflictingActivity.getStartTime();
			int a2End = possibleConflictingActivity.getEndTime();

			if (a1End >= a2Start && a1Start <= a2Start || a2End >= a1Start && a2Start <= a1Start) {
				throw new ConflictException();
			}

		}

	}



}