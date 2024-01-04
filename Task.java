
public class Task {
	
	private String title;
	private String description;
	private String date;
	private boolean isCompleted;
	
	public Task(String title, String description, String date, boolean isCompleted) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.isCompleted = isCompleted;
	}
	/*
	 * 
	 * Set the value of title
	 * 
	 * @param title    The title of the object
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/*
	 * 
	 * Get the value of title
	 * 
	 * @return		Returns the title of the object
	 * 
	 */
	public String getTitle() {
		return this.title;
	}
	/*
	 * 
	 * Set the description of the object
	 * 
	 * @param description		The description on the object
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	 * 
	 * Get the description of the object
	 * 
	 * @return		Returns the description of the object
	 * 
	 */
	public String getDescription() {
		return this.description;
	}
	/*
	 * 
	 * Set the date of the object
	 * 
	 * @param date		The date to set of the object
	 * 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/*
	 * 
	 * Get the date of the object
	 * 
	 * @return		Returns the date of the object
	 * 
	 */
	public String getDate() {
		return this.date;
	}
	/*
	 * 
	 * Set the status of the object
	 * 
	 * @param isCompleted		Set the value of status for the object
	 * 
	 */
	public void setStatus(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	/*
	 * 
	 * Get the status of the object
	 * 
	 * @return		Returns the status of the object
	 * 
	 */
	public boolean getStatus() {
		return this.isCompleted;
	}
	
}
