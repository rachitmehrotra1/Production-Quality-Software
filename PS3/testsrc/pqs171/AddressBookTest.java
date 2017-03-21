  package pqs171;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test Class for AddressBook Code Coverage : 84.4% , since the IOExceptions and finally block
 * with try-catch cannot be unit tested.
 * 
 * @author Rachit
 *
 */
// Alot of tests in this class would behave unexpectedly since the main entries object is Static.
// To Overcome that issue I'm creating a @After where I'm removing all the testEntries from the
// addressBook object to make sure that at least some of the test run fine. This won't be required
// in the case that Entries in AddressBook was not static. But still the tests will run as expected
// even in the case Entries was not static.
public class AddressBookTest {

  private AddressBook addressBook;
  private Entry testEntry1;
  private Entry testEntry2;
  private Entry testEntry3;
  private Entry testEntry4;
  private Entry testEntry5;

  @Before
  public void setUp() {
    addressBook = AddressBook.createAddressBook();
    testEntry1 = new Entry.EntryBuilder("Name1", "11112222").emailAddress("test1@gmail.com")
        .postalAddress("1300,Manhattan,NY").note("TestNote1").build();
    testEntry2 = new Entry.EntryBuilder("Name2", "22223333").emailAddress("test2@gmail.com")
        .postalAddress("1300,Manhattan,NY").note("TestNote2").build();
    testEntry3 = new Entry.EntryBuilder("Name3", "33334444").emailAddress("test3@gmail.com")
        .postalAddress("1300,Manhattan,NY").note("TestNote3").build();
    testEntry4 = new Entry.EntryBuilder("Name4", "44445555").emailAddress("test4@gmail.com")
        .postalAddress("1300,Manhattan,NY").note("TestNote4").build();
    testEntry5 = new Entry.EntryBuilder("Name5", "55556666").emailAddress("test5@gmail.com")
        .postalAddress("1300,Manhattan,NY").note("TestNote5").build();

    addressBook.addEntry(testEntry1);
    addressBook.addEntry(testEntry2);
    addressBook.addEntry(testEntry3);
    addressBook.addEntry(testEntry4);
  }

  @After
  public void tearDown() {
    addressBook.removeEntry(testEntry1);
    addressBook.removeEntry(testEntry2);
    addressBook.removeEntry(testEntry3);
    addressBook.removeEntry(testEntry4);
  }

  @Test
  public void testCreate() {
    assertNotNull("addressBook should not be null since we added entries", addressBook);
  }

  // Have to test AddEntry using the search option to see if the entry gets added
  // Since , the author has set the return type of addEntry to be void. And there
  // is no other way to see the list of entries in addressBook apart from toString
  // or search. A poor design choice in my opinion.
  @Test
  public void testAddEntry() {
    ArrayList<Entry> searchResult = addressBook.searchByAnyProperty(testEntry3.getName());
    assertTrue("Search Result should contain the Entry we added in setup",
        searchResult.contains(testEntry3));
  }

  /*
   * Since the Entries in addressBook is Static and that causes other tests to fail, and since in
   * this test I'm adding a new entry to the addressBook and the teardown method would not remove
   * this entry. I'm using a finally block to Ensure that this entry is removes so that it does not
   * interfere with other test. Ideally this won't be required.
   */
  @Test
  public void testAddEntry_duplicate() {
    try {
      // Adding testEntry2 for the second time to check if it gets added twice
      addressBook.addEntry(testEntry2);
      ArrayList<Entry> searchResult =
          addressBook.searchByAnyProperty(testEntry2.getEmailAddress());
      assertTrue("According to API duplicate entries should be allowed",
          searchResult.size() == 2);
    } finally {
      addressBook.removeEntry(testEntry2);
    }
  }

  @Test
  public void testRemoveEntry_positive() {
    assertTrue(
        "removeEntry should return true as we know that testEntry1 is there in addressBook",
        addressBook.removeEntry(testEntry1));
  }

  @Test
  public void testRemoveEntry_negative() {
    assertFalse("removeEntry should return false as testEntry5 is not in addressBook",
        addressBook.removeEntry(testEntry5));
  }

  // This test Fails since the author has made the ArrayList of entries Static
  // which causes this error , where the earlier entries are stored permanently
  @Test
  public void testRemoveEntry_emptyAddressBook() {
    addressBook = AddressBook.createAddressBook();
    assertFalse("removeEntry should return false as testEntry1 is not in addressBook",
        addressBook.removeEntry(testEntry1));
  }

  /*
   * In this test , We will test Search by any property using all the components possible i.e name,
   * phone , email , address , and note to make sure that the search is exhaustive and really does
   * look into all the properties for the search term. And returns one or more positive results
   */
  @Test
  public void testSearchByAnyProperty_positive() {
    // Testing name
    ArrayList<Entry> searchResult = addressBook.searchByAnyProperty(testEntry2.getName());
    assertTrue("Returned Entry should be testEntry2",
        searchResult.size() == 1 && searchResult.get(0).equals(testEntry2));
    // Testing phone number
    searchResult = addressBook.searchByAnyProperty(testEntry1.getPhoneNumber());
    assertTrue("Returned Entry should be testEntry1",
        searchResult.size() == 1 && searchResult.get(0).equals(testEntry1));
    // Testing Email
    searchResult = addressBook.searchByAnyProperty(testEntry2.getEmailAddress());
    assertTrue("Returned Entry should be testEntry2",
        searchResult.size() == 1 && searchResult.get(0).equals(testEntry2));
    // Testing Note
    searchResult = addressBook.searchByAnyProperty(testEntry3.getNote());
    assertTrue("Returned Entry should be testEntry1",
        searchResult.size() == 1 && searchResult.get(0).equals(testEntry3));
    // Testing Postal Address && More than one matching result
    searchResult = addressBook.searchByAnyProperty(testEntry3.getPostalAddress());
    assertTrue("Returned Entry should be testEntry1", searchResult.size() == 4);
  }

  @Test
  public void testSearchByAnyProperty_negative() {
    ArrayList<Entry> searchResult = addressBook.searchByAnyProperty(testEntry5.getName());
    assertFalse(
        "Nothing should be returned since the search term does not exist in addressBook",
        searchResult.size() > 0);
    searchResult = addressBook.searchByAnyProperty(testEntry5.getEmailAddress());
    assertFalse(
        "Nothing should be returned since the search term does not exist in addressBook",
        searchResult.size() > 0);
  }

  @Test
  public void testToString() {
    String expectedOutput = "Name1;11112222;1300,Manhattan,NY;test1@gmail.com;TestNote1\n"
        + "Name2;22223333;1300,Manhattan,NY;test2@gmail.com;TestNote2\n"
        + "Name3;33334444;1300,Manhattan,NY;test3@gmail.com;TestNote3\n"
        + "Name4;44445555;1300,Manhattan,NY;test4@gmail.com;TestNote4\n";
    assertEquals("The output of to string should be same as Expected output", expectedOutput,
        addressBook.toString());
  }

  /*
   * The java docs for readBookFromFile says that it would load the entries on the addresbook
   * object. But according to the code that doesn't happen.Instead It returns ArrayList<Entries> SO
   * to test if readBookFromFile actually reads entries. We'll extract the entries from file. And
   * see if the entries match what we had put in them in the setup
   */
  @Test
  public void testSavetoFileAndReadFromFile() {
    try {
      addressBook.saveBookToFile("output.txt");
      ArrayList<Entry> fileEntries = addressBook.readBookFromFile("output.txt");
      assertTrue(
          "Entries extracted from file should be equal to the entries in addressBook initially",
          fileEntries.size() == 4);
      assertTrue("Entries exacted from file should contain testEntry1",
          fileEntries.contains(testEntry1));
      assertTrue("Entries exacted from file should contain testEntry2",
          fileEntries.contains(testEntry2));
      assertTrue("Entries exacted from file should contain testEntry3",
          fileEntries.contains(testEntry3));
      assertTrue("Entries exacted from file should contain testEntry4",
          fileEntries.contains(testEntry4));

    } catch (Exception c) {
      fail("Exception found in saving/loading data");
    }
  }

  /*
   * This test fails because even though the author has a try-catch block in their readBookFromFile
   * method, all they are doing is printing a stack trace and not throwing the Eception to the
   * parent level. Thus failing this test. That catch statement can be modified to : catch
   * (FileNotFoundException e) { e.printStackTrace(); throw new FileNotFoundException(); } And that
   * would make this test work.
   * The second catch IO exception block is not reachable since FileNotFoundException is a subclass
   * of IOexception
   */
  @Test
  public void testReadFromFile_invalidFile() {
    try {
      addressBook.readBookFromFile("randomfile");
      fail("FileNotFound Exception was not thrown while reading from non existant file");
    } catch (Exception e) {
      assertTrue("FileNotFound Exception was not thrown",
          e instanceof FileNotFoundException);
    }
  }

  @Test
  public void testWriteBooktoFile_nullFilePath() {
    try {
      addressBook.saveBookToFile(null);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertTrue("Null Pointer Exception was not thrown",
          e instanceof NullPointerException);
    }
  }

  /*
   * This test fails because even though AddressBook.java:104 throws a FileNotFoundException the
   * author neither catch that exception nor did throw the exception to the caller thus failing the
   * test. This can be easily fixed by adding a "throw FileNotFoundException" to the function
   * definition
   */
  @Test
  public void testWriteBookToFile_emptyStringAsFile() {
    try {
      addressBook.saveBookToFile("");
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertTrue("FileNotFoundException was not thrown",
          e instanceof FileNotFoundException);
    }
  }

  @Test()
  public void testReadfromFile_nullFilePath() {
    try {
      addressBook.readBookFromFile(null);
      fail("Expected exception was not thrown");
    } catch (Exception e) {
      assertTrue("NullPointerException was not thrown", e instanceof NullPointerException);
    }
  }
}
