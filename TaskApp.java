import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskApp {
	
	boolean addTask = true;
	boolean writeFile = false;
	boolean fileRead = false;
	boolean writeCurrent;
	String currentFile;
	String input;
	String fileText;
	String[] stringValues;
	Scanner console = new Scanner(System.in);
	TaskManager taskManager = new TaskManager();
	static TaskApp app = new TaskApp();
	
	public void readFile(String filename) {
		File file = new File(filename);
		this.currentFile = filename;
		try {
			if(!app.taskManager.getTasks().isEmpty()) {
				app.taskManager.clearTasks();
			}
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String fileLine = scan.nextLine();
				stringValues = fileLine.split(" ");
				Task newTask = new Task(stringValues[1], stringValues[2], stringValues[3], Boolean.parseBoolean(stringValues[4])); //Constant indexes, because we know that there only will be 4 entries, and start with 1 because we dont need the id
				app.taskManager.addTask(newTask);
			}
			scan.close();
			app.chooseAction();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public void writeFile() {
		System.out.println("Write to a new file - 1, Write to current file - 2, Exit - 3");
		this.input = console.nextLine();
		try {
			switch(app.convertToInt(this.input)) {
				case 1:
					writeFile = true;
					writeCurrent = false;
					break;
				case 2:
					writeCurrent = true;
					writeFile = false;
					break;
				case 3:
					app.chooseAction();
					break;
				default:
					return;
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		if(writeCurrent) {
			if(this.currentFile != null) {
				try {
					FileWriter currentWritter = new FileWriter(this.currentFile);
					for(Task singleTask : taskManager.getTasks()) {
						currentWritter.write((taskManager.getIndex(singleTask)) + ". " + singleTask.getTitle() + " " + singleTask.getDescription() + " " + singleTask.getDate() + " " + singleTask.getStatus() + "\n");
					}
					currentWritter.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}else {
				System.out.println("File does not exist!");
				app.chooseAction();
			}
		}
		if(writeFile) {
			try {
				System.out.println("Enter file name: ");
				String newFile = app.console.nextLine();
				File fileDoc = new File(newFile);
				if(newFile.isBlank() || newFile.isEmpty()) {
					System.out.println("Wrong format!");
					app.chooseAction();
				}else if(fileDoc.exists() || fileDoc.isDirectory()) {
					System.out.println("File name exists!");
					app.chooseAction();
				}
				FileWriter writer = new FileWriter(newFile);
				for(Task singleTask : taskManager.getTasks()) {
					writer.write((taskManager.getIndex(singleTask)) + ". " + singleTask.getTitle() + " " + singleTask.getDescription() + " " + singleTask.getDate() + " " + singleTask.getStatus() + "\n");
				}
				writer.close();
				System.out.println("File " + newFile + " created succesfully!");
				app.chooseAction();
			} catch (IOException e) {	
				System.out.println(e);
			}
		}
	}
	
	public void createTask() {		
		while(true && this.addTask) {
			System.out.println("Enter a title: ");
			String title = console.nextLine();
			System.out.println("Enter a description: ");
			String description = console.nextLine();
			System.out.println("Enter a date: ");
			String date = console.nextLine();
			Task task = new Task(title, description, date, false);
			taskManager.addTask(task);
			System.out.println("Add more? 1 - yes, 2 - no");
			this.input = console.nextLine();
			try {
				switch(app.convertToInt(this.input)) {
					case 1:
						this.addTask = true;
						break;
					case 2:
						this.writeFile();
						app.chooseAction();
						break;
					default:
						return;
				}
			}catch(NumberFormatException e) {
				System.out.println(e);
			}
		}
		app.chooseAction();
	}
	
	public void openFile() {
		System.out.println("Open a file? 1 - yes, 2 - no");
		this.input = console.nextLine();
		try {
			switch(app.convertToInt(this.input)) {
				case 1:
					System.out.println("Enter file name:");
					String filename = console.nextLine();
					this.readFile(filename);
					app.chooseAction();
					break;
				case 2:
					app.chooseAction();
					break;
				default:
					return;
			}
		} catch(NumberFormatException e) {
			System.out.println(e);
		}
	}
	
	public void showTasks() {
		if(!taskManager.getTasks().isEmpty()) {
			for(Task singleTask : taskManager.getTasks()) {
				System.out.println((taskManager.getIndex(singleTask)) + ". " + singleTask.getTitle() + " " + singleTask.getDescription() + " " + singleTask.getDate() + " " + singleTask.getStatus() + "\n");
			}
			System.out.println("1 - Delete a task, 2 - Mark as done/undone, 3 - Archive a task, 4 - Exit");
			this.input = app.console.nextLine();
			try {
				switch(app.convertToInt(this.input)) {
					case 1:
						System.out.println("Select an id to delete");
						this.input = app.console.nextLine();
						int selection = app.convertToInt(this.input);
						if(app.taskManager.getTaskById(selection) != null) {
							app.deleteTask(selection);
							app.chooseAction();
						}else {
							System.out.println("Task does not exist!");
							app.chooseAction();
						}
						break;
					case 2:
						System.out.println("Select an id to mark as done/undone");
						this.input = app.console.nextLine();
						selection = app.convertToInt(this.input);
						if(app.taskManager.getTaskById(selection) != null) {
							app.markDone(selection);
							app.chooseAction();	
						}else {
							System.out.println("Task does not exist!");
							app.chooseAction();
						}
						break;
					case 3:
						System.out.println("Select an id to archive");
						this.input = app.console.nextLine();
						selection = app.convertToInt(this.input);
						if(app.taskManager.getTaskById(selection) != null) {
							app.archiveTask(app.convertToInt(this.input));
							app.chooseAction();
						}else {
							System.out.println("Task does not exist!");
							app.chooseAction();
						}
						break;
					case 4:
						app.chooseAction();
						break;
					default:
						return;
				}
			}catch(IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("No tasks found!");
			app.chooseAction();
		}
	}
	
	public void chooseAction() {		
		System.out.println("Create task - 1, Open file - 2, Show all tasks - 3, Show archived - 4");	
		this.input = console.nextLine();
		try {
			switch(app.convertToInt(this.input)) {
				case 1:
					app.createTask();
					break;
				case 2:
					app.openFile();
					break;
				case 3:
					app.showTasks();
				case 4:
					app.showArchived();
				default:
					return;
			}	
		}catch(NumberFormatException e) {
			System.out.println(e);
			this.chooseAction();
		}
	}
	
	public void deleteTask(int taskId) {
		Task selectedTask = app.taskManager.getTaskById(taskId);
		if(app.taskManager.getTaskCount() != 0) {
			try {
				app.taskManager.removeTask(selectedTask);
				app.chooseAction();
			}catch(IndexOutOfBoundsException e) {
				System.out.println(e);
				app.chooseAction();
			}	
		}else {
			System.out.println("There are no tasks to delete!");
			app.chooseAction();
		}
	}
	
	public void markDone(int taskId) {
		Task selectedTask = app.taskManager.getTaskById(taskId);
		if(!app.taskManager.getTasks().isEmpty()) {
			try {
				selectedTask.setStatus(!selectedTask.getStatus());
				System.out.println("Status set to - " + selectedTask.getStatus());
				app.chooseAction();
			}catch(IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("No tasks found!");
		}
	}
	
	public void archiveTask(int taskId) {
		Task selectedTask = app.taskManager.getTaskById(taskId);
		app.taskManager.archive(selectedTask);
		app.chooseAction();
	}
	
	public void unarchiveTask(int taskId) {
		Task selectedTask = app.taskManager.getArchivedTaskById(taskId);
		app.taskManager.unarchive(selectedTask);;
		app.chooseAction();
	}
	
	public void showArchived() {
		if(app.taskManager.getArchivedCount() != 0) {
			for(Task singleArchived : app.taskManager.getArchivedTasks()) {
				System.out.println((taskManager.getIndexOfArchivedTask(singleArchived)) + ". " + singleArchived.getTitle() + " " + singleArchived.getDescription() + " " + singleArchived.getDate() + " " + singleArchived.getStatus() + "\n");
			}
			System.out.println("Unarchive a task? 1 - yes, 2 - no");
			this.input = app.console.nextLine();
			try {
				switch(app.convertToInt(this.input)) {
					case 1:
						System.out.println("Select an id to unarchive");
						this.input = app.console.nextLine();
						app.unarchiveTask(app.convertToInt(input));
						app.chooseAction();
						break;
					case 2:
						app.chooseAction();
						break;
					default:
						return;
				}
			}catch(IndexOutOfBoundsException e){
				System.out.println(e);
				app.chooseAction();
			}
		}else {
			System.out.println("No archived tasks!");
			app.chooseAction();
		}
	}
	
	public int convertToInt(String number) {
		return Integer.parseInt(number);
	}
	
	public static void main(String[] args) {
		app.chooseAction();
	}
}

