package contacts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *  This class act as base class for storing contact details of different type.
 */
public class Contact{
    private String name;
    private String phoneNumber;
    private Date creationTime;
    private Date updatedTime;

    Contact() {
        setCreationTime(new Date());
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
        setUpdatedTime(new Date());
    }

    protected void setCreationTime(Date date) {
        this.creationTime = date;
    }

    protected Date getCreationTime() {
        return this.creationTime;
    }

    protected void setUpdatedTime(Date date) {
        this.updatedTime = date;
    }

    protected Date getUpdatedTime() {
        return this.updatedTime;
    }

    protected String getPhoneNumber() {
        return this.phoneNumber;
    }

    protected String getContactInfo() {
        return "%s, %s".formatted(this.getName(), this.getPhoneNumber());
    }

    protected void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
        setUpdatedTime(new Date());
    }


    protected boolean hasNumber() {
        return !this.phoneNumber.equals("");
    }

    protected String getContactName() {
        return this.getName();
    }

    protected void showContactInfo() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        System.out.println("Name: " + this.getName());
        System.out.println("Number: " + (this.getPhoneNumber().equals("") ? "[no number]" : this.getPhoneNumber()));
        System.out.println("Time created: " + dateFormat.format(this.getCreationTime()));
        System.out.println("Time last edit: " + dateFormat.format(this.getUpdatedTime()));
    }

    protected static boolean isValidPhoneNumber(String phoneNumber) {
        String separatorRegex = "[\\s\\-]";
        String[] number = phoneNumber.split(separatorRegex);

        String groupRegex = "[A-Za-z\\d]{2,}";
        String firstGroupRegex = "\\+?[A-Za-z\\d]+";
        String firstGroupWithBracket = "\\+?\\([A-Za-z\\d]+\\)";
        String secondGroupWithBracket = "\\([A-Za-z\\d]{2,}\\)";
        boolean hasParenthesis = false;
        boolean isValidNumber = true;

        for (int i = 0; i < number.length; i++) {
            if (i == 0) {
                if (number[i].matches(firstGroupWithBracket)) {
                    hasParenthesis = true;
                } else if (!number[i].matches(firstGroupRegex)) {
                    isValidNumber = false;
                    break;
                }
            } else if (i == 1) {
                if (hasParenthesis && number[i].matches(secondGroupWithBracket)) {
                    isValidNumber = false;
                    break;
                } else if (!hasParenthesis && !number[i].matches(secondGroupWithBracket) && !number[i].matches(groupRegex)) {
                    isValidNumber = false;
                    break;
                }
            } else if (!number[i].matches(groupRegex)) {
                isValidNumber = false;
                break;
            }
        }

        return isValidNumber;
    }

}
