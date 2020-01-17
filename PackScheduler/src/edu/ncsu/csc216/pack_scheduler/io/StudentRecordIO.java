package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * This class controls the input and output of the PackScheduler program
 * @author Brian Alonso
 * 
 */
public class StudentRecordIO {

	/**
	 * Method for reading the records of all students from a desired file
	 * @param fileName the name of the desired file
	 * @return students an array list containing all Students in the input file
	 * @throws FileNotFoundException thrown if unable to read the fileName
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {

		Scanner scan = new Scanner(new FileInputStream(fileName));
		ArrayList<Student> students = new ArrayList<Student>();

		while (scan.hasNextLine()) {

			try {
				Student addingStudent = processStudent(scan.nextLine());
				students.add(addingStudent);
			} catch (IllegalArgumentException e) {
				// skips the line
			}
		}
		scan.close();
		return students;
	}

	/**
	 * Method for writing the records of all students into a desired file
	 * @param fileName the name of the desired file
	 * @param studentDirectory the directory containing the Students array list
	 * @throws IOException thrown if unable to write to the fileName
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		PrintStream output = new PrintStream(new File (fileName)); // TODO Auto-generated method stub

		for (int i = 0; i < studentDirectory.size(); i++) {
			output.println(studentDirectory.get(i).toString());
		}

		output.close();

	}

	/**
	 * Method for processing a line containing information to create a Student
	 * @param line the input line from the FileStream
	 * @return student a Student created from the information in the file line
	 */
	private static Student processStudent(String line) {
		Student student = null;
		Scanner in = new Scanner(line);

		in.useDelimiter(",");

		try {
			String first = in.next();
			String last = in.next();
			String id = in.next();
			String email = in.next();
			String password = in.next();
			//System.out.println("First: " + first + "| Last: " + last + "| ID: " + id + "| Email: " + email + "| Password: " + password);

			if (in.hasNextInt()) {
				int credits = in.nextInt();
				student = new Student(first, last, id, email, password, credits);
			} else {
				student = new Student(first, last, id, email, password);
			}

		} catch (IllegalArgumentException e) {
			in.close();
			throw new IllegalArgumentException("Could not read student");
		}
		
		in.close();
		return student;

	}
	
}
