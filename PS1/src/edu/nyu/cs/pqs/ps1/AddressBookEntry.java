package edu.nyu.cs.pqs.ps1;

public class AddressBookEntry {
  // Mandatory Entry
  private String name;
  private String phoneNumber;
  // Optional Entries
  private String postalAddress;
  private String emailAddress;
  private String note;

  private AddressBookEntry(EntryBuilder builder) {
    this.name = builder.name;
    this.postalAddress = builder.postalAddress;
    this.phoneNumber = builder.phoneNumber;
    this.emailAddress = builder.emailAddress;
    this.note = builder.note;
  }

  public String getName() {
    return name;
  }

  public String getPostalAddress() {
    return postalAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getNote() {
    return note;
  }

  public void setName(String data) {
    this.name = data;
  }

  public void setPostalAddress(String data) {
    this.postalAddress = data;
  }

  public void setPhoneNumber(String data) {
    this.phoneNumber = data;
  }

  public void setEmailAddress(String data) {
    this.emailAddress = data;
  }

  public void setNote(String data) {
    this.note = data;
  }

  public static class EntryBuilder {
    // Mandatory Entry
    private String name;
    private String phoneNumber;
    // Optional Entries
    private String postalAddress = "";
    private String emailAddress = "";
    private String note = "";

    public EntryBuilder(String name, String phoneNumber) {
      this.name = name;
      this.phoneNumber = phoneNumber;
    }

    public AddressBookEntry build() {
      return new AddressBookEntry(this);
    }

    public EntryBuilder postalAddress(String postalAddress) {
      this.postalAddress = postalAddress;
      return this;
    }

    public EntryBuilder emailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public EntryBuilder note(String note) {
      this.note = note;
      return this;
    }

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((note == null) ? 0 : note.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((postalAddress == null) ? 0 : postalAddress.hashCode());
    return result;
  }

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

  @Override
  public String toString() {
    return "AddressBookEntry [name=" + name + ", phoneNumber=" + phoneNumber + ", postalAddress="
        + postalAddress + ", emailAddress=" + emailAddress + ", note=" + note + "]";
  }

}
