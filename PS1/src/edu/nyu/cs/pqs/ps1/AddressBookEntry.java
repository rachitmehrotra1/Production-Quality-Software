package edu.nyu.cs.pqs.ps1;

import java.util.HashMap;

/**
 * This is the entry class that stores the information of each single entry to the address book
 * according to the given problem set
 * 
 * @author Rachit
 *
 */
public class AddressBookEntry {
  // Mandatory Entry
  private String name;
  private String phoneNumber;
  // Optional Entries
  private String postalAddress;
  private String emailAddress;
  private String note;

  /**
   * Private Constructor to create an AddressBook Entry using the encapsulated Entry Builder class
   * 
   * @param builder
   */
  private AddressBookEntry(EntryBuilder builder) {
    this.name = builder.name;
    this.postalAddress = builder.postalAddress;
    this.phoneNumber = builder.phoneNumber;
    this.emailAddress = builder.emailAddress;
    this.note = builder.note;
  }

  /**
   * Getter for the name property of the entry
   * 
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for the Postal Address property of the entry
   * 
   * @return
   */
  public String getPostalAddress() {
    return postalAddress;
  }

  /**
   * Getter for the Phone Number property of the entry
   * 
   * @return
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Getter for the Email Address property of the entry
   * 
   * @return
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Getter for the Note property of the entry
   * 
   * @return
   */
  public String getNote() {
    return note;
  }

  /**
   * Setter for the Name property of the entry
   * 
   * @param data
   */
  public void setName(String data) {
    this.name = data;
  }

  /**
   * Setter for the Postal Address property of the entry
   * 
   * @param data
   */
  public void setPostalAddress(String data) {
    this.postalAddress = data;
  }

  /**
   * Setter for the Phone Number property of the entry
   * 
   * @param data
   */
  public void setPhoneNumber(String data) {
    this.phoneNumber = data;
  }

  /**
   * Setter for the Email Address property of the entry
   * 
   * @param data
   */
  public void setEmailAddress(String data) {
    this.emailAddress = data;
  }

  /**
   * Setter for the Note property of the entry
   * 
   * @param data
   */
  public void setNote(String data) {
    this.note = data;
  }

  /**
   * Custom Builder Class to create the AddressBookEntry object with Name and Phone Number as
   * Mandatory Requirements
   */
  public static class EntryBuilder {
    // Mandatory Entry
    private String name;
    private String phoneNumber;
    // Optional Entries
    private String postalAddress = "";
    private String emailAddress = "";
    private String note = "";

    /**
     * Default Constructor for the EntryBuilder
     * 
     * @param name
     * @param phoneNumber
     */
    public EntryBuilder(String name, String phoneNumber) {
      this.name = name;
      this.phoneNumber = phoneNumber;
    }

    /**
     * Build function to generate the AddressBookEntry object from the information given to Builder
     * class
     * 
     * @return
     */
    public AddressBookEntry build() {
      return new AddressBookEntry(this);
    }

    /**
     * Optional Constructor to input the postal address while building an Address Book Entry
     * 
     * @param postalAddress
     * @return
     */
    public EntryBuilder postalAddress(String postalAddress) {
      this.postalAddress = postalAddress;
      return this;
    }

    /**
     * Optional Constructor to input the email address while building an Address Book Entry
     * 
     * @param emailAddress
     * @return
     */
    public EntryBuilder emailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    /**
     * Optional Constructor to input the note while building an Address Book Entry
     * 
     * @param note
     * @return
     */
    public EntryBuilder note(String note) {
      this.note = note;
      return this;
    }

  }

  /**
   * Automatically Override the hashCode function so that 2 objects of AddressBookEntry type can be
   * differentiated when stored in a hash type storage like HashSet,HashTree etc
   * 
   * @return
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 7;
    result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((note == null) ? 0 : note.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((postalAddress == null) ? 0 : postalAddress.hashCode());
    return result;
  }

  /**
   * Override the equals function to properly compare 2 objects of addressbook entry type
   * 
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AddressBookEntry other = (AddressBookEntry) obj;
    if (emailAddress == null) {
      if (other.emailAddress != null)
        return false;
    } else if (!emailAddress.equals(other.emailAddress))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (note == null) {
      if (other.note != null)
        return false;
    } else if (!note.equals(other.note))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (postalAddress == null) {
      if (other.postalAddress != null)
        return false;
    } else if (!postalAddress.equals(other.postalAddress))
      return false;
    return true;
  }

  /**
   * The modified toString function to print an entry details if required
   * 
   * @return
   */
  @Override
  public String toString() {
    return "AddressBookEntry [name=" + name + ", phoneNumber=" + phoneNumber + ", postalAddress="
        + postalAddress + ", emailAddress=" + emailAddress + ", note=" + note + "]";
  }

  /**
   * Function required to convert the addressbookentry with all its properties into a hashmap that
   * can be used to store it to a xml,json etc file easily with every property linked to its value
   * 
   * @return
   */
  public HashMap<String, Object> toSaveFormat() {
    HashMap<String, Object> entryInSaveFormat = new HashMap<String, Object>();
    entryInSaveFormat.put("Name", this.getName());
    entryInSaveFormat.put("Phone Number", this.getPhoneNumber());
    entryInSaveFormat.put("Postal Address", this.getPostalAddress());
    entryInSaveFormat.put("Email Address", this.getEmailAddress());
    entryInSaveFormat.put("Note", this.getNote());
    return entryInSaveFormat;
  }

}
