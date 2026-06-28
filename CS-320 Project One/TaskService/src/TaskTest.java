import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {


    // Helper: a valid task for reuse

    private Task validTask() {
        return new Task("1234567890", "Buy Groceries", "Pick up milk, eggs, and bread from the store.");
    }


    // Task ID tests


    @Test
    void testTaskIdIsStored() {
        Task t = validTask();
        assertEquals("1234567890", t.getTaskId());
    }

    @Test
    void testTaskIdNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task(null, "Buy Groceries", "Pick up milk from the store."));
    }

    @Test
    void testTaskIdTooLongThrows() {
        // 11 characters should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Task("12345678901", "Buy Groceries", "Pick up milk from the store."));
    }

    @Test
    void testTaskIdExactly10CharsAllowed() {
        assertDoesNotThrow(
            () -> new Task("1234567890", "Buy Groceries", "Pick up milk from the store."));
    }

    @Test
    void testTaskIdNotUpdatable() {
        Task t = validTask();
        t.setName("New Name");
        assertEquals("1234567890", t.getTaskId());
    }


    // Name tests


    @Test
    void testNameIsStored() {
        Task t = validTask();
        assertEquals("Buy Groceries", t.getName());
    }

    @Test
    void testNameNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("ID001", null, "Pick up milk from the store."));
    }

    @Test
    void testNameTooLongThrows() {
        // 21 characters should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Task("ID001", "Buy Groceries Extra!!", "Pick up milk from the store."));
    }

    @Test
    void testNameExactly20CharsAllowed() {
        assertDoesNotThrow(
            () -> new Task("ID001", "Buy Groceries Extra!", "Pick up milk from the store."));
    }

    @Test
    void testSetNameUpdates() {
        Task t = validTask();
        t.setName("New Task Name");
        assertEquals("New Task Name", t.getName());
    }

    @Test
    void testSetNameNullThrows() {
        Task t = validTask();
        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
    }

    @Test
    void testSetNameTooLongThrows() {
        Task t = validTask();
        // 21 characters should throw
        assertThrows(IllegalArgumentException.class,
            () -> t.setName("Buy Groceries Extra!!"));
    }


    // Description tests

    @Test
    void testDescriptionIsStored() {
        Task t = validTask();
        assertEquals("Pick up milk, eggs, and bread from the store.", t.getDescription());
    }

    @Test
    void testDescriptionNullThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task("ID001", "Buy Groceries", null));
    }

    @Test
    void testDescriptionTooLongThrows() {
        // 51 characters should throw
        assertThrows(IllegalArgumentException.class,
            () -> new Task("ID001", "Buy Groceries",
                "Pick up milk, eggs, and bread from the grocery store!"));
    }

    @Test
    void testDescriptionExactly50CharsAllowed() {
        assertDoesNotThrow(
            () -> new Task("ID001", "Buy Groceries",
                "Pick up milk, eggs, and bread from the grocery st."));
    }

    @Test
    void testSetDescriptionUpdates() {
        Task t = validTask();
        t.setDescription("Updated description for the task.");
        assertEquals("Updated description for the task.", t.getDescription());
    }

    @Test
    void testSetDescriptionNullThrows() {
        Task t = validTask();
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));
    }

    @Test
    void testSetDescriptionTooLongThrows() {
        Task t = validTask();
        // 51 characters — should throw
        assertThrows(IllegalArgumentException.class,
            () -> t.setDescription("Pick up milk, eggs, and bread from the grocery store!"));
    }
}
