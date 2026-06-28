import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }


    // Helper

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 86400000);
    }

    private Appointment makeAppointment(String id) {
        return new Appointment(id, futureDate(), "Annual checkup with the doctor.");
    }

    // Add appointments with a unique ID

    @Test
    void testAddAppointmentSucceeds() {
        service.addAppointment(makeAppointment("ID001"));
        assertEquals("Annual checkup with the doctor.",
            service.getAppointment("ID001").getDescription());
    }

    @Test
    void testAddDuplicateIdThrows() {
        service.addAppointment(makeAppointment("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.addAppointment(makeAppointment("ID001")));
    }

    @Test
    void testAddMultipleUniqueAppointmentsSucceeds() {
        service.addAppointment(makeAppointment("ID001"));
        service.addAppointment(makeAppointment("ID002"));
        service.addAppointment(makeAppointment("ID003"));
        assertEquals("Annual checkup with the doctor.",
            service.getAppointment("ID002").getDescription());
    }

    @Test
    void testAddNullAppointmentThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addAppointment(null));
    }

    // Delete appointments per appointment ID

    @Test
    void testDeleteAppointmentSucceeds() {
        service.addAppointment(makeAppointment("ID001"));
        service.deleteAppointment("ID001");
        // After deletion, looking up that ID should throw
        assertThrows(IllegalArgumentException.class,
            () -> service.getAppointment("ID001"));
    }

    @Test
    void testDeleteNonExistentIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteAppointment("MISSING"));
    }

    @Test
    void testDeleteOneDoesNotAffectOthers() {
        service.addAppointment(makeAppointment("ID001"));
        service.addAppointment(makeAppointment("ID002"));
        service.deleteAppointment("ID001");
        // ID002 should still be reachable...
        assertDoesNotThrow(() -> service.getAppointment("ID002"));
    }
}
