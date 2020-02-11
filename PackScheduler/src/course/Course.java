/**
 * 
 */
package course;

/**
 * Class for a that is the primary object of a course.
 * This class represents a course's Name, Title, Section, Credit hours, instructor, 
 * and the days/times the course meets
 * @author Brian Alonso
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	
	/** Course's section. */
	private String section;
	
	/** Course's credit hours */
	private int credits;
	
	/** Course's instructor */
	private String instructorId;
	
	/** The length of the section string */
	private static final int SECTION_LENGTH = 3;
	
	/** The maximum length of the name string */
	private static final int MAX_NAME_LENGTH = 6;
	
	/** The minimum length of the name string */
	private static final int MIN_NAME_LENGTH = 4;
	
	/** The maximum credits for a course */
	private static final int MAX_CREDITS = 5;
	
	/** The minimum credits for a course */
	private static final int MIN_CREDITS = 1;
	
	/**
	 * Constructs a Course object with specified values for each field
	 * @param name the name of Course
	 * @param title the title of Course
	 * @param section the section of Course
	 * @param credits number of credits for Course
	 * @param instructorId the instructor's unity ID who teaches Course
	 * @param meetingDays the days of the week Course meets
	 * @param startTime the time Course starts on each meeting day
	 * @param endTime the time Course ends on each meeting day
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}

	/**
	 * A Course given a name, title, section, credit hours, instructor's unity ID, and meeting days
	 * for course.
	 * @param name the name of Course
	 * @param title the title of Course
	 * @param section the section of Course
	 * @param credits number of credits for Course
	 * @param instructorId the instructor's unity ID who teaches Course
	 * @param meetingDays the days of the week Course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/** 
	 * Returns the name of the course
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or 
	 * greater than 6
	 */
	private void setName(String name) {
	    if (name == null) {
	        throw new IllegalArgumentException();
	    }
	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException();
	    }
	    this.name = name;
	}
	
	/**
	 * Returns the section number of the course
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the section number of the course
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null or if the 
	 * length of section is not exactly 3
	 */
	public void setSection(String section) {
		if (section == null) {
	        throw new IllegalArgumentException();
	    }
		if (section.length() != SECTION_LENGTH) {
	        throw new IllegalArgumentException();
	    }
		this.section = section;
	}
	
	/**
	 * Gets the number of credit hours for the course
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the number of credit hours for the course
	 * @param credits the credits to set
	 * @throws IllegalArgumentException credits is not between 1 and 5 inclusive
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
	        throw new IllegalArgumentException();
	    }
		this.credits = credits;
	}
	
	/**
	 * Returns the unity ID of the instructor of the course
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the unity ID of the instructor for the course
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
	        throw new IllegalArgumentException();
	    }
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Method to determine the hash code for activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	@Override
	public String[] getShortDisplayArray() {
		return new String[]{getName(), getSection(), getTitle(), getMeetingString()};
	}

	@Override
	public String[] getLongDisplayArray() {
		return new String[]{getName(), getSection(), getTitle(), "" + getCredits(), 
					getInstructorId(), getMeetingString(), ""};
	}

	@Override
	public void setMeetingDays(String meetingDays) {
		String goodDays = "MTWHFA";
		if (meetingDays == null) {
	        throw new IllegalArgumentException();
	    }
		if (meetingDays.length() == 0) {
	        throw new IllegalArgumentException();
	    }
		for (int i = 0; i < meetingDays.length(); i++) {
			if (!goodDays.contains(meetingDays.substring(i, i + 1))) {
				throw new IllegalArgumentException();
			}
		}
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException();
		}
		super.setMeetingDays(meetingDays);
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		if (getClass() != activity.getClass())
			return false;
		Course other = (Course) activity;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
