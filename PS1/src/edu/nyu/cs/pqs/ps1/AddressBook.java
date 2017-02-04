package edu.nyu.cs.pqs.ps1;

import java.util.HashSet;

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


}
