package com.gl.app.service.impl;
import com.gl.app.dao.*;
import com.gl.app.dao.impl.ContactDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import com.gl.app.entity.Contact;
import com.gl.app.service.ContactService;

public class ContactServiceImpl implements ContactService{
	ContactDAO dao=new ContactDAOImpl();
	public ContactServiceImpl() {

	}

	public ContactServiceImpl(ContactService contactService) {

	}


	@Override
	public short addContact(Contact contact) throws SQLException {
		// TODO Auto-generated method stub
		//Write code to add contact
		dao.addContact(contact);
		return 0;
	}

	@Override
	public List<Contact> getContacts() throws SQLException {
		// TODO Auto-generated method stub
		//Write code to display contact list

		return dao.getContacts();
	}

	@Override
	public void sortContact(String criteria) throws SQLException {
		// TODO Auto-generated method stub
		//Write code to sort contact list
		dao.sortContact(criteria);
	}

	@Override
	public Contact getContactByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getContactByID(id);
	}



	@Override
	public void importContacts(Stream<String> contactsData) {
		// TODO Auto-generated method stub
		//Write code to import contacts
		dao.importContacts(contactsData);
	}

}
