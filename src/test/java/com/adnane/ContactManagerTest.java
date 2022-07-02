package com.adnane;

import org.junit.jupiter.api.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    // BeforeXXX Used to perform some initialization tasks for tests
    @BeforeAll
    public static void ba(){
        System.out.println("Before All");
    }

    @BeforeEach
    public void be(){
        System.out.println("Before Each");
    }

    // AfterXXX Used to perform cleanup tasks for tests
    @AfterAll
    public static void aa(){
        System.out.println("After All");
    }

    @AfterEach
    public void ae(){
        System.out.println("After Each");
    }

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

    @Test
    @DisplayName("Should Not Create Contact When Lastname is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        ContactManager contactManager = new ContactManager();
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact("Adnane", null, "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact When PhoneNumber is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        ContactManager contactManager = new ContactManager();
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact("Adnane", "Chahid", null));
    }
}