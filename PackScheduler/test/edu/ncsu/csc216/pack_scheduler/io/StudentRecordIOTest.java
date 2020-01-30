package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Suite of test cases for StudentRecordIO
 * @author Jack Lanois
 *
 */
public class StudentRecordIOTest {

	/** Valid Students*/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	/** Array of the valid students that is expected as a result*/
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	/** Password variable used to check hashed passwords when reading files*/
	private String hashPW;
	/** The hashing algorithm used by the program */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** A source file of known valid tests*/
	private static final String VALID_TEST_FILE = "test-files/student_records.txt";
	/** A source file of known general invalid tests*/
	private static final String INVALID_TEST_FILE = "test-files/invalid_student_records.txt";
	
	/**
	 * Resets the validStudents ArrayList for use in tests
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Method used to check an expected file to see if an file actually made matches the result.
	 * It fails if the actual file doesn't match the expected file, and succeeds if the files do
	 * match.
	 * @param expFile The String for the name of the expected file which is checked against.
	 * @param actFile The String for the name for the file actually made that is checked to match
	 * the expected file.
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

	/**
	 * Tests readStudentRecords() with both valid and invalid test files containing general input cases.
	 */
	@Test
	public void testReadStudentRecords() {
		//Valid Record Test
		try {
			ArrayList<Student> students = StudentRecordIO.readStudentRecords(VALID_TEST_FILE);
			assertEquals(students.size(), validStudents.length);
		} catch (FileNotFoundException e) {
			fail("Couldn't read " + VALID_TEST_FILE);
		}
		
		//Invalid Record Test
		try {
			ArrayList<Student> students = StudentRecordIO.readStudentRecords(INVALID_TEST_FILE);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Couldn't read " + INVALID_TEST_FILE);
		}
		
		
	}
	
	/**
	 * Checks writeStudentRecords() by checking if it can write to a file, and checking it with the an
	 * expected result file.
	 */
	@Test
	public void testWriteStudentRecords() {
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
			checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		} catch (IOException e) {
			fail("Unable to write file");
		}
	}

	/**
	 * Checks writeStudentRecords() by testing save cases that would make it fail to write files by
	 * writing to a place where it doesn't have permission to.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		String invalidDirectory = "non-existing-folder/actual_student_records.txt";
	    ArrayList<Student> students = new ArrayList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    try {
	    	StudentRecordIO.writeStudentRecords(invalidDirectory, students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	    	assertEquals(invalidDirectory + " (No such file or directory)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	/**
	 * Tests readStudentRecords() with unique inputs to test the different paths in its
	 * helper method, processStudent(), so as to test for weird cases the program would
	 * encounter when reading files.
	 */
	@Test
	public void testProcessStudent() {
		ArrayList<Student> s1 =  new ArrayList<Student>();
		
		try {
			s1 = StudentRecordIO.readStudentRecords("test-files/incorrect_input_records.txt");
			assertEquals(new ArrayList<Student>(), s1);
		} catch (FileNotFoundException e) {
			fail("The file should've have been read.");
		}
		
		try {
			s1 = StudentRecordIO.readStudentRecords("test-files/student_without_credits.txt");
			assertEquals(1, s1.size());
		} catch (FileNotFoundException e) {
			fail("The file should've have been read.");
		}

	}
	
	

}
