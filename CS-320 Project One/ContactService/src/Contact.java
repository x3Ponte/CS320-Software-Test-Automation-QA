public class Contact {

    private final String contactId;   // required.. unique, max 10 chars, not null, and also not updatable
    private String firstName;         // required. max 10 chars not null
    private String lastName;          // required. max 10 chars not null
    private String phone;             // required. has to be exactly 10 digits not null
    private String address;           // required. with a max 30 chars and not null

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {

        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException(
                "Contact ID must not be null and must be 10 characters or fewer.");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException(
                "First name must not be null and must be 10 characters or fewer.");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException(
                "Last name must not be null and must be 10 characters or fewer.");
        }
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException(
                "Phone must not be null and must be exactly 10 digits.");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException(
                "Address must not be null and must be 30 characters or fewer.");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.phone     = phone;
        this.address   = address;
    }

    // Getters
    public String getContactId() { return contactId; }
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getPhone()      { return phone; }
    public String getAddress()    { return address; }

    // Setters (contactId intentionally omitted not updatable)
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException(
                "First name must not be null and must be 10 characters or fewer.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException(
                "Last name must not be null and must be 10 characters or fewer.");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException(
                "Phone must not be null and must be exactly 10 digits.");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException(
                "Address must not be null and must be 30 characters or fewer.");
        }
        this.address = address;
    }
}
