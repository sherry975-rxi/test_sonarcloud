package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The Student class contains identifier information for each student object created.
 * Detailed information regarding the students firstName, lastName, id, email,
 * password, and the maxCredits the student can have is stored in each instance.
 * @author Christopher Wagner
 */

public class Student {
	
	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String password;
	private int maxCredits;
	static final int MAX_CREDITS = 18;

	/**
	 * Creates a Student object using their firstName, lastName, email, password, and maxCredits.
	 * @param firstName The first name of the Student
	 * @param lastName The last name of the Student
	 * @param id The id of the Student
	 * @param email The email of the Student
	 * @param password The password of the Student
	 * @param maxCredits The max credits of the Student
	 * @throws IllegalArgumentException Thrown if setters throw IllegalArgumentException
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) throws IllegalArgumentException {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * Creates a Student object using their firstName, lastName, email, password, with maxCredits being set
	 * to the MAX_CREDITS variable, which is 18 by default.
	 * @param firstName The first name of the Student
	 * @param lastName The last name of the Student
	 * @param id The id of the Student
	 * @param email The email of the Student
	 * @param password The password of the Student
	 * @throws IllegalArgumentException Thrown if setters throw IllegalArgumentException
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName,lastName,id,email,password,MAX_CREDITS);
	}
	
	/**
	 * Returns the Student's email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Set the Student's email.
	 * @param email The email to set
	 * @throws IllegalArgumentException if email is not entered in correct format
	 */
	public void setEmail(String email) {
		if((email == null) || (email.isEmpty())
				|| (!email.contains("@")) || (!email.contains("."))
				|| (email.lastIndexOf(".") < email.indexOf("@"))
				) {
			throw new IllegalArgumentException();
			}
		this.email = email;
	}


	/**
	 * Returns the Student's password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the Student's password.
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or empty string
	 */
	public void setPassword(String password) {
		if((password == null) || (password.isEmpty())) {
			throw new IllegalArgumentException();
			}
		this.password = password;
	}


	/**
	 * Returns the Student's maxCredits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}


	/**
	 * Set the Student's maxCredits.
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if credits are less than 3 or more than MAX_CREDITS
	 */
	public void setMaxCredits(int maxCredits) {
		if((maxCredits < 3) || (maxCredits > MAX_CREDITS)) { //MAX_CREDITS should be 18 so it should throw if <3 and >18
			throw new IllegalArgumentException();
			}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns the Student's first name.
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set the Student's first name.
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty string
	 */
	public void setFirstName(String firstName) {
		if((firstName == null) || (firstName.isEmpty())) {
			throw new IllegalArgumentException();
			}
		this.firstName = firstName;
	}

	/**
	 * Returns the Student's last name.
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the Student's last name.
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty string
	 */
	public void setLastName(String lastName) {
		if((lastName == null) || (lastName.isEmpty())) {
			throw new IllegalArgumentException();
			}
		this.lastName = lastName;
	}

	/**
	 * Returns the Student's id.
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set the Student's id.
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty string
	 */
	private void setId(String id) {
		if((id == null) || (id.isEmpty())) {
			throw new IllegalArgumentException();
			}
		this.id = id;
	}

	/**
	 * Generates a hasCode for Student using all fields.
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", email=" + email
				+ ", password=" + password + ", maxCredits=" + maxCredits + "]";
	}

}
