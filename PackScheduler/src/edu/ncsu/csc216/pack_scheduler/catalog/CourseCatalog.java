/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * @author Brian Alonso
 *
 */
public class CourseCatalog {
	
	/**
	 * 
	 */
	private SortedList<Course> catalog;
	
	/**
	 * 
	 */
	public CourseCatalog() {
		newCourseCatalog();
		
	}

	/**
	 * 
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
		
	}
	
	/**
	 * Method that takes a filename and generates the course catalog from courses in a file.
	 * @param fileName the input file to generate the course records from. Throws illegal argument exception
	 * if the file is not found.
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
		
	}
	
	
	
	/**
	 * Method for adding a course to the schedule. If the course has already been added to the schedule, no matter the
	 * section an exception will be thrown. The method also checks the course is in the catalog.
	 * @param name the name of the course to add to the schedule
	 * @param section the section of the course to add to the schedule
	 * @return true if the course was successfully added
	 */
	/**
	 * @param name
	 * @param title
	 * @param section
	 * @param credits
	 * @param instructorID
	 * @param meetingDays
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean addCourse(String name, String title, String section, int credits, String instructorID, 
			String meetingDays, int startTime, int endTime) {
		
		Course courseToAdd = new Course(name, title, section, credits, instructorID, 
				meetingDays, startTime, endTime);
		
		boolean added = false;
		boolean validCourse = false;
		boolean inCatalog = false;
		//checking if in the catalog already
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				inCatalog = true;
			}
		}

		if (validCourse && !inCatalog) {
			catalog.add(courseToAdd);
			added = true;
		}

		return added;
	}
	
	
	/**
	 * Method for removing a course from the schedule by checking if it is in the schedule, and then removing the
	 * instance.
	 * @param idx the index of the activity to be removed
	 * @return true if the course was successfully removed
	 */
	public boolean removeCourseFromCatalog(int idx) {
		boolean removed = false;
		//checking if in the schedule and a valid index
		if (idx >= 0 && idx < catalog.size() && catalog.get(idx) != null) {
			catalog.remove(idx); //remove
			removed = true;
		}
		return removed;
	}
	
	/**
	 * Method to retrieve a course from the catalog, returns null if it does not exist in
	 * the catalog.
	 * @param name the name of the course to get from the catalog
	 * @param section the section of the course to get from the catalog
	 * @return the desired course to get from the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		Course course = null;
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) &&
					section.equals(catalog.get(i).getSection())) {
				course = catalog.get(i);
			}
		}
		return course;
	}
	
	/**
	 * Method to return a 2D String array of the course catalog from the input file.
	 * @return courses a 2D String array of the course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] courses = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			courses[i][0] = catalog.get(i).getName();
			courses[i][1] = catalog.get(i).getSection();
			courses[i][2] = catalog.get(i).getTitle();
			courses[i][3] = catalog.get(i).getMeetingString();
		}
		return courses;
	}
	
	/**
	 * Method for exporting the current catalog to the desired filename. If the file cannot be
	 * saved, an exception is thrown.
	 * @param fileName the desired location to save the file
	 * @throws IllegalArgumentException if the file is unable to be saved
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved."); 
		}

	}

}
