import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class AppointmentTest {


    // Helpers

    // Tomorrow.. guaranteed future date
    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    // Yesterday.  guaranteed past date
    private Date pastDate() {
        return new Date(System.currentTimeMillis() - 86400000);
    }

    private Appointment validAppointment() {
        return new Appointment("1234567890", futureDate(), "Annual checkup with the doctor.");
    }

    // Appointment ID tests

    @Test
    void testAppointmentIdIsStored() {
        assertEquals("1234567890", validAppointment().getAppointmentId());
    }

    @Test
    void testAppointmentIdNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment(null, futureDate(), "Annual checkup with the doctor."));
    }

    @Test
    void testAppointmentIdTooLongThrows() {
        // 11 characters. should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("12345678901", futureDate(), "Annual checkup with the doctor."));
    }

    @Test
    void testAppointmentIdExactly10CharsAllowed() {
        assertDoesNotThrow(
            () -> new Appointment("1234567890", futureDate(), "Annual checkup with the doctor."));
    }

    @Test
    void testAppointmentIdNotUpdatable() {
        // No setAppointmentId method exists... getter should always return original value
        Appointment a = validAppointment();
        assertEquals("1234567890", a.getAppointmentId());
    }

    // Appointment Date tests

    @Test
    void testAppointmentDateIsStored() {
        Date future = futureDate();
        Appointment a = new Appointment("ID001", future, "Annual checkup with the doctor.");
        assertEquals(future, a.getAppointmentDate());
    }

    @Test
    void testAppointmentDateNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("ID001", null, "Annual checkup with the doctor."));
    }

    @Test
    void testAppointmentDateInPastThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("ID001", pastDate(), "Annual checkup with the doctor."));
    }

    @Test
    void testAppointmentDateFutureAllowed() {
        assertDoesNotThrow(
            () -> new Appointment("ID001", futureDate(), "Annual checkup with the doctor."));
    }


    // Description tests
    @Test
    void testDescriptionIsStored() {
        Appointment a = validAppointment();
        assertEquals("Annual checkup with the doctor.", a.getDescription());
    }

    @Test
    void testDescriptionNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("ID001", futureDate(), null));
    }

    @Test
    void testDescriptionTooLongThrows() {
        // 51 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment("ID001", futureDate(),
                "This description is way too long and goes over fifty!"));
    }

    @Test
    void testDescriptionExactly50CharsAllowed() {
        assertDoesNotThrow(
            () -> new Appointment("ID001", futureDate(),
                "12345678901234567890123456789012345678901234567890"));
    }
}
