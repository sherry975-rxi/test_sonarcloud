package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Class to test the functionality of CourseCatalog
 * Some of the test cases have been reused from WolfSchedulerTest in GP1 
 * by @author Sarah Heckman
 * @author Brian Alonso
 *
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";

	/** Course name */
	private static final String NAME = "CSC416";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if files cannot be reset
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void tesetCourseCatalog() {
		//Test with invalid file.  Should have an empty catalog 
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, c1.getCourseCatalog().length);
		c1.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");

		//Test with valid file containing 8 courses.  Will test other methods in other tests.
		CourseCatalog c2 = new CourseCatalog();
		c2.loadCoursesFromFile(validTestFile);
		assertEquals(8, c2.getCourseCatalog().length);		
	}

	/**
	 * Test CourseCatalog.addCourse().
	 */
	@Test
	public void testAddCourse() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		assertEquals(8, c1.getCourseCatalog().length);

		//Attempt to add a course that does exist
		assertTrue(c1.addCourse(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Actual: " + c1.getCourseCatalog().length, 9, c1.getCourseCatalog().length);

		//Attempt to add a course that already exists
		assertFalse(c1.addCourse(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(9, c1.getCourseCatalog().length);

	}

	/**
	 * Test CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);

		//not a real course
		Course c = c1.getCourseFromCatalog("NotReal", "NotReal");
		assertNull(c);

		//a real course
		Course c2 = c1.getCourseFromCatalog("CSC216", "001");
		assertEquals(c2.getSection(), SECTION);

	}

	/**
	 * Test CourseCatalog.removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);

		//not a real course
		boolean c = c1.removeCourseFromCatalog("NotReal", "NotReal");
		assertFalse(c);

		//a real course
		boolean c2 = c1.removeCourseFromCatalog("CSC216", "001");
		assertTrue(c2);
		
		Course c3 = c1.getCourseFromCatalog("CSC216", "001");
		assertNull(c3);

	}

	/**
	 * Test CourseCatalog.newCourseCatalog()
	 */
	@Test
	public void testResetCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);

		assertEquals(8, c1.getCourseCatalog().length);

		c1.newCourseCatalog();

		assertEquals(0, c1.getCourseCatalog().length);

	}

	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);

		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = c1.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));

			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
