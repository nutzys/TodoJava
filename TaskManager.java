import java.util.ArrayList;

public class TaskManager {
	
	private ArrayList<Task> tasks;
	private ArrayList<Task> archivedTasks;
	
	public TaskManager() {
		tasks = new ArrayList<Task>();
		archivedTasks = new ArrayList<Task>();
	}
	
	/*
	 * 
	 * Adds a object to the ArrayList
	 * 
	 * @param task		Object that will be added to the list
	 * 
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}
	/*
	 * 
	 * Removes the object from the ArrayList
	 * 
	 * @param task		Object that will be removed from the list
	 * 
	 */
	public void removeTask(Task task) {
		tasks.remove(task);
	}
	/*
	 * 
	 * Clears all the tasks which are in the ArrayList
	 * 
	 */
	public void clearTasks() {
		tasks.clear();
	}
	/*
	 * 
	 * Get the total count of the ArrayList
	 * 
	 * @return		Returns the count of the ArrayList
	 * 
	 */
	public int getTaskCount() {
		return tasks.size();
	}
	/*
	 * 
	 * Get the index of the object
	 * 
	 * @return		Returns the index of the specified object
	 * 
	 */
	public int getIndex(Task task) {
		return tasks.indexOf(task);
	}
	/*
	 * 
	 * Get all the objects
	 * 
	 * @return		Returns all the objects from the ArrayList
	 * 
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	/*
	 * 
	 * Adds a object to the archived ArrayList
	 * 
	 * @param task		Object that will be added to the list
	 * 
	 */
	public void archive(Task task) {
		archivedTasks.add(task);
		tasks.remove(task);
	}
	/*
	 * 
	 * Removes the object from the archived ArrayList and stores back in tasks
	 * 
	 * @param task		Object that will be removed from the list
	 * 
	 */
	public void unarchive(Task task) {
		archivedTasks.remove(task);
		tasks.add(task);
	}
	/*
	 * 
	 * Get the total count of the archived ArrayList
	 * 
	 * @return		Returns the count of the archived ArrayList
	 * 
	 */
	public int getArchivedCount() {
		return archivedTasks.size();
	}
	/*
	 * 
	 * Get the index of the object
	 * 
	 * @return		Returns the index of the specified object
	 * 
	 */
	public int getIndexOfArchivedTask(Task task) {
		return archivedTasks.indexOf(task);
	}
	/*
	 * 
	 * Get all the objects
	 * 
	 * @return		Returns all the objects from the archived ArrayList
	 * 
	 */
	public ArrayList<Task> getArchivedTasks(){
		return archivedTasks;
	}
	/*
	 * 
	 * Get the task by tasks id
	 * 
	 * @return		Returns the task from tasks by id
	 * 
	 */
	public Task getTaskById(int id) {
		return tasks.get(id);
	}
	/*
	 * 
	 * Get the task from archived tasks by id
	 * 
	 * @return		Returns the task from archived tasks by id
	 */
	public Task getArchivedTaskById(int id) {
		return archivedTasks.get(id);
	}
}
