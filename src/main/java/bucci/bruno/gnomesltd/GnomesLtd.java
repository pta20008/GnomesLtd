/**
 ** https://github.com/pta20008/GnomesLtd.git
 **/
package bucci.bruno.gnomesltd;

/**
 ** @author bruno
 **/


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Employee class
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
        if (email.length() > 3 && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email. It must be longer than 3 characters and contain '@'.");
        }
    }

    // Static method
    public static int getNextEmpNum() {
        return nextEmpNum;
    }
}

// EmployeeTest class
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

// Company class
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
                System.out.println(employee.getEmpNum() + "- " + "Name: " + employee.getName() + ", Email: " + employee.getEmail());
            }
        }
    }

    // Remove an employee by empNum
    public void removeStaff(int empNum) {
        Iterator<Employee> iterator = staff.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmpNum() == empNum) {
                iterator.remove();
                System.out.println("Employee removed successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}

// Additional functionality for Manager class
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

// Console menu system
public class GnomesLtd {
    private static boolean isAdminLoggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Manager manager = new Manager("Manager Gnome", "manager@gnomes.com", "Gnomeo", "smurf");

        Company gnomesLtd = new Company("Gnomes Ltd");

        gnomesLtd.addNewStaff(new Employee("Joe Bloggs", "jb@gmail.com"));
        gnomesLtd.addNewStaff(new Employee("Ann Banana", "ab@gmail.com"));
        gnomesLtd.addNewStaff(new Employee("Tom Thumb", "tt@gmail.com"));

        int choice;
        do {
            System.out.println("#######   Welcome to Gnomes Ltd System!  #######");
            System.out.println("1. Log in");
            System.out.println("2. View current staff");
            System.out.println("3. Add new staff");
            System.out.println("4. Remove staff");
            System.out.println("5. Exit");
            System.out.println("##############");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(manager, scanner);
                    break;
                case 2:
                    viewCurrentStaff(gnomesLtd);
                    break;
                case 3:
                    if (isAdminLoggedIn) {
                        addNewStaff(gnomesLtd, scanner);
                    } else {
                        System.out.println("Only available by an Admin. Please log in!");
                    }
                    break;
                case 4:
                    if (isAdminLoggedIn) {
                        removeStaff(gnomesLtd, scanner);
                    } else {
                        System.out.println("Only available by an Admin. Please log in!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
    // Login system
    private static void login(Manager manager, Scanner scanner) {
        System.out.print("Enter username: ");
        String enteredUsername = scanner.next();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.next();

        if (enteredUsername.equals(manager.getUsername()) && enteredPassword.equals(manager.getPassword())) {
            System.out.println("Login successful!");
            isAdminLoggedIn = true;
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }
    // Listing the staff (printing current staff)
    private static void viewCurrentStaff(Company gnomesLtd) {
        System.out.println("Current staff:");
        gnomesLtd.listEmployees(0); // Display all employees
    }
    // Receiving name and email from user to be added
    private static void addNewStaff(Company gnomesLtd, Scanner scanner) {
        System.out.print("Enter employee name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter employee email: ");
        String email = scanner.nextLine();

        Employee newEmployee = new Employee(name, email);
        gnomesLtd.addNewStaff(newEmployee);
        System.out.println("New staff added successfully!");
    }
    // Receiving the empNum from user to be deleted
    private static void removeStaff(Company gnomesLtd, Scanner scanner) {
        System.out.print("Enter empNum of the employee to remove: ");
        int empNumToRemove = scanner.nextInt();
        gnomesLtd.removeStaff(empNumToRemove);
    }
}
