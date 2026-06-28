import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    // Helper: a valid contact for reuse
	
    private Contact validContact() {
        return new Contact("1234567890", "John", "Doe", "5555555555", "123 Main St");
    }


    // Contact ID tests

    @Test
    void testContactIdIsStored() {
        Contact c = validContact();
        assertEquals("1234567890", c.getContactId());
    }

    @Test
    void testContactIdNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact(null, "John", "Doe", "5555555555", "123 Main St"));
    }

    @Test
    void testContactIdTooLongThrows() {
        // 11 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("12345678901", "John", "Doe", "5555555555", "123 Main St"));
    }

    @Test
    void testContactIdNotUpdatable() {
        // Contact has no setContactId method — verified at compile-time by design.
        // We confirm the getter still returns the original value after other fields change.
        Contact c = validContact();
        c.setFirstName("Jane");
        assertEquals("1234567890", c.getContactId());
    }

    //  The first name tests


    @Test
    void testFirstNameIsStored() {
        Contact c = validContact();
        assertEquals("John", c.getFirstName());
    }

    @Test
    void testFirstNameNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", null, "Doe", "5555555555", "123 Main St"));
    }

    @Test
    void testFirstNameTooLongThrows() {
        // 11 characters... should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "Johnathannnn", "Doe", "5555555555", "123 Main St"));
    }

    @Test
    void testFirstNameExactly10CharsAllowed() {
        assertDoesNotThrow(
            () -> new Contact("ID001", "Johnathann", "Doe", "5555555555", "123 Main St"));
    }

    @Test
    void testSetFirstNameUpdates() {
        Contact c = validContact();
        c.setFirstName("Jane");
        assertEquals("Jane", c.getFirstName());
    }

    @Test
    void testSetFirstNameNullThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
    }

    @Test
    void testSetFirstNameTooLongThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("Johnathannnn"));
    }

    // Last name tests...

    @Test
    void testLastNameIsStored() {
        Contact c = validContact();
        assertEquals("Doe", c.getLastName());
    }

    @Test
    void testLastNameNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", null, "5555555555", "123 Main St"));
    }

    @Test
    void testLastNameTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doeeeeeeeeee", "5555555555", "123 Main St"));
    }

    @Test
    void testLastNameExactly10CharsAllowed() {
        assertDoesNotThrow(
            () -> new Contact("ID001", "John", "Doeeeeeeeee".substring(0, 10),
                              "5555555555", "123 Main St"));
    }

    @Test
    void testSetLastNameUpdates() {
        Contact c = validContact();
        c.setLastName("Smith");
        assertEquals("Smith", c.getLastName());
    }

    @Test
    void testSetLastNameNullThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
    }

    // Phone tests

    @Test
    void testPhoneIsStored() {
        Contact c = validContact();
        assertEquals("5555555555", c.getPhone());
    }

    @Test
    void testPhoneNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", null, "123 Main St"));
    }

    @Test
    void testPhoneTooShortThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", "555555555", "123 Main St")); // 9 digits
    }

    @Test
    void testPhoneTooLongThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", "55555555556", "123 Main St")); // 11 digits
    }

    @Test
    void testPhoneWithLettersThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", "555-555-555", "123 Main St"));
    }

    @Test
    void testSetPhoneUpdates() {
        Contact c = validContact();
        c.setPhone("1234567890");
        assertEquals("1234567890", c.getPhone());
    }

    @Test
    void testSetPhoneNullThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setPhone(null));
    }

    @Test
    void testSetPhoneInvalidThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345"));
    }

    // The addresses tests

    @Test
    void testAddressIsStored() {
        Contact c = validContact();
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void testAddressNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", "5555555555", null));
    }

    @Test
    void testAddressTooLongThrows() {
        // 31 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Contact("ID001", "John", "Doe", "5555555555",
                              "1234567890123456789012345678901")); // 31 chars
    }

    @Test
    void testAddressExactly30CharsAllowed() {
        assertDoesNotThrow(
            () -> new Contact("ID001", "John", "Doe", "5555555555",
                              "123456789012345678901234567890")); // 30 chars
    }

    @Test
    void testSetAddressUpdates() {
        Contact c = validContact();
        c.setAddress("456 Elm Ave");
        assertEquals("456 Elm Ave", c.getAddress());
    }

    @Test
    void testSetAddressNullThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }

    @Test
    void testSetAddressTooLongThrows() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class,
            () -> c.setAddress("1234567890123456789012345678901")); // 31 chars
    }
}
