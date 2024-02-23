/*
 * https://github.com/pta20008/GnomesLtd.git
 */

package bucci.bruno.gnomesltd;

/**
 *
 * @author bruno
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Employee {
    private String name;
    private String email;
    private int empNum;

    private static int nextEmpNum = 1;

    // Constructors
    public Employee() {
        this.name = "Default";
        this.email = "default@email.com";
        this.empNum = nextEmpNum++;
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
        this.empNum = nextEmpNum++;
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getEmpNum() {
        return empNum;
    }

    // Mutator method
    public void setEmail(String email) {
        if (email.length() > 3) {
            this.email = email;
        } else {
            System.out.println("Invalid email. It must be longer than 3 characters.");
        }
    }

    // Static method
    public static int getNextEmpNum() {
        return nextEmpNum;
    }
}

class EmployeeTest {
    public static void main(String[] args) {
        Employee[] projectGroup = new Employee[3];

        projectGroup[0] = new Employee("Joe Bloggs", "jb@gmail.com");
        projectGroup[1] = new Employee("Ann Banana", "ab@gmail.com");
        projectGroup[2] = new Employee("Tom Thumb", "tt@gmail.com");

        System.out.println("Value of nextEmpNum: " + Employee.getNextEmpNum());

        int m = 2; // Example value for m
        searchAndDisplay(projectGroup, m);
    }

    private static void searchAndDisplay(Employee[] employees, int m) {
        System.out.println("Employees with empNum > " + m + ":");
        for (Employee employee : employees) {
            if (employee.getEmpNum() > m) {
                System.out.println(employee.getName());
            }
        }
    }
}

class Company {
    private String companyName;
    private ArrayList<Employee> staff;

    // Constructors
    public Company() {
        this.companyName = "Default Company";
        this.staff = new ArrayList<>();
    }

    public Company(String companyName) {
        this.companyName = companyName;
        this.staff = new ArrayList<>();
    }

    // Methods
    public void addNewStaff(Employee employee) {
        staff.add(employee);
    }

    public int getStaffNumber() {
        return staff.size();
    }

    public void listEmployees(int empNumThreshold) {
        System.out.println("Employees with empNum > " + empNumThreshold + ":");
        Iterator<Employee> iterator = staff.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmpNum() > empNumThreshold) {
                System.out.println(employee.getName());
            }
        }
    }
}

class Manager extends Employee {
    private String username;
    private String password;

    // Constructors
    public Manager(String name, String email, String username, String password) {
        super(name, email);
        this.username = username;
        this.password = password;
    }

    // Accessor methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class GnomesLtd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a manager
        Manager manager = new Manager("Manager Gnome", "manager@gnomes.com", "Gnomeo", "smurf");

        // Creating a company
        Company gnomesLtd = new Company("Gnomes Ltd");

        // Adding employees to the company
        gnomesLtd.addNewStaff(new Employee("Joe Bloggs", "jb@gmail.com"));
        gnomesLtd.addNewStaff(new Employee("Ann Banana", "ab@gmail.com"));
        gnomesLtd.addNewStaff(new Employee("Tom Thumb", "tt@gmail.com"));

        // Login for the manager
        System.out.print("Enter username: ");
        String enteredUsername = scanner.next();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.next();

        if (enteredUsername.equals(manager.getUsername()) && enteredPassword.equals(manager.getPassword())) {
            System.out.println("Login successful!");
            System.out.println("Current staff:");
            gnomesLtd.listEmployees(0); // Display all employees
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }

        scanner.close();
    }
}

