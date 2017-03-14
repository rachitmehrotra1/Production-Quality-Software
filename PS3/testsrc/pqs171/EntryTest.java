package pqs171;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test Class for Entry Class 
 * Code Coverage : 100%
 * @author Rachit
 *
 */
public class EntryTest {

  private Entry entry;

  @Before
  public void setUp() {
    entry = new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
  }

  /*
   * This Test fails because Name is allowed to be set null , which according to the javadocs of
   * Entry class is a mandatory requirement. Thus should not be allowed.
   */
  @Test
  public void testEntry_nullName() {
    Entry testEntry = new Entry.EntryBuilder(null, "0123456789").build();
    assertNotNull("Name Should not be null", testEntry.getName());

  }

  /*
   * This Test fails because Phone Number is allowed to be set null , which according to the
   * javadocs of the Entry class is a mandatory requirement. Thus should not be allowed.
   */
  @Test
  public void testEntry_nullPhoneNumber() {
    Entry testEntry = new Entry.EntryBuilder("John D", null).build();
    assertNotNull("Phone Number Should not be null", testEntry.getPhoneNumber());

  }

  @Test
  public void testGetters() {
    Entry testEntry = new Entry.EntryBuilder("J Doe", "123123123")
        .emailAddress("random@gmail.com").postalAddress("test").note("test note").build();
    assertEquals("Name should be same as what was set initially", "J Doe", testEntry.getName());
    assertEquals("Phone num should be same as what was set initially", "123123123",
        testEntry.getPhoneNumber());
    assertEquals("Email should be same as what was set initially", "random@gmail.com",
        testEntry.getEmailAddress());
    assertEquals("Postal Address should be same as what was set initially", "test",
        testEntry.getPostalAddress());
    assertEquals("Note should be same as what was set initially", "test note",
        testEntry.getNote());
  }

  @Test
  public void testEquals_positive() {
    Entry testEntry = new Entry.EntryBuilder("John Doe", "0123456789")
        .emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertEquals("The two entries were not equal", testEntry, entry);
  }

  /*
   * Going to test negative based on all the different component one by one
   */
  @Test
  public void testEquals_negative() {
    // Different Name
    Entry testEntry = new Entry.EntryBuilder("Jane Doe", "0123456789")
        .emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Name", testEntry.equals(entry));

    // Different Phone Number
    testEntry = new Entry.EntryBuilder("John Doe", "0123456700")
        .emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Phone Number",
        testEntry.equals(entry));

    // Different Email
    testEntry = new Entry.EntryBuilder("John Doe", "0123456789")
        .emailAddress("janedoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Email", testEntry.equals(entry));

    // Different Address
    testEntry =
        new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Houdson county , NJ , 07306").note("Test Note")
            .build();
    assertFalse("Two entries should not be equal - Different Address", testEntry.equals(entry));

    // Different Note
    testEntry =
        new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306")
            .note("Test Note Version 2").build();
    assertFalse("Two entries should not be equal - Different Note", testEntry.equals(entry));
  }

  @Test
  public void testEquals_sameObject() {
    assertTrue("Should be equal since it's same object", entry.equals(entry));
  }

  @Test
  public void testEquals_null() {
    assertFalse("Entry should not be equal to null", entry.equals(null));
  }

  @Test
  public void testEquals_notEntryObject() {
    assertFalse("Entry should not be equal to other object",
        entry.equals(new String("Test String")));
  }

  @Test
  public void testEquals_twoNullEntryObjects() {
    entry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null).note(null)
        .build();
    Entry testEntry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null)
        .note(null).build();
    assertTrue("Two null Entry objects should be equal", entry.equals(testEntry));
  }

  @Test
  public void testHashCode_positive() {
    Entry testEntry = new Entry.EntryBuilder("John Doe", "0123456789")
        .emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertTrue("The hashcode of two equal objects should not be equal",
        entry.hashCode() == testEntry.hashCode());
  }

  @Test
  public void testHashCode_negative() {
    Entry testEntry = new Entry.EntryBuilder("Jane Doe", "0123456789")
        .emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("The hashcode of two unequal objects should not be equal",
        entry.hashCode() == testEntry.hashCode());
  }

  @Test
  public void testToString() {
    String expectedOutput = "John Doe;0123456789;APT 3 , Test Ave , Jersey City "
        + ", NJ , 07306;johndoe@gmail.com;Test Note";
    assertEquals("To String of Entry should match the output expected", entry.toString(),
        expectedOutput);
  }

  @Test
  public void testHashCode_withOneEntryNull() {
    Entry testEntry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null)
        .note(null).build();
    assertFalse("HashCode of differenty entries should not be same",
        testEntry.hashCode() == entry.hashCode());
  }

  @Test
  public void testEquals_extendedToTestFlowControl() {
    Entry testEntry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null)
        .note(null).build();
    assertFalse("Equals of a null Entry with any other entry with some value should be false",
        testEntry.equals(entry));
    // To test the flow control where calling object has values , and some values of the parameter
    // of
    // equals are null
    entry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null).note(null)
        .build();
    testEntry = new Entry.EntryBuilder(null, null).emailAddress("random@gmail.com")
        .postalAddress(null).note(null).build();
    assertFalse("Objects are not equal", entry.equals(testEntry));
    testEntry = new Entry.EntryBuilder("Jane Doe", null).emailAddress(null).postalAddress(null)
        .note(null).build();
    assertFalse("Objects are not equal", entry.equals(testEntry));
    testEntry = new Entry.EntryBuilder(null, null).emailAddress(null).postalAddress(null)
        .note("Test Note 2").build();
    assertFalse("Objects are not equal", entry.equals(testEntry));
    testEntry = new Entry.EntryBuilder(null, "123123").emailAddress(null).postalAddress(null)
        .note(null).build();
    assertFalse("Objects are not equal", entry.equals(testEntry));
    testEntry = new Entry.EntryBuilder(null, null).emailAddress(null)
        .postalAddress("4th street,Manhattan").note(null).build();
    assertFalse("Objects are not equal", entry.equals(testEntry));

  }

}
