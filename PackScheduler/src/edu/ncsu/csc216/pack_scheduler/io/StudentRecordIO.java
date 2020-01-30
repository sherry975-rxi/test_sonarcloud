package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * This class controls the input and output of the PackScheduler program
 * @author Brian Alonso
 * 
 */
public class StudentRecordIO  {

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
			Student addingStudent = processStudent(scan.nextLine());
			if(addingStudent != null) {
				students.add(addingStudent);
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
		try {
		PrintStream output = new PrintStream(new File (fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			output.println(studentDirectory.get(i).toString());
		}
		output.close();
		
		} catch (IOException e) {
			throw new IOException(fileName + " (Permission denied)");
		}

	}

	/**
	 * Method for processing a line containing information to create a Student
	 * @param line the input line from the FileStream
	 * @return student a Student created from the information in the file line
	 */
	private static Student processStudent(String line) {
		Student student = null;
		Scanner in = new Scanner(line);
		
		String first = null;
		String last = null;
		String id = null;
		String email = null;
		String password = null;

		in.useDelimiter(",");
		try {
			first = in.next();
			last = in.next();
			id = in.next();
			email = in.next();
			password = in.next();
		} catch (NoSuchElementException e) {
			//if one of the elements is missing, it will return null which should be skipped
			in.close();
			return null;
		}
		
		try {
			if (password.getBytes("US-ASCII").length == 32) {
				// SHA-256 Hashing is done in 256bit/32byte encoding. By checking for the password, which IS known to be hashed, it should be 32 byte
				// The MessageDigest should be 32 bytes, which when checking the byte size, matched up with the ISO-8859-1 character encoding.
				// We checked the bytesize to check that the password is hashed.
				if (in.hasNextInt()) {
					int credits = in.nextInt();
					try {
						student = new Student(first, last, id, email, password, credits);
					}
					catch (IllegalArgumentException e) {
						//Since the student's data does't make sense, a Null will be returned instead
					}
				} else {
					try {
						if(!in.hasNext()) {  //Should run if there is nothing in the credits thing, if there is no integer next
							student = new Student(first, last, id, email, password);
						} //else, it would skip it when adding to arraylist
					}
					catch (IllegalArgumentException e) {
						//Since the student's data does't make sense, a Null will be returned instead
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			//Do nothing so the entry with the invalid password will be left out.
		}

		in.close();
		return student;

	}

}
