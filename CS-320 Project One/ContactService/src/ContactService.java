import java.util.HashMap;
import java.util.Map;

public class ContactService {

    // In memory storage.. key is the contactId
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact and then throws if the ID already exists.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException(
                "A contact with ID '" + contact.getContactId() + "' already exists.");
        }
        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes a contact by contact ID and throws if its not found.
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException(
                "No contact found with ID '" + contactId + "'.");
        }
        contacts.remove(contactId);
    }

    /**
     * This just updates the first name of the contact with the given ID
     */
    public void updateFirstName(String contactId, String firstName) {
        getContactOrThrow(contactId).setFirstName(firstName);
    }

    /**
     * Updates the last name of the contact with the given ID...
     */
    public void updateLastName(String contactId, String lastName) {
        getContactOrThrow(contactId).setLastName(lastName);
    }

    /**
     * Updates the phone number of the contact with the given ID
     */
    public void updatePhone(String contactId, String phone) {
        getContactOrThrow(contactId).setPhone(phone);
    }

    /**
     * Updates the address of the contact with the given ID
     */
    public void updateAddress(String contactId, String address) {
        getContactOrThrow(contactId).setAddress(address);
    }

    /**
     * Returns the contact with the given ID or throws if not found
     */
    public Contact getContact(String contactId) {
        return getContactOrThrow(contactId);
    }

    // Private helper to avoid repetition
    private Contact getContactOrThrow(String contactId) {
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException(
                "No contact found with ID '" + contactId + "'.");
        }
        return c;
    }
}
