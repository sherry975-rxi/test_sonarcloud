package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
	//Valid Testing Variables
	private static final String FIRST_NAME = "Chris";
	private static final String LAST_NAME = "Wagner";
	private static final String ID = "cwwagne2";
	private static final String EMAIL = "cwwagne2@ncsu.edu";
	private static final String PASSWORD = "password";
	private static final int CREDITS = 4;

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testStudentStringStringStringStringStringInt() {
		
		//Test valid constructor which calls super constructor
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals(s1.getFirstName(), FIRST_NAME);
		assertEquals(s1.getMaxCredits(), 18);
		
		
		
		//Test invalid first name
		Student s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	@Test
	public void testStudentStringStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		Student s1 = null;
		
		//Test  invalid email
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}
		
		//Test for empty email
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}
		
		//Test for no "@"
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "invalid.email" , PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}
		
		//Test for no "."
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "invalid@email", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}
		
		//Test if "." is before "@"
		try {
			s1 = new Student(FIRST_NAME, LAST_NAME, ID, "inva.lid@emailcom", PASSWORD);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid email");
			assertNull(s1);
		}
		
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxCredits() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFirstName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
