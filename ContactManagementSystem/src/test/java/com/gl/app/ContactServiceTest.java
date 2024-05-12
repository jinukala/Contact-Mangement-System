package com.gl.app;

import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.dao.ContactDAO;
import com.gl.app.dao.impl.ContactDAOImpl;
import com.gl.app.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;

import com.gl.app.entity.Contact;
import com.gl.app.service.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {
    @Mock
    private Contact contact;
    private ContactService contactService;
    @InjectMocks
    private ContactDAO contactDAO = new ContactDAOImpl(contactService);

    @BeforeEach
    void setUp() {
        //write your code here
        contactService = mock(ContactService.class);
    }

    @Test
    void testAddContact() throws SQLException {
        //write your code here
        contact = new Contact(1, "xyz", "xyz@gmail.com", "9087656789");
        verify(contactService, times(1)).addContact(contact);
    }
}