package contacts;

import java.util.ArrayList;

/*
 *  This class store contact list.
 */
public class PhoneBook {
    ArrayList<Contact> contacts = new ArrayList<>();

    protected ArrayList<Contact> getContacts() {
        return contacts;
    }


    protected void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    protected Contact getContact(int index) {
        return this.contacts.get(index);
    }

    protected void deleteContact(int index) {
        this.contacts.remove(index);
    }

    protected int getAllContactCount() {
        return contacts.size();
    }

   

}

