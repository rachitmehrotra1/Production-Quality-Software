package pqs171;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
  public void testEquals_positive() {
    Entry testEntry =
        new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertEquals("The two entries were not equal", testEntry, entry);
  }

  /*
   * Going to test negative based on all the different component one by one
   */
  @Test
  public void testEquals_negative() {
    // Different Name
    Entry testEntry =
        new Entry.EntryBuilder("Jane Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Name", testEntry.equals(entry));

    // Different Phone Number
    testEntry = new Entry.EntryBuilder("John Doe", "0123456700").emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Phone Number",
        testEntry.equals(entry));

    // Different Email
    testEntry = new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("janedoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Email", testEntry.equals(entry));

    // Different Address
    testEntry = new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Houdson county , NJ , 07306").note("Test Note").build();
    assertFalse("Two entries should not be equal - Different Address", testEntry.equals(entry));

    // Different Note
    testEntry = new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
        .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note Version 2")
        .build();
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
    entry = new Entry.EntryBuilder(null, null).build();
    Entry testEntry = new Entry.EntryBuilder(null, null).build();
    assertTrue("Two null Entry objects should be equal", entry.equals(testEntry));
  }

  @Test
  public void testHashCode_positive() {
    Entry testEntry =
        new Entry.EntryBuilder("John Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertTrue("The hashcode of two equal objects should not be equal",
        entry.hashCode() == testEntry.hashCode());
  }

  @Test
  public void testHashCode_negative() {
    Entry testEntry =
        new Entry.EntryBuilder("Jane Doe", "0123456789").emailAddress("johndoe@gmail.com")
            .postalAddress("APT 3 , Test Ave , Jersey City , NJ , 07306").note("Test Note").build();
    assertFalse("The hashcode of two unequal objects should not be equal",
        entry.hashCode() == testEntry.hashCode());
  }

  @Test
  public void testToString() {
    String expectedOutput =
        "John Doe;0123456789;APT 3 , Test Ave , Jersey City , NJ , 07306;johndoe@gmail.com;Test Note";
    assertEquals("To String of Entry should match the output expected", entry.toString(),
        expectedOutput);
  }

}
