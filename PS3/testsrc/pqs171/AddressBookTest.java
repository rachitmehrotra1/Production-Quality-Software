package pqs171;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test Class for AddressBook
 * 
 * @author Rachit
 *
 */
public class AddressBookTest {

  private AddressBook addressBook;
  private Entry testEntry1;
  private Entry testEntry2;
  private Entry testEntry3;
  private Entry testEntry4;

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

    addressBook.addEntry(testEntry1);
    addressBook.addEntry(testEntry2);
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
    addressBook.addEntry(testEntry3);
    ArrayList<Entry> searchResult = addressBook.searchByAnyProperty(testEntry3.getName());
    assertTrue("Search Result should contain the Entry we just added",
        searchResult.contains(testEntry3));
  }

  @Test
  public void testAddEntry_duplicate() {
    // Adding testEntry2 for the second time to check if it gets added twice
    addressBook.addEntry(testEntry2);
    ArrayList<Entry> searchResult =
        addressBook.searchByAnyProperty(testEntry2.getEmailAddress());
    assertTrue("According to API duplicate entries should be allowed", searchResult.size() == 2);
  }

  @Test
  public void testRemoveEntry_positive() {
    assertTrue(
        "removeEntry should return true as we know that testEntry1 is there in addressBook",
        addressBook.removeEntry(testEntry1));
  }

  @Test
  public void testRemoveEntry_negative() {
    assertFalse("removeEntry should return false as testEntry4 is not in addressBook",
        addressBook.removeEntry(testEntry4));
  }

  // This test Fails since the author has made the ArrayList of entries Static
  // which causes this error , where the earlier entries are stored permanently
  @Test
  public void testRemoveEntry_emptyAddressBook() {
    addressBook = AddressBook.createAddressBook();
    assertFalse("removeEntry should return false as testEntry1 is not in addressBook",
        addressBook.removeEntry(testEntry1));
  }
}
