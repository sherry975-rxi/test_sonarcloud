package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;



/**
 * Class to deal with the input and output of course records
 * @author Brian Alonso
 *
 */
public class CourseRecordIO {

	/**
	 * Method to read course data from a file and generate a sorted list of Courses based on the file if the
	 * courses are valid. Invalid courses will not be considered. A FileNotFoundException is thrown is the
	 * file is not able to be read. 
	 * @param validTestFile the name of the file to read courses from
	 * @return an array list of valid courses
	 * @throws FileNotFoundException if the file cannot be opened or found
	 */
	public static SortedList<Course> readCourseRecords(String validTestFile) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(validTestFile));

		SortedList<Course> courses = new SortedList<Course>();

		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) &&
							course.getSection().equals(c.getSection())) {
						//it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				//skip the line
			}
		}

		fileReader.close();

		return courses;
	}

	/**
	 * Reads the course information provided in a line by readCourseRecords()
	 * Catches any exceptions thrown by course
	 * @param nextLine the line containing the course data
	 * @return course the course created from the input line
	 */
	private static Course readCourse(String nextLine) {
		Scanner input = new Scanner(nextLine);
		Course course;

		input.useDelimiter(","); //from 116 knowledge

		try {

			String name = input.next();
			String title = input.next();
			String section = input.next();
			int credits = input.nextInt();
			String instructorId = input.next();
			String meetingDays = input.next();
			
			if (!input.hasNextInt()) {
				course = new Course(name, title, section, credits, instructorId, meetingDays);
			} else {
				int startTime = input.nextInt(); 
				int endTime = input.nextInt();
				course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			}
		} catch (NoSuchElementException e) {
			input.close();
			throw new IllegalArgumentException();
		}
		
		input.close();
		return course;
		
	}

	/**
	 * Method for writing a list of courses to a specified file
	 * @param fileName the desired writing file
	 * @param catalog an sorted list of courses to write
	 * @throws IOException if the file cannot be found or written to
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> catalog) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < catalog.size(); i++) {
			fileWriter.println(catalog.get(i).toString());
		}

		fileWriter.close();

	}

}
