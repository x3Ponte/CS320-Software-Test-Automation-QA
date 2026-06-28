import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }


    // Helper

    private Task makeTask(String id) {
        return new Task(id, "Buy Groceries", "Pick up milk, eggs, and bread.");
    }

    // Add tasks with a unique ID


    @Test
    void testAddTaskSucceeds() {
        service.addTask(makeTask("ID001"));
        assertEquals("Buy Groceries", service.getTask("ID001").getName());
    }

    @Test
    void testAddDuplicateIdThrows() {
        service.addTask(makeTask("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.addTask(makeTask("ID001")));
    }

    @Test
    void testAddMultipleUniqueTasksSucceeds() {
        service.addTask(makeTask("ID001"));
        service.addTask(makeTask("ID002"));
        service.addTask(makeTask("ID003"));
        assertEquals("Buy Groceries", service.getTask("ID002").getName());
    }

    @Test
    void testAddNullTaskThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }


    // Delete tasks per task ID

    @Test
    void testDeleteTaskSucceeds() {
        service.addTask(makeTask("ID001"));
        service.deleteTask("ID001");
        // After deletion.. looking up that ID should throw
        assertThrows(IllegalArgumentException.class, () -> service.getTask("ID001"));
    }

    @Test
    void testDeleteNonExistentIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteTask("MISSING"));
    }

    @Test
    void testDeleteOneDoesNotAffectOthers() {
        service.addTask(makeTask("ID001"));
        service.addTask(makeTask("ID002"));
        service.deleteTask("ID001");
        // ID002 should still be reachable
        assertDoesNotThrow(() -> service.getTask("ID002"));
    }


    // Update task fields per task ID

    // name
    @Test
    void testUpdateNameSucceeds() {
        service.addTask(makeTask("ID001"));
        service.updateName("ID001", "New Task Name");
        assertEquals("New Task Name", service.getTask("ID001").getName());
    }

    @Test
    void testUpdateNameNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateName("MISSING", "New Task Name"));
    }

    @Test
    void testUpdateNameNullThrows() {
        service.addTask(makeTask("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateName("ID001", null));
    }

    @Test
    void testUpdateNameTooLongThrows() {
        service.addTask(makeTask("ID001"));
        // 21 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> service.updateName("ID001", "Buy Groceries Extra!!"));
    }

    // --- description ---
    @Test
    void testUpdateDescriptionSucceeds() {
        service.addTask(makeTask("ID001"));
        service.updateDescription("ID001", "Updated task description.");
        assertEquals("Updated task description.", service.getTask("ID001").getDescription());
    }

    @Test
    void testUpdateDescriptionNotFoundThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateDescription("MISSING", "Updated task description."));
    }

    @Test
    void testUpdateDescriptionNullThrows() {
        service.addTask(makeTask("ID001"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateDescription("ID001", null));
    }

    @Test
    void testUpdateDescriptionTooLongThrows() {
        service.addTask(makeTask("ID001"));
        // 51 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> service.updateDescription("ID001",
                "Pick up milk, eggs, and bread from the grocery store!"));
    }
}
