package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Student class and getter methods
 * @author Christopher Wagner
 *
 */
public class StudentTest {
	// Valid Testing Variables
	private static final String FIRST_NAME = "Chris";
	private static final String LAST_NAME = "Wagner";
	private static final String ID = "cwwagne2";
	private static final String EMAIL = "cwwagne2@ncsu.edu";
	private static final String PASSWORD = "password";
	private static final int CREDITS = 4;

	/**
	 * Testing hashCode() method for Student
	 */
	@Test
	public void testHashCode() {

		// student 3 has different credits from students 1 and 2
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);

		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		assertNotEquals(s2.hashCode(), s3.hashCode());

	}
	/**
	 * Test both constructors for valid and invalid inputs
	 * @throws IllegalArgumentException if student first name or id is null
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {

		// Test valid constructor which calls super constructor
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(s1.getFirstName(), FIRST_NAME);
		assertEquals(s1.getMaxCredits(), 18);

		// Test invalid first name
		Student s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Test invalid ID
		Student s2 = null;
		try {
			s2 = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}

	}

	/**
	 * Tests invalid email
	 * @throws IllegalArgumentException if email is null, empty, missing '@' symbol, missing '.', or if the last
	 * occurence of the '.' character comes before the '@' character.
	 */
	@Test
	public void testSetEmail() {
		Student s1 = null;

		// Test null email
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}

		// Test for empty email
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}

		// Test for no "@"
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "invalid.email", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}

		// Test for no "."
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "invalid@email", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}

		// Test if "." is before "@"
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "inva.lid@emailcom", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}

	}
	/**
	 * Test invalid password
	 * @throws IllegalArgumentException if password is null or empty
	 */
	@Test
	public void testSetPassword() {
		Student s = null;
		//test null password
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
			assertNull(s);
		}

		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
			assertNull(s);
		}
	}
	/**
	 * Test setting max credits
	 * @throws IllegalArgumentException if credits are less than 3 or greater than 18
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = null;

		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid max credits");
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid max credits");
			assertNull(s);
		}

	}

	/**
	 * Method to test setFirstName().
	 * @throws IllegalArgumentException if first name is null or empty
	 */
	@Test
	public void testSetFirstName() {
		Student s1 = null;

		// Test null first name
		try {
			s1 = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid first name");
			assertNull(s1);
		}

		// Test empty first name
		try {
			s1 = new Student("", LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid first name");
			assertNull(s1);
		}
	}

	/**
	 * Method to test setLastName().
	 * @throws IllegalArgumentException if last name is null or empty
	 */
	@Test
	public void testSetLastName() {
		Student s1 = null;

		// Test null last name
		try {
			s1 = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid last name");
			assertNull(s1);
		}

		// Test empty last name
		try {
			s1 = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid last name");
			assertNull(s1);
		}
	}

	/**
	 * Testing hashCode() method for Student
	 */
	@Test
	public void testEqualsObject() {

		// student 3 has different credits from students 1 and 2
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, ID, "different@email.com", PASSWORD);
		Student s5 = new Student("different", LAST_NAME, ID, EMAIL, PASSWORD);
		Student s6 = new Student(FIRST_NAME, "different", ID, EMAIL, PASSWORD);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different");

		assertTrue(s1.equals(s2));
		assertFalse(s3.equals(s2));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

	}
	
	/**
	 * test toString method
	 */
	@Test
	public void testToString() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String s1String = s1.toString();
		assertEquals("Chris,Wagner,cwwagne2,cwwagne2@ncsu.edu,password,18", s1String);
	}
	
	/**
	 * Method to test the compareTo() method in student. This method will test by comparing different
	 * last names, different first names, and different unity ID's. This method will also test when they
	 * are equal.
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		
		int x = s1.compareTo(s2);
		int y = s2.compareTo(s1);
		
		assertEquals(x, 0); //should be the same
		assertEquals(y, 0); //should be the same
		
		try {
			Student s3 = null;
			s1.compareTo(s3);
			fail();
		} catch (NullPointerException e) {
			assertTrue(s1.equals(new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD))); //making sure it doesnt change
		}
		
		s2.setLastName("Smith"); //changing the last name of student 2
		
		int z = s1.compareTo(s2); //should be -1
		
		int a = s2.compareTo(s1); //should be 1
		
		assertEquals(-1, z);
		assertEquals(1, a);
		
		//setting s2 to have the same last name as s1, but changing the first name of s2
		s2.setLastName(LAST_NAME); //changing the last name of student 2
		s2.setFirstName("Brian");
		
		int b = s1.compareTo(s2); //should be -1
		
		int c = s2.compareTo(s1); //should be 1
		
		assertEquals(-1, b);
		assertEquals(1, c);
		
		//setting s4 to have the same names as s1 but have different unity ids
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "bealonso", EMAIL, PASSWORD);
		
		int d = s1.compareTo(s4); //should be -1
		
		int e = s4.compareTo(s1); //should be 1
		
		assertEquals(-1, d);
		assertEquals(1, e);
		
		
		
		
	}

}
