import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    // Helper
    private Contact makeContact(String id) {
        return new Contact(id, "John", "Doe", "5555555555", "123 Main St");
    }

    // Adding the contacts with a unique ID

    @Test
    void testAddContactSucceeds() {
        Contact c = makeContact("ID001");
        service.addContact(c);
        assertEquals("John", service.getContact("ID001").getFirstName());
    }

    @Test
    void testAddDuplicateIdThrows() {
        service.addContact(makeContact("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.addContact(makeContact("ID001")));
    }

    @Test
    void testAddMultipleUniqueContactsSucceeds() {
        service.addContact(makeContact("ID001"));
        service.addContact(makeContact("ID002"));
        service.addContact(makeContact("ID003"));
        assertEquals("John", service.getContact("ID002").getFirstName());
    }

    @Test
    void testAddNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    // Deleting contacts per contact ID..

    @Test
    void testDeleteContactSucceeds() {
        service.addContact(makeContact("ID001"));
        service.deleteContact("ID001");
        // After deletion looking up that ID should throw
        assertThrows(IllegalArgumentException.class, () -> service.getContact("ID001"));
    }

    @Test
    void testDeleteNonExistentIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteContact("MISSING"));
    }

    @Test
    void testDeleteOneDoesNotAffectOthers() {
        service.addContact(makeContact("ID001"));
        service.addContact(makeContact("ID002"));
        service.deleteContact("ID001");
        // ID002 should still be reachable
        assertDoesNotThrow(() -> service.getContact("ID002"));
    }

    // Update contact fields per contact ID

    // firstName 
    @Test
    void testUpdateFirstNameSucceeds() {
        service.addContact(makeContact("ID001"));
        service.updateFirstName("ID001", "Jane");
        assertEquals("Jane", service.getContact("ID001").getFirstName());
    }

    @Test
    void testUpdateFirstNameNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateFirstName("MISSING", "Jane"));
    }

    @Test
    void testUpdateFirstNameInvalidValueThrows() {
        service.addContact(makeContact("ID001"));
        // null value should be rejected by the Contact setter
        assertThrows(IllegalArgumentException.class,
            () -> service.updateFirstName("ID001", null));
    }

    // lastName
    @Test
    void testUpdateLastNameSucceeds() {
        service.addContact(makeContact("ID001"));
        service.updateLastName("ID001", "Smith");
        assertEquals("Smith", service.getContact("ID001").getLastName());
    }

    @Test
    void testUpdateLastNameNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateLastName("MISSING", "Smith"));
    }

    @Test
    void testUpdateLastNameInvalidValueThrows() {
        service.addContact(makeContact("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateLastName("ID001", null));
    }

    // phone numbers
    @Test
    void testUpdatePhoneSucceeds() {
        service.addContact(makeContact("ID001"));
        service.updatePhone("ID001", "1234567890");
        assertEquals("1234567890", service.getContact("ID001").getPhone());
    }

    @Test
    void testUpdatePhoneNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updatePhone("MISSING", "1234567890"));
    }

    @Test
    void testUpdatePhoneInvalidValueThrows() {
        service.addContact(makeContact("ID001"));
        // Only 5 digits — invalid
        assertThrows(IllegalArgumentException.class,
            () -> service.updatePhone("ID001", "12345"));
    }

    // and finally the addresses
    @Test
    void testUpdateAddressSucceeds() {
        service.addContact(makeContact("ID001"));
        service.updateAddress("ID001", "456 Elm Ave");
        assertEquals("456 Elm Ave", service.getContact("ID001").getAddress());
    }

    @Test
    void testUpdateAddressNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateAddress("MISSING", "456 Elm Ave"));
    }

    @Test
    void testUpdateAddressInvalidValueThrows() {
        service.addContact(makeContact("ID001"));
        // 31 characters is too long (making sure its no longer than 30 chars)
        assertThrows(IllegalArgumentException.class,
            () -> service.updateAddress("ID001", "1234567890123456789012345678901"));
    }
}
