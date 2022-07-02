package com.adnane;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    @Test
    public void shouldCreateContact(){
        // Given
        ContactManager contactManager = new ContactManager();
        // When
        contactManager.addContact("Adnane", "Chahid", "0123456789");
        // Then
        Collection<Contact> contacts = contactManager.getAllContacts();
        assertFalse(contacts.isEmpty());
        assertEquals(1, contacts.size());
//        assertTrue(contacts.stream().filter(
//                c -> c.getFirstName().equals("Adnane") &&
//                c.getLastName().equals("Chahid") &&
//                c.getPhoneNumber().equals("0123456789")).findAny().isPresent());
        assertTrue(contacts.stream().anyMatch(
                c -> c.getFirstName().equals("Adnane") &&
                        c.getLastName().equals("Chahid") &&
                        c.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should Not Create Contact When Firstname is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        ContactManager contactManager = new ContactManager();
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact(null, "Chahid", "0123456789"));
    }
}