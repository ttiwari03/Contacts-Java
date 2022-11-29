package contacts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *  Store contact details of a person.
 */
public class Person extends Contact {

    private String surname;
    private String gender;
    private String birthDate;

    protected String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
        setUpdatedTime(new Date());
    }

    protected void setGender(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            this.gender = gender;
        } else {
            this.gender = "";
            System.out.println("Bad gender!");
        }
        setUpdatedTime(new Date());
    }

    protected String getGender() {
        return this.gender;
    }

    protected void setBirthDate(String birthDate) {
        if (!birthDate.equals("")) {
            this.birthDate = birthDate;
        } else {
            this.birthDate = "";
            System.out.println("Bad birth date!");
        }

        setUpdatedTime(new Date());
    }

    protected String getBirthDate() {
        return this.birthDate;
    }

    protected String getContactName() {
        return "%s %s".formatted(getName(), getSurname());
    }

    protected String getContactInfo() {
        return "%s %s, %s, %s, %s".formatted(this.getName(), this.getSurname(), this.getBirthDate(), this.getGender(), this.getPhoneNumber());
    }

    protected void showContactInfo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + (this.getBirthDate().equals("") ? "[no data]" : this.getBirthDate()));
        System.out.println("Gender: " + (this.getGender().equals("") ? "[no data]" : this.getGender()));
        System.out.println("Number: " + (this.getPhoneNumber().equals("") ? "[no number]" : this.getPhoneNumber()));
        System.out.println("Time created: " + dateFormat.format(this.getCreationTime()));
        System.out.println("Time last edit: " + dateFormat.format(this.getUpdatedTime()));

    }
}
