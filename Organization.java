package contacts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *  Save contact details of a organization.
 */
public class Organization extends Contact {
    private String address;

    protected void setAddress(String address) {
        this.address = address;
        setUpdatedTime(new Date());
    }

    protected String getAddress() {
        return this.address;
    }

    protected String getContactName() {
        return "%s".formatted(getName());
    }

    protected String getContactInfo() {
        return "%s, %s, %s".formatted(this.getName(), this.getAddress(), this.getPhoneNumber());
    }

    protected void showContactInfo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        System.out.println("Organization name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + (this.getPhoneNumber().equals("") ? "[no number]" : this.getPhoneNumber()));
        System.out.println("Time created: " + dateFormat.format(this.getCreationTime()));
        System.out.println("Time last edit: " + dateFormat.format(this.getUpdatedTime()));
    }
}
