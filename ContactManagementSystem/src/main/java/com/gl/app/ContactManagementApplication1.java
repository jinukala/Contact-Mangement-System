package com.gl.app;

import com.gl.app.entity.Contact;
import com.gl.app.service.ContactService;
import com.gl.app.service.impl.ContactServiceImpl;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactManagementApplication1 {
    private static final Pattern EMAIL_PATTERN = null;
    private static final Pattern PHONE_PATTERN = null;

    public static void main(String[] args) throws FileNotFoundException, SQLException {
    	List<String> contactsData = Arrays.asList(
    	        "John,john.doe@example.com,1234567890",
    	        "Jane,jane.doe@example.com,1234567891",
    	        "Bob,bob.smith@example.com,1234567892"
    	    );
    	
    	
        ContactService contactService = new ContactServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean input = true;

        while (input) {
            System.out.println("1. Add contact");
            System.out.println("2. Display contact List");
            System.out.println("3. Sort contact List");
            System.out.println("4. Get contact by ID");
            System.out.println("5. Import contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:

                    //Write code to add contact
                    System.out.print("Enter name : ");
                    String name = scanner.next();

                    String email;
                    while (true) {
                        System.out.print("Enter email : ");
                        email = scanner.next();
                        if (ContactManagementApplication1.validateEmail(email)) {
                            break;
                        } else {
                            System.out.println("Invalid emailID ");
                        }
                    }

                    String phoneNumber;
                    while (true) {
                        System.out.print("Enter phone number : ");
                        phoneNumber = scanner.next();
                        if (ContactManagementApplication1.validatePhoneNumber(phoneNumber)) {
                            break;
                        } else {
                            System.out.println("Invalid phone number ");
                        }
                    }
                    contactService.addContact(new Contact(1,name, email, phoneNumber));
                    break;
                case 2:
//Write code to display contact list
                    System.out.println("Contact list: ");
                    List<Contact> contacts = contactService.getContacts();
                    contacts.forEach(System.out::println);
                    break;
                case 3:
                    //Write code to sort contact list
                    System.out.print("Enter Criteria : ");
                    String criteria = scanner.next();
                    contactService.sortContact(criteria);
                    break;
                case 4:
                    System.out.print("Enter Contact Id : ");
                    int id = scanner.nextInt();
                    Contact contact = contactService.getContactByID(id);
                    System.out.println(contact.toString());
                    //Write code to get contact by ID
                    break;
                case 5:
                    contactService.importContacts(contactsData.stream());
                    break;

                case 6:
                    //Write code to exit
                    System.out.println("Exiting....");
                    System.exit(0);


                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    private static boolean validateEmail(String email) {

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
   private static boolean validatePhoneNumber(String phoneNumber) {

        return phoneNumber.length()==10;
    }
}