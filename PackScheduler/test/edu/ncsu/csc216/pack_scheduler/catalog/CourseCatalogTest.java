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
	private static final String NAME = "CSC216";
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
		
		//Attempt to add a course that doesn't exist
		assertFalse(c1.addCourse("CSC492", TITLE, "001", CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(8, c1.getCourseCatalog().length);
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		
		//Attempt to add a course that does exist
		assertTrue(c1.addCourse(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(9, c1.getCourseCatalog().length);
		
	}
	
	/**
	 * Test WolfScheduler.removeCourse().
	 */
	@Test
	public void testRemoveCourse() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		
		//Attempt to remove from empty schedule
		assertFalse(ws.removeCourse(NAME, SECTION));
		
		//Add some courses and remove them
		assertTrue(ws.addCourse(NAME, SECTION));
		assertTrue(ws.addCourse("CSC226", "001"));
		assertTrue(ws.addCourse("CSC116", "002"));
		assertEquals(3, ws.getScheduledCourses().length);
		assertEquals(3, ws.getFullScheduledCourses().length);
		
		//Check that removing a course that doesn't exist when there are 
		//scheduled courses doesn't break anything
		assertFalse(ws.removeCourse("CSC492", "001"));
		assertEquals(3, ws.getScheduledCourses().length);
		assertEquals(3, ws.getFullScheduledCourses().length);
		
		//Remove CSC226
		assertTrue(ws.removeCourse("CSC226", "001"));
		assertEquals(2, ws.getScheduledCourses().length);
		assertEquals(2, ws.getFullScheduledCourses().length);
		
		//Remove CSC116
		assertTrue(ws.removeCourse("CSC116", "002"));
		assertEquals(1, ws.getScheduledCourses().length);
		assertEquals(1, ws.getFullScheduledCourses().length);
		
		//Remove CSC216
		assertTrue(ws.removeCourse(NAME, SECTION));
		assertEquals(0, ws.getScheduledCourses().length);
		assertEquals(0, ws.getFullScheduledCourses().length);
		
		//Check that removing all doesn't break future adds
		assertTrue(ws.addCourse("CSC230", "001"));
		assertEquals(1, ws.getScheduledCourses().length);
		assertEquals(1, ws.getFullScheduledCourses().length);
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
	 * Test WolfScheduler.exportSchedule().
	 */
	@Test
	public void testExportSchedule() {
		//Test that empty schedule exports correctly
		WolfScheduler ws = new WolfScheduler(validTestFile);
		ws.exportSchedule("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Add courses and test that exports correctly
		ws.addCourse("CSC216", "002");
		ws.addCourse("CSC226", "001");
		assertEquals(2, ws.getScheduledCourses().length);
		ws.exportSchedule("test-files/actual_schedule_export.txt");
		checkFiles("test-files/expected_schedule_export.txt", "test-files/actual_schedule_export.txt");
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
