package edu.nyu.cs.pqs.ps1;

public class Test {
  public static void main(String[] args){
    AddressBook temp = AddressBook.create();
//    AddressBookEntry a = new AddressBookEntry.EntryBuilder("Rachit", "3454342123").postalAddress("Random").build();
//    temp.addEntry(a);
//    a = new AddressBookEntry.EntryBuilder("Tim", "42123").note("Test note").build();
//    temp.addEntry(a);
//    System.out.println(temp.searchAddressBookEntry("Rachit"));
    System.out.println(temp.toString());
//    temp.saveAddressBook("test.txt");
    temp.loadAddressBook("test.txt");
    System.out.println(temp.toString());
    
  }
}
