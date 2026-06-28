import java.util.HashMap;
import java.util.Map;

public class TaskService {

    // In memory storage. key is the taskId
    private final Map<String, Task> tasks = new HashMap<>();

     // Just adds a new task and throws if the ID already exists.

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null.");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "A task with ID '" + task.getTaskId() + "' already exists.");
        }
        tasks.put(task.getTaskId(), task);
    }


     // This deletes a task by task ID and Throws if not found

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException(
                "No task found with ID '" + taskId + "'.");
        }
        tasks.remove(taskId);
    }

     // Updates the name of the task with the given ID...

    public void updateName(String taskId, String name) {
        getTaskOrThrow(taskId).setName(name);
    }


     // Updates the description of the task with the given ID..

    public void updateDescription(String taskId, String description) {
        getTaskOrThrow(taskId).setDescription(description);
    }

     
     // Returns the task with the given ID or just throws if not found

    public Task getTask(String taskId) {
        return getTaskOrThrow(taskId);
    }

    // Private helper to avoid repetition
    private Task getTaskOrThrow(String taskId) {
        Task t = tasks.get(taskId);
        if (t == null) {
            throw new IllegalArgumentException(
                "No task found with ID '" + taskId + "'.");
        }
        return t;
    }
}
