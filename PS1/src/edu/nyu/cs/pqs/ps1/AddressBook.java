package edu.nyu.cs.pqs.ps1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The main AddressBook class that stores the different entries and has various utility functions to
 * help users to add,remove,save,load the entries
 * 
 * @author Rachit
 *
 */
public class AddressBook {
  // AddressBook variable to store all the Address Book Entries. Choosing HasSet due to the fast
  // retrieval time
  HashSet<AddressBookEntry> addressBook;

  /**
   * Private Constructor to initialize the HashSet for the addressBook
   */
  private AddressBook() {
    addressBook = new HashSet<AddressBookEntry>();
  }

  /**
   * Public constructor to return a new Addressbook
   * 
   * @return
   */
  public static AddressBook create() {
    return new AddressBook();
  }

  /**
   * Getter to access all the stored Entries in HashSet
   * 
   * @return
   */
  public HashSet<AddressBookEntry> getAddressBookEntries() {
    return addressBook;
  }

  /**
   * Setter to set all the entries in HasSet
   * 
   * @param entries
   */
  public void setAddressBookEntries(HashSet<AddressBookEntry> entries) {
    addressBook = entries;
  }

  /**
   * Utility function to add a single entry to the addressbook
   * 
   * @param singleEntry
   * @return
   */
  public boolean addEntry(AddressBookEntry singleEntry) {
    return addressBook.add(singleEntry);
  }

  /**
   * Utility function to remove a single entry from the addressbook
   * 
   * @param singleEntry
   * @return
   */
  public boolean removeEntry(AddressBookEntry singleEntry) {
    return addressBook.remove(singleEntry);
  }

  /**
   * Search the address book using the Name property
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchByName(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getName().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  /**
   * Search the address book using the Phone Number property
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchByPhoneNumber(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getPhoneNumber().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  /**
   * Search the address book using the Postal Address property
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchByPostalAddress(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getPostalAddress().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  /**
   * Search the address book using the Email Address property
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchByEmailAddress(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getEmailAddress().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  /**
   * Search the address book using the Note property
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchByNote(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getNote().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  /**
   * Search the address book entries using a search String accross all the different types of
   * information stored in an entry. A kind of Search All function
   * 
   * @param searchString
   * @return
   */
  public List<AddressBookEntry> searchAddressBookEntry(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getName().equals(searchString) || entry.getEmailAddress().equals(searchString)
          || entry.getNote().equals(searchString) || entry.getPhoneNumber().equals(searchString)
          || entry.getPostalAddress().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;

  }

  /**
   * toString Function overriden to use StringBuilder which is the more efficient way to contruct a
   * string which will display all the stored entries
   * 
   * @return
   */
  @Override
  public String toString() {
    StringBuilder addressBookString = new StringBuilder();
    for (AddressBookEntry entry : addressBook) {
      addressBookString.append(entry.toString());
    }
    return addressBookString.toString();
  }

  /**
   * The following code is based on the tutorial
   * http://crunchify.com/how-to-write-json-object-to-file-in-java/ that teaches how to write json
   * objects from java using the JSON SIMPLE v1.1.1 library
   * 
   * @param filePath
   */
  @SuppressWarnings("unchecked")
  public void saveAddressBook(String filePath) {
    JSONArray entries = new JSONArray();
    for (AddressBookEntry entry : addressBook) {
      entries.add(new JSONObject(entry.toSaveFormat()));
    }
    try {
      FileWriter saveToFile = new FileWriter(filePath);
      saveToFile.write(entries.toJSONString());
      saveToFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The following code is based on the tutorial
   * http://crunchify.com/how-to-read-json-object-from-file-in-java/ using the JSON SIMPLE v1.1.1
   * library
   * 
   * @param filePath
   */
  public void loadAddressBook(String filePath) {
    JSONParser parser = new JSONParser();
    try {
      Object parse = parser.parse(new FileReader(filePath));
      JSONArray entries = (JSONArray) parse;
      addressBook = new HashSet<AddressBookEntry>();
      for (Object entry : entries) {
        JSONObject currentEntry = (JSONObject) entry;
        AddressBookEntry currEntry =
            new AddressBookEntry.EntryBuilder((String) currentEntry.get("Name"),
                (String) currentEntry.get("Phone Number"))
                    .postalAddress((String) currentEntry.get("Postal Address"))
                    .emailAddress((String) currentEntry.get("Email Address"))
                    .note((String) currentEntry.get("Note")).build();
        addressBook.add(currEntry);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
