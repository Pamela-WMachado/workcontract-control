package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter department's name: ");
		String departmentsName = sc.nextLine();
		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: $");
		double bSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, WorkerLevel.valueOf(level), bSalary, new Department(departmentsName));
		System.out.print("How many contracts do this worker has? ");
		int n = sc.nextInt();
		 
		for (int i = 1; i <= n; i++) {
			sc.nextLine();
			System.out.println("Enter contract #" + i + " data: ");
			System.out.print("Date (DD/MM/YYYY): ");
			String date1 = sc.next();
			DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate contractDate = LocalDate.parse(date1, fmt1);
			System.out.print("Value per hour: ");
			double value = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(contractDate, value, hours);
			worker.saveContract(contract);
		}
		
		
		System.out.println("Enter month and year to calculate income(MM/YYYY):");
		String date2 = sc.next();
		int month = Integer.parseInt(date2.substring(0, 2));
		int year = Integer.parseInt(date2.substring(3));
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + date2 + ": " + String.format("%.2f", worker.income(year, month)));
		sc.close();
		}
}
