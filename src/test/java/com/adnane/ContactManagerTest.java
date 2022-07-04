package com.adnane;

import org.junit.jupiter.api.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

// JUnit instantiates the test class only once
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    private static int i = 0;
    private ContactManager contactManager;

    public  ContactManagerTest(){
        // Junit instantiates the tests for each test method
        // We can change this behavior so that it will create one instance of the class
        // By adding the TestInstance annotation to the top of teh class
        System.out.println("i : " + ++i);
    }

    // BeforeXXX Used to perform some initialization tasks for tests
    @BeforeAll
    // When we use the TestInstance Lifecycle per class
    // We can remove the static keyword because we will have only one instance of the test class
    // Instead of  public static void ba()
    public void ba(){
        System.out.println("Should print Before All Tests");
    }

    @BeforeEach
    public void be(){
        System.out.println("Should execute before each Test");
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact(){
        System.out.println("shouldCreateContact");
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
        System.out.println("shouldThrowRuntimeExceptionWhenFirstNameIsNull");
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact(null, "Chahid", "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact When Lastname is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        System.out.println("shouldThrowRuntimeExceptionWhenLastNameIsNull");
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact("Adnane", null, "0123456789"));
    }

    @Test
    @DisplayName("Should Not Create Contact When PhoneNumber is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        System.out.println("shouldThrowRuntimeExceptionWhenPhoneNumberIsNull");
        assertThrows(
                RuntimeException.class,
                () -> contactManager.addContact("Adnane", "Chahid", null));
    }

    @Test
    @DisplayName("Test Contact Creation On Developer Machine")
    public void shouldCreateContactOnDev(){
        assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Adnane", "Chahid", "0123456789");
        Collection<Contact> contacts = contactManager.getAllContacts();
        assertFalse(contacts.isEmpty());
        assertEquals(1, contacts.size());
    }

    // AfterXXX Used to perform cleanup tasks for tests
    @AfterEach
    public void ae(){
        System.out.println("Should execute after each Test");
    }

    @AfterAll
    // Same as Before All we remove the static keyword
    public void aa(){
        System.out.println("Should be executed at the end of the Test");
    }
}