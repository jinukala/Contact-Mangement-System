package com.gl.app.dao;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import com.gl.app.entity.Contact;

public interface ContactDAO {
	public void addContact(Contact contact) throws SQLException;
	List<Contact> getContacts() throws SQLException;
	void sortContact(String criteria) throws SQLException;
	//Use Java8  Streams API for sorting 
	//Sorts the contacts based on criteria 
	Contact getContactByID(int id) throws SQLException;
	void importContacts(Stream<String> contactsData);

}
