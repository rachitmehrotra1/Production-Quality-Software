package edu.nyu.cs.pqs.ps1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AddressBook {
  // AddressBook variable to store all the Address Book Entries. Choosing HasSet due to the fast
  // retrieval time
  HashSet<AddressBookEntry> addressBook;

  private AddressBook() {
    addressBook = new HashSet<AddressBookEntry>();
  }

  public static AddressBook create() {
    return new AddressBook();
  }

  public HashSet<AddressBookEntry> getAddressBookEntries() {
    return addressBook;
  }

  public void setAddressBookEntries(HashSet<AddressBookEntry> entries) {
    addressBook = entries;
  }

  public boolean addEntry(AddressBookEntry singleEntry) {
    return addressBook.add(singleEntry);
  }

  public boolean removeEntry(AddressBookEntry singleEntry) {
    return addressBook.remove(singleEntry);
  }

  public List<AddressBookEntry> searchByName(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getName().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  public List<AddressBookEntry> searchByPhoneNumber(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getPhoneNumber().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  public List<AddressBookEntry> searchByPostalAddress(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getPostalAddress().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  public List<AddressBookEntry> searchByEmailAddress(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getEmailAddress().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

  public List<AddressBookEntry> searchByNote(String searchString) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry entry : addressBook) {
      if (entry.getNote().equals(searchString)) {
        result.add(entry);
      }
    }
    return result;
  }

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

  @Override
  public String toString() {
    StringBuilder addressBookString = new StringBuilder();
    for (AddressBookEntry entry : addressBook) {
      addressBookString.append(entry.toString());
    }
    return addressBookString.toString();
  }


}
