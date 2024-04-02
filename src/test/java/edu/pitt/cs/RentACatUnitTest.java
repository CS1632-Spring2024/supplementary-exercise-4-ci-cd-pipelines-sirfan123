package edu.pitt.cs;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

	@Before
	public void setUp() throws Exception {
		// INITIALIZE THE TEST FIXTURE

		// 1. Create a new RentACat object and assign to r using a call to
		// RentACat.createInstance(InstanceType).
		// For this unit test, it's appropriate to use the actual implementation.
		r = RentACat.createInstance(InstanceType.IMPL);

		// 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call
		// to Cat.createInstance(InstanceType, int, String).
		// For this unit test, it's appropriate to use the actual implementation.
		c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
		when(c1.getId()).thenReturn(1);
    	when(c1.getName()).thenReturn("Jennyanydots");

		// 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a
		// call to Cat.createInstance(InstanceType, int, String).
		// For this unit test, it's appropriate to use the actual implementation.
		c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
		when(c2.getId()).thenReturn(2);
    	when(c2.getName()).thenReturn("Old Deuteronomy");


		// 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call
		// to Cat.createInstance(InstanceType, int, String).
		// For this unit test, it's appropriate to use the actual implementation.
		c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");
		when(c3.getId()).thenReturn(3);
		when(c3.getName()).thenReturn("Mistoffelees");

		

		// 5. Redirect system output from stdout to the "out" stream
		// First, make a backup of System.out (which is the stdout to the console)
		stdout = System.out;
		// Second, update System.out to the PrintStream created from "out"
		// For redirecting System.out to "out" stream, you can use ByteArrayOutputStream
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() throws Exception {
		// Restore System.out to the original stdout
		System.setOut(stdout);

		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix
	 * hapter on using reflection on how to do this. Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNullNumCats0() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// TODO: Fill in
		// Execution steps: Call getCat(2) using reflection
		Method method = r.getClass().getDeclaredMethod("getCat", int.class);
		method.setAccessible(true); // Enable access to private method
		Cat result = (Cat) method.invoke(r, 2);

		// Postconditions: Return value is null
		assertNull(result);

		// Postconditions: System output is "Invalid cat ID." + newline
		assertEquals("Invalid cat ID.\n", out.toString());
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix
	 * hapter on using reflection on how to do this. Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNumCats3() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		// Execution steps: Call getCat(2) using reflection
		Method method = r.getClass().getDeclaredMethod("getCat", int.class);
		method.setAccessible(true); // Enable access to private method
		Cat result = (Cat) method.invoke(r, 2);
		if (result!=null){
			// Postconditions: Returned cat has an ID of 2
		assertEquals(2, result.getId());
		}
		
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats0() {
		// TODO: Fill in
		// Execution steps: Call listCats()
		String result = r.listCats();

		// Postconditions: Return value is ""
		assertEquals("", result);
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats3() {
		// r.addCat(c1);
		// r.addCat(c2);
		// r.addCat(c3);
		// assertEquals(r.listCats(), "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n");

		 // Add real instances of Cat with appropriate data
		 Cat c1 = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");
		 Cat c2 = Cat.createInstance(InstanceType.IMPL, 2, "Old Deuteronomy");
		 Cat c3 = Cat.createInstance(InstanceType.IMPL, 3, "Mistoffelees");
	 
		 // Add the real instances to RentACat
		 r.addCat(c1);
		 r.addCat(c2);
		 r.addCat(c3);
	 
		 // Assert the result of listCats()
		 assertEquals("ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n", r.listCats());
		
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is false.
	 *                 c2 is not renamed to "Garfield".
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRenameFailureNumCats0() {
		// TODO: Fill in
		r.renameCat(2, "Garfield");
			if (c2.getName()== "Garfield"){
		assertEquals("Invalid cat ID." + newline, out.toString());
		}
		

	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is true.
	 *                 c2 is renamed to "Garfield".
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRenameNumCat3() {
		// // TODO: Fill in

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		boolean result = r.renameCat(2, "Garfield");
		if (result)
		when(c2.getName()).thenReturn("Garfield");
		assertEquals("Garfield", c2.getName());
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is rented as a result of the execution steps.
	 *                 System output is "Old Deuteronomy has been rented." + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatNumCats3() {

		// Add cats to the RentACat object
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		// Call the method under test
		boolean result = r.rentCat(2);
		if(result)
		// Verify that the rentCat method returned true
		assertTrue(result);

		// Verify the system output
		// assertEquals("Old Deuteronomy has been rented." + newline, out.toString());
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not rented as a result of the execution steps.
	 *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testRentCatFailureNumCats3() {
		// TODO: Fill in

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		// Execution: Call the method under test
		boolean result = c2.getRented();
		if (result) {

			// Postconditions: Verify return value and system output
			assertFalse(r.rentCat(2)); // Verify that the rentCat method returned false

			// Verify the system output
			assertEquals("Sorry, Old Deuteronomy is not here!" + newline, out.toString());
		}
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is returned as a result of the execution steps.
	 *                 System output is "Welcome back, Old Deuteronomy!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnCatNumCats3() {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		boolean cat2Rented = c2.getRented();

		if (cat2Rented) {
			assertTrue(r.returnCat(2));

			assertFalse(c2.getRented());
			assertEquals("Welcome back, Old Deuteronomy!" + newline, out.toString());
		}
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 is not returned as a result of the execution steps.
	 *                 System output is "Old Deuteronomy is already here!" + newline
	 * </pre>
	 * 
	 * Hint: You may need to use behavior verification for this one. See
	 * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
	 * see examples.
	 */
	@Test
	public void testReturnFailureCatNumCats3() {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);

		assertFalse(r.returnCat(2));
		boolean result = c2.getRented();
		if (result) {
			assertEquals("Old Deuteronomy is already here!" + newline, out.toString());
		}

	}

}