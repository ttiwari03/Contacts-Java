package contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 *  This program allow
 *      - adding contact details for person and organization.
 *      - list all contacts
 *      - count all contacts
 *      - search in contacts
 */

public class Main {
    public static final Scanner readIp = new Scanner(System.in);

    public static void main(String[] args) {
        
        PhoneBook phoneBook = new PhoneBook();

        boolean closeContact = false;

        while (!closeContact) {
            System.out.println();
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String action = readIp.nextLine();

            switch (action) {
                case "add" -> addRecord(phoneBook);
                case "list" -> listRecords(phoneBook);
                case "search" -> searchContact(phoneBook);
                case "count" -> countRecords(phoneBook);
                case "exit" -> closeContact = true;
                default -> System.out.println("Please select valid action.\n");
            }
        }
    }

    private static void addRecord(PhoneBook phoneBook) {
        System.out.println("Enter the type (person, organization):");
        String type = readIp.nextLine();

        Contact contact = null;
        switch (type) {
            case "person" -> contact = addPerson();
            case "organization" -> contact = addOrganization();
        }

        phoneBook.addContact(contact);
        System.out.println("The record added.");

    }

    private static Contact addOrganization() {
        Organization organization = new Organization();

        System.out.println("Enter the organization name:");
        String orgName = readIp.nextLine();
        organization.setName(orgName);

        System.out.println("Enter the address:");
        String address = readIp.nextLine();
        organization.setAddress(address);

        System.out.println("Enter the number:");
        String number = readIp.nextLine();
        organization.setPhoneNumber(number);

        return organization;
    }

    private static Contact addPerson() {
        Person person = new Person();

        System.out.println("Enter the name:");
        String name = readIp.nextLine();
        person.setName(name);

        System.out.println("Enter the surname:");
        String surname = readIp.nextLine();
        person.setSurname(surname);

        System.out.println("Enter the birth date:");
        String birthDate = readIp.nextLine();
        person.setBirthDate(birthDate);

        System.out.println("Enter the gender (M, F):");
        String gender = readIp.nextLine();
        person.setGender(gender);

        System.out.println("Enter the number:");
        String phoneNumber = readIp.nextLine();
        person.setPhoneNumber(phoneNumber);

        return person;
    }

    private static void countRecords(PhoneBook phoneBook) {
        int totalRecord = phoneBook.getAllContactCount();
        System.out.printf("The Phone Book has %s records.%n", totalRecord);

    }

    private static void searchContact(PhoneBook phoneBook) {

        System.out.println("Enter search query:");
        String query = readIp.nextLine();
        Map<Integer, Integer> foundContactsIndex = search(query, phoneBook);

        System.out.println();
        System.out.println("[search] Enter action ([number], back, again):");
        String command = readIp.nextLine();

        if (command.equals("again")) {
            searchContact(phoneBook);
        }

        if (command.matches("\\d+")) {
            int index = foundContactsIndex.get((Integer.parseInt(command)));
            Contact contact = getRecord(index, phoneBook);
            contact.showContactInfo();
            showRecordMenu(contact, phoneBook);
        }

        if (command.equals("back")) {
            return;
        }
    }

    private static Map<Integer, Integer> search(String query, PhoneBook phoneBook) {
        ArrayList<Contact> foundContact = new ArrayList<>();
        Map<Integer, Integer> foundContactIndex = new HashMap<>();
        ArrayList<Contact> allContacts = phoneBook.getContacts();

        int key = 1;
        for (int i = 0; i < allContacts.size(); i++) {
            Contact contact = allContacts.get(i);
            if (contact.getContactInfo().toLowerCase().contains(query.toLowerCase())) {
                foundContact.add(contact);
                foundContactIndex.put(key, i);
                key++;
            }
        }

        System.out.printf("Found %s results:%n", foundContact.size());
        int count = 0;
        for (Contact contact : foundContact) {
            System.out.printf("%d %s%n", ++count, contact.getContactName());
        }

        return foundContactIndex;
    }

    private static void showRecordMenu(Contact contact, PhoneBook phoneBook) {
        System.out.println();
        System.out.println("[record] Enter action (edit, delete, menu):");
        String action = readIp.nextLine();

        if (action.equals("edit")) {
            editRecord(contact, phoneBook);
        }

        if (action.equals("delete")) {
            deleteRecord(contact, phoneBook);
        }

        if (action.equals("menu")) {
            return;
        }
    }

    private static void listRecords(PhoneBook phoneBook) {
        int count = 0;
        for (Contact contact : phoneBook.getContacts()) {
            System.out.printf("%d. %s%n", ++count, contact.getContactName());
        }

        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        String action = readIp.nextLine();

        if (action.matches("\\d+")) {
            Contact contact = getRecord((Integer.parseInt(action) - 1), phoneBook);
            contact.showContactInfo();
            showRecordMenu(contact, phoneBook);
        }

        if (action.equals("back")) {
            return;
        }

    }

    private static void editRecord(Contact contact, PhoneBook phoneBook) {
        if (contact.getClass().getSimpleName().equals("Person")) {
            editPerson((Person) contact);
        } else {
            editOrganization((Organization) contact);
        }
        System.out.println("Saved");

        contact.showContactInfo();
        showRecordMenu(contact, phoneBook);
    }

    private static void editOrganization(Organization organization) {
        System.out.println("Select a field (name, address, number):");
        String field = readIp.nextLine();

        switch (field) {
            case "name" -> {
                System.out.println("Enter the name:");
                String name = readIp.nextLine();
                organization.setName(name);
            }
            case "address" -> {
                System.out.println("Enter the address:");
                String address = readIp.nextLine();
                organization.setAddress(address);
            }
            case "number" -> {
                System.out.println("Enter the number:");
                String number = readIp.nextLine();
                organization.setPhoneNumber(number);
            }
            default -> System.out.println("Please select a valid field.");
        }
    }

    private static void editPerson(Person person) {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String field = readIp.nextLine();
        switch (field) {
            case "name" -> {
                System.out.println("Enter name:");
                String name = readIp.nextLine();
                person.setName(name);
            }
            case "surname" -> {
                System.out.println("Enter surname:");
                String surname = readIp.nextLine();
                person.setSurname(surname);
            }
            case "birth" -> {
                System.out.println("Enter birth date:");
                String birthDate = readIp.nextLine();
                person.setBirthDate(birthDate);
            }
            case "gender" -> {
                System.out.println("Enter gender:");
                String gender = readIp.nextLine();
                person.setGender(gender);
            }
            case "number" -> {
                System.out.println("Enter number:");
                String number = readIp.nextLine();
                person.setPhoneNumber(number);
            }
            default -> System.out.println("Please select valid field.");
        }
    }

    private static void deleteRecord(Contact contact, PhoneBook phoneBook) {
        ArrayList<Contact> contacts = phoneBook.getContacts();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getContactName().equals(contact.getContactName())) {
                phoneBook.deleteContact(i);
                System.out.println("The record removed!");
                System.out.println();
                break;
            }
        }
    }

    private static Contact getRecord(int index, PhoneBook phoneBook) {
        return phoneBook.getContact(index);
    }
}