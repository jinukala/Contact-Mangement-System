package com.gl.app.dao.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.*;


import com.gl.app.dao.ContactDAO;
import com.gl.app.entity.Contact;
import com.gl.app.exception.ContactNotFoundException;
import com.gl.app.service.ContactService;
import com.gl.app.util.ContactUtil;

public class ContactDAOImpl implements ContactDAO{
	
	private Connection connection;
	private ContactUtil contactUtil=new ContactUtil();
	public ContactDAOImpl() {

	}

	public ContactDAOImpl(ContactService contactService) {

	}


	public void addContact(Contact contact) throws SQLException {
		//Write code to add contact
		connection = ContactUtil.getConnection();
		String query = "INSERT INTO contactmanagement.contact(name,email,phoneNumber)VALUES(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, contact.contactName());
		statement.setString(2, contact.contactEmail());
		statement.setString(3, contact.contactNumber());
		int count = statement.executeUpdate();
		if(count==1){
			System.out.println("Contact Added Successfully");
		}
		
    }

    public List<Contact> getContacts() throws SQLException {
		connection = ContactUtil.getConnection();
      List<Contact> contacts = new ArrayList<>();
		String query = "SELECT * FROM contactmanagement.contact";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()){
			int contactId = resultSet.getInt("contact_id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String phoneNumber =  resultSet.getString("phoneNumber");
			contacts.add(new Contact(contactId,name,email,phoneNumber));
		}
        return contacts;
    }

    public void sortContact(String criteria) throws SQLException {
		// Write code to sort contact list
		connection = ContactUtil.getConnection();
		List<Contact> contacts = new ArrayList<>();
		String query = "SELECT * FROM contactmanagement.contact order by ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, criteria);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			while (resultSet.next()) {
				int contactId = resultSet.getInt("contact_id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String phoneNumber = resultSet.getString("phoneNumber");
				contacts.add(new Contact(contactId, name, email, phoneNumber));
			}
			if(criteria.equals("name")) {
				List<Contact> sortedContacts = contacts.stream().sorted(Comparator.comparing(Contact::contactName)).toList();
				sortedContacts.forEach(System.out::println);
			}
			else if(criteria.equals("email")) {
				List<Contact> sortedContacts = contacts.stream().sorted(Comparator.comparing(Contact::contactEmail)).toList();
				sortedContacts.forEach(System.out::println);
			}
			else if(criteria.equals("phoneNumber")) {
				List<Contact> sortedContacts = contacts.stream().sorted(Comparator.comparing(Contact::contactNumber)).toList();
				sortedContacts.forEach(System.out::println);
			}
		}
		else {
			throw new ContactNotFoundException("Contact list does not have the given Criteria");
		}
	}

    
	@Override
	public Contact getContactByID(int id) throws SQLException {

	    connection = ContactUtil.getConnection();
		String query = "SELECT * FROM contactmanagement.contact WHERE contact_id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1,id);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()){
			int contactId = resultSet.getInt("contact_id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String phoneNumber =  resultSet.getString("phoneNumber");
			return  new Contact(contactId,name,email,phoneNumber);
		}

	 
	    throw  new  ContactNotFoundException("Contact Not found with given id : "+id);
	}
	

	@Override
	public void importContacts(Stream<String> contactsData) {
		// TODO Auto-generated method stub
		//Write code to import contacts
		contactsData.map(line -> line.split(","))
				.map(parts -> new Contact(1,parts[0], parts[1], parts[2])).forEach(contact -> {
			try {
				addContact(contact);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		System.out.println("Contacts imported Successfully");
	}

	

}

	
	


