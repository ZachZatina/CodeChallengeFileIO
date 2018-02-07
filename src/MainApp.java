import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		// variable list
		boolean isTrue = false;
		int userInput;
		ArrayList<Employees> employeeList;
		String directory = "Employees";
		createDirectory(directory);
		createFile(directory, "aboutEmployees.txt");
		writeToFile(directory, "aboutEmployees.txt");
		employeeList = readFromFile(directory, "aboutEmployees.txt");

		// loop to print the array list
		System.out.println("");
		for (int i = 0; i < employeeList.size(); i++) {
			System.out.println((i + 1) + ") " + employeeList.get(i).getName());
		}
		// scanner creation
		Scanner scan = new Scanner(System.in);
		// loop to allow the user to keep finding things out about the employees
		while (isTrue == false) {
			System.out.println("");
			// prompts user to choose an employee
			System.out.print("Select an employee to learn more about (1 - " + employeeList.size() + "): ");
			userInput = scan.nextInt();
			scan.nextLine();
			// validation for the user input
			if (userInput < 1 || userInput > employeeList.size()) {
				System.out.println(
						"That is not a valid input, please enter the employee's full name with correct capitalization.");
				// lists the user selected employee and asks if user wants to look into another
				// employee
			} else {
				System.out.println("Name: " + employeeList.get(userInput - 1).getName() + " Age: "
						+ employeeList.get(userInput - 1).getAge() + " Favorite Food: "
						+ employeeList.get(userInput - 1).getFavFood());

				System.out.println("");
				System.out.print("Would you like to learn about another employee? (y/n): ");
				String cont = scan.nextLine();

				if (cont.equalsIgnoreCase("Y")) {
					continue;
				} else {
					isTrue = true;
				}
			}
		}
		System.out.println("Goodbye!");
		scan.close();

	}

	// method to create a folder to store everything
	public static void createDirectory(String dirString) {

		Path dirPath = Paths.get(dirString);
		// this will only execute if the file is not there
		if (Files.notExists(dirPath)) {
			try {
				Files.createDirectories(dirPath);
			} catch (IOException e) {
				System.out.println("I'm not sure what happened, contact customer service");
			}
		}

	}

	// method to create a file in the previously created folder
	public static void createFile(String dirString, String fileString) {

		Path filePath = Paths.get(dirString, fileString);

		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("Your file was created successfully");
			} catch (IOException e) {
				System.out.println("Hey, something went wrong with the file creation");
			}
		}

	}

	// method to add things to the file
	public static void writeToFile(String dirString, String fileString) {
		Path writeFile = Paths.get(dirString, fileString);
		Employees emp;
		String name;
		int age;
		String favFood;
		String cont;
		Scanner scnr = new Scanner(System.in);

		File file = writeFile.toFile(); // the toFile() converts the path to a file object

		try {
			do {
				// parses each part of the employee class and creates the class as a result
				System.out.print("Employee Name: ");
				name = scnr.nextLine();
				System.out.print("Employee age: ");
				age = scnr.nextInt();
				scnr.nextLine();
				System.out.print("Employee's favorite food: ");
				favFood = scnr.nextLine();
				// passes the parsed values to the class
				emp = new Employees(name, age, favFood);

				// outputs the class to the file.
				PrintWriter printOut = new PrintWriter(new FileOutputStream(file, true));
				printOut.println(emp);
				printOut.close(); // closing flushes our data and closes the PrintWriter (object)

				System.out.print("Would you like to add another employee? (y/n): ");
				cont = scnr.nextLine();
			} while (cont.equalsIgnoreCase("Y"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to read the data off of the file into a usable format.
	public static ArrayList<Employees> readFromFile(String dirString, String filePath) {
		Path readFile = Paths.get(dirString, filePath);
		File file = readFile.toFile();
		ArrayList<Employees> emp = new ArrayList<>();

		try {
			FileReader fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();

			while (line != null) {
				emp.add(convertToEmployees(line));
				line = reader.readLine();
			}
			reader.close(); // flushes and closes the buffer

			return emp;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong with this!");
			e.printStackTrace();
		}

		return null;

	}

	// method to convert the string back into the Employees type by parsing the
	// string into an array and re entering the values into the type
	public static Employees convertToEmployees(String fromFile) {
		Employees input;
		String name;
		int age;
		String favFood;
		String[] arr1 = fromFile.split(",");
		name = arr1[0];
		age = Integer.parseInt(arr1[1]);
		favFood = arr1[2];

		input = new Employees(name, age, favFood);
		return input;
	}

}
