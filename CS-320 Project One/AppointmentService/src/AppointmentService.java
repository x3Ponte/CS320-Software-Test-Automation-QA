import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    // In memory storage. key is the appointmentId
    private final Map<String, Appointment> appointments = new HashMap<>();

    /**
     * Adds a new appointment. Then throws if the ID already exists.
     */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment must not be null.");
        }
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException(
                "An appointment with ID '" + appointment.getAppointmentId() + "' already exists.");
        }
        appointments.put(appointment.getAppointmentId(), appointment);
    }

    /**
     * Deletes an appointment by appointment ID. Then also throws if not found.
     */
    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException(
                "No appointment found with ID '" + appointmentId + "'.");
        }
        appointments.remove(appointmentId);
    }

    /**
     * Returns the appointment with the given ID.. or throws if not found.
     */
    public Appointment getAppointment(String appointmentId) {
        Appointment a = appointments.get(appointmentId);
        if (a == null) {
            throw new IllegalArgumentException(
                "No appointment found with ID '" + appointmentId + "'.");
        }
        return a;
    }
}
