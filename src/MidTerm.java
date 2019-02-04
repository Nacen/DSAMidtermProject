import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MidTerm {
    private static Scanner input = new Scanner(System.in);
    private static HashMap<String, LinkedList<String>> firstYearHMap = new HashMap<>();
    private static HashMap<String, LinkedList<String>> secondYearHMap = new HashMap<>();
    private static HashMap<String, LinkedList<String>> thirdYearHMap = new HashMap<>();
    private static HashMap<String, LinkedList<String>> fourthYearHMap = new HashMap<>();
    private static String course;
    private static int year;

    // Use regex to validate if name is valid
    private static boolean IsValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    private static void AddNewSection(String sectionName, int yearLevel) {
        // Creates the Linked List here
        // store the section name as key in the hash map
        // so we can easily retrieve it using the section name itself
        if (yearLevel == 1) {
            LinkedList<String> list = new LinkedList<>();
            firstYearHMap.put(sectionName, list);
        } else if (yearLevel == 2) {
            LinkedList<String> list = new LinkedList<>();
            secondYearHMap.put(sectionName, list);
        } else if (yearLevel == 3) {
            LinkedList<String> list = new LinkedList<>();
            thirdYearHMap.put(sectionName, list);
        } else if (yearLevel == 4) {
            LinkedList<String> list = new LinkedList<>();
            fourthYearHMap.put(sectionName, list);
        }
    }

    private static String CourseMenu() {
        String courseChoice = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Pamantasan ng Lungsod ng Marikina");
            System.out.println("College");
            System.out.println("    A. Information Technology");
            System.out.println("    B. Tourism Management");
            System.out.println("    C. Psychology");
            System.out.println("    D. Accountancy");
            System.out.println("    E. Public Administration");
            System.out.println("    F. Business Administration");
            System.out.println("    G. Criminology");
            System.out.print("Enter your choice: ");
            courseChoice = input.nextLine();
            if (!(courseChoice.equalsIgnoreCase("A") || courseChoice.equalsIgnoreCase("B") ||
                    courseChoice.equalsIgnoreCase("C") || courseChoice.equalsIgnoreCase("D") ||
                    courseChoice.equalsIgnoreCase("E") || courseChoice.equalsIgnoreCase("F") ||
                    courseChoice.equalsIgnoreCase("G"))) {
                System.out.println("Wrong input please try again.");
            } else {
                validInput = true;
            }
        }
        // passes the letter to choiceCourse Method and gets the equivalent Course
        courseChoice = choiceCourse(courseChoice);
        return courseChoice;
    }

    private static String choiceCourse(String choice) {
        if (choice.equalsIgnoreCase("A")) {
            return "Information Technology";
        } else if (choice.equalsIgnoreCase("B")) {
            return "Tourism Management";
        } else if (choice.equalsIgnoreCase("C")) {
            return "Psychology";
        } else if (choice.equalsIgnoreCase("D")) {
            return "Accountancy";
        } else if (choice.equalsIgnoreCase("E")) {
            return "Public Administration";
        } else if (choice.equalsIgnoreCase("F")) {
            return "Business Administration";
        } else if (choice.equalsIgnoreCase("G")) {
            return "Criminology";
        }
        return "";
    }

    private static int YearLevelMenu(String courseChoice) {
        System.out.println();
        System.out.println("Welcome to College of " + courseChoice + "!");
        System.out.println("Choose the year level you want to create new section: ");
        System.out.println("    A. 1st year");
        System.out.println("    B. 2nd year");
        System.out.println("    C. 3rd year");
        System.out.println("    D. 4th year");
        int yearLevel = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your choice: ");
            String yearLevelChoice = input.nextLine();
            if (yearLevelChoice.equalsIgnoreCase("A")) {
                yearLevel = 1;
                validInput = true;
            } else if (yearLevelChoice.equalsIgnoreCase("B")) {
                yearLevel = 2;
                validInput = true;
            } else if (yearLevelChoice.equalsIgnoreCase("C")) {
                yearLevel = 3;
                validInput = true;
            } else if (yearLevelChoice.equalsIgnoreCase("D")) {
                yearLevel = 4;
                validInput = true;
            } else {
                System.out.println("Wrong input please try again");
            }
        }
        String yearChoice = YearChoice(yearLevel);
        System.out.print("How many section you want to create for " + yearChoice + "?: ");
        CreateSection(yearLevel, yearChoice);
        return yearLevel;
    }

    private static String YearChoice(int yearLevel) {
        if (yearLevel == 1) {
            return "1st year";
        } else if (yearLevel == 2) {
            return "2nd year";
        } else if (yearLevel == 3) {
            return "3rd year";
        } else if (yearLevel == 4) {
            return "4th year";
        }
        return "year";
    }

    private static void SectionMenu(int yearLevel) {
        String yearChoice = YearChoice(yearLevel);
        DisplaySection(yearLevel, yearChoice);

        System.out.println();
        boolean validInput = false;
        String addOrView = "";
        while (!validInput) {
            System.out.print("Do you want to add students or view students? ADD or Display: ");
            addOrView = input.nextLine();
            if (addOrView.equalsIgnoreCase("add") || addOrView.equalsIgnoreCase("display"))
                validInput = true;
            else
                System.out.println("Wrong input please try again.");
        }
        boolean sectionExist = false;
        String sectionName = "";
        // loops over until a valid section is entered
        while (!sectionExist) {
            System.out.print("Enter the Section Name: ");
            sectionName = input.nextLine();
            if (yearLevel == 1 && firstYearHMap.containsKey(sectionName)) {
                sectionExist = true;
            } else if (yearLevel == 2 && secondYearHMap.containsKey(sectionName)) {
                sectionExist = true;
            } else if (yearLevel == 3 && thirdYearHMap.containsKey(sectionName)) {
                sectionExist = true;
            } else if (yearLevel == 4 && fourthYearHMap.containsKey(sectionName)) {
                sectionExist = true;
            } else {
                System.out.println("The section does not exist Please try again.");
            }
        }

        if (addOrView.equalsIgnoreCase("ADD")) {
            AddStudents(sectionName, yearLevel);
            String display = "";
            while (!(display.equalsIgnoreCase("Y") || display.equalsIgnoreCase("N"))) {
                System.out.print("Do you want to display the list of students in " + sectionName + "?(Y/N): ");
                display = input.nextLine();
                if (!(display.equalsIgnoreCase("Y") || display.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input please try again.");
                }
            }
            if (display.equalsIgnoreCase("Y")) {
                DisplayStudents(sectionName, yearLevel);
            } else if (display.equalsIgnoreCase("N"))
                System.out.println("You've chosen not to display");
        } else if (addOrView.equalsIgnoreCase("DISPLAY")) {
            DisplayStudents(sectionName, yearLevel);
        }
    }

    private static void AddStudents(String sectionName, int yearLevel) {
        System.out.print("Enter the number of students: ");
        // Repeat until next item is an integer
        while (!input.hasNextInt()) {
            input.next(); // Read and discard offending non-int input
            System.out.print("Invalid input please enter a positive integer and try again: "); // Re-prompt
        }
        int noOfStudents = input.nextInt();
        while (noOfStudents <= 0) {
            System.out.println("Wrong input please try again"); // Re-prompt
            System.out.println("Please try again.");
            System.out.print("Please enter a positive integer number: "); // Re-prompt
            noOfStudents = input.nextInt();
        }
        input.nextLine();
        for (int i = 0; i < noOfStudents; i++) {
            String studentName;
            do {
                System.out.print("Input Student #" + (i + 1) + " Name: ");
                studentName = input.nextLine();
                if (!IsValidName(studentName))
                    System.out.println("Invalid Name input please try again.");
            }
            while (!IsValidName(studentName));
            // add studentName to the list depending on its year level and section
            if (yearLevel == 1)
                firstYearHMap.get(sectionName).add(studentName);
            else if (yearLevel == 2)
                secondYearHMap.get(sectionName).add(studentName);
            else if (yearLevel == 3)
                thirdYearHMap.get(sectionName).add(studentName);
            else if (yearLevel == 4)
                fourthYearHMap.get(sectionName).add(studentName);
        }
        System.out.println();

    }

    private static void DisplayStudents(String sectionName, int yearLevel) {
        // iterate over the list of students and print
        int i = 0;
        if (yearLevel == 1) {
            // if the section is empty ask the user if they want to enter students into the section
            if (firstYearHMap.get(sectionName).isEmpty()) {
                System.out.println("Student List of " + sectionName + ": ");
                System.out.println("There are currently no students in this section");
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Would you like to Add Students in this section? Y/N: ");
                    String addChoice = input.nextLine();
                    while (!(addChoice.equalsIgnoreCase("Y") || addChoice.equalsIgnoreCase("N"))) {
                        System.out.println("Invalid input please try again.");
                        System.out.print("Would you like to Add Students in this section? Y/N: ");
                        addChoice = input.nextLine();
                    }
                    if (addChoice.equalsIgnoreCase("Y")) {
                        AddStudents(sectionName, yearLevel);
                        validInput = true;
                    } else if (addChoice.equalsIgnoreCase("N"))
                        validInput = true;
                }
            }
            // prints out the list of students
            System.out.println("Student List of " + sectionName + ": ");
            for (String student : firstYearHMap.get(sectionName)) {
                System.out.println("Student #" + (i + 1) + ":" + student);
                i++;
            }
        } else if (yearLevel == 2) {
            // if the section is empty ask the user if they want to enter students into the section
            if (secondYearHMap.get(sectionName).isEmpty()) {
                System.out.println("Student List of " + sectionName + ": ");
                System.out.println("There are currently no students in this section");
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Would you like to Add Students in this section? Y/N: ");
                    String addChoice = input.nextLine();
                    while (!(addChoice.equalsIgnoreCase("Y") || addChoice.equalsIgnoreCase("N"))) {
                        System.out.println("Invalid input please try again.");
                        System.out.print("Would you like to Add Students in this section? Y/N: ");
                        addChoice = input.nextLine();
                    }
                    if (addChoice.equalsIgnoreCase("Y")) {
                        AddStudents(sectionName, yearLevel);
                        validInput = true;
                    } else if (addChoice.equalsIgnoreCase("N"))
                        validInput = true;
                }
            }
            System.out.println("Student List of " + sectionName + ": ");
            for (String student : secondYearHMap.get(sectionName)) {
                System.out.println("Student #" + (i + 1) + ":" + student);
                i++;
            }
        } else if (yearLevel == 3) {
            // if the section is empty ask the user if they want to enter students into the section
            if (thirdYearHMap.get(sectionName).isEmpty()) {
                System.out.println("Student List of " + sectionName + ": ");
                System.out.println("There are currently no students in this section");
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Would you like to Add Students in this section? Y/N: ");
                    String addChoice = input.nextLine();
                    while (!(addChoice.equalsIgnoreCase("Y") || addChoice.equalsIgnoreCase("N"))) {
                        System.out.println("Invalid input please try again.");
                        System.out.print("Would you like to Add Students in this section? Y/N: ");
                        addChoice = input.nextLine();
                    }
                    if (addChoice.equalsIgnoreCase("Y")) {
                        AddStudents(sectionName, yearLevel);
                        validInput = true;
                    } else if (addChoice.equalsIgnoreCase("N"))
                        validInput = true;
                }
            }
            System.out.println("Student List of " + sectionName + ": ");
            for (String student : thirdYearHMap.get(sectionName)) {
                System.out.println("Student #" + (i + 1) + ":" + student);
                i++;
            }
        } else if (yearLevel == 4) {
            // if the section is empty ask the user if they want to enter students into the section
            if (fourthYearHMap.get(sectionName).isEmpty()) {
                System.out.println("Student List of " + sectionName + ": ");
                System.out.println("There are currently no students in this section");
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Would you like to Add Students in this section? Y/N: ");
                    String addChoice = input.nextLine();
                    while (!(addChoice.equalsIgnoreCase("Y") || addChoice.equalsIgnoreCase("N"))) {
                        System.out.println("Invalid input please try again.");
                        System.out.print("Would you like to Add Students in this section? Y/N: ");
                        addChoice = input.nextLine();
                    }
                    if (addChoice.equalsIgnoreCase("Y")) {
                        AddStudents(sectionName, yearLevel);
                        validInput = true;
                    } else if (addChoice.equalsIgnoreCase("N"))
                        validInput = true;
                }
            }
            System.out.println("Student List of " + sectionName + ": ");
            for (String student : fourthYearHMap.get(sectionName)) {
                System.out.println("Student #" + (i + 1) + ":" + student);
                i++;
            }
        }
        System.out.println();
    }

    private static void CreateSection(int yearLevel, String yearChoice) {
        int noOfSection = 0;

        while (!input.hasNextInt()) {
            System.out.print("Invalid input please enter a positive integer and try again: "); // Re-prompt
            input.next(); // Read and discard offending non-int input
        }
        noOfSection = input.nextInt();
        while (noOfSection <= 0) {
            System.out.println("Invalid input please enter a positive integer and try again"); // Re-prompt
            System.out.print("How many section you want to create for " + yearChoice + "?: ");
            noOfSection = input.nextInt();
        }
        input.nextLine();
        for (int i = 0; i < noOfSection; i++) {
            System.out.print("Input Section #" + (i + 1) + " " + "Name: ");
            String sectionInput = input.nextLine();
            // validation to check if section already exist for that year level
            if (yearLevel == 1) {
                while (firstYearHMap.containsKey(sectionInput)) {
                    System.out.println("That section already exist for first year level, Please try again");
                    System.out.print("Input Section #" + (i + 1) + " " + "Name: ");
                    sectionInput = input.nextLine();
                }
            } else if (yearLevel == 2) {
                while (secondYearHMap.containsKey(sectionInput)) {
                    System.out.println("That section already exist for second year level, Please try again");
                    System.out.print("Input Section #" + (i + 1) + " " + "Name: ");
                    sectionInput = input.nextLine();
                }
            } else if (yearLevel == 3) {
                while (thirdYearHMap.containsKey(sectionInput)) {
                    System.out.println("That section already exist for third year level, Please try again");
                    System.out.print("Input Section #" + (i + 1) + " " + "Name: ");
                    sectionInput = input.nextLine();
                }
            } else if (yearLevel == 4) {
                while (fourthYearHMap.containsKey(sectionInput)) {
                    System.out.println("That section already exist for fourth year level, Please try again");
                    System.out.print("Input Section #" + (i + 1) + " " + "Name: ");
                    sectionInput = input.nextLine();
                }
            }
            // add the section name to the list
            AddNewSection(sectionInput, yearLevel);
        }
    }

    private static void DisplaySection(int yearLevel, String yearChoice) {
        System.out.println();
        System.out.println("The following are all the section/s for " + yearChoice);
        if (yearLevel == 1) {
            // we get the all the sections of first year and sort it
            SortedSet<String> sections = new TreeSet<>(firstYearHMap.keySet());
            // loop to print all sections
            for (String section : sections) {
                System.out.println(section);
            }
        } else if (yearLevel == 2) {
            // we get the all the sections of section year and sort it
            SortedSet<String> sections = new TreeSet<>(secondYearHMap.keySet());
            // loop to print all sections
            for (String section : sections) {
                System.out.println(section);
            }
        } else if (yearLevel == 3) {
            // we get the all the sections of third year and sort it
            SortedSet<String> sections = new TreeSet<>(thirdYearHMap.keySet());
            // loop to print all sections
            for (String section : sections) {
                System.out.println(section);
            }
        } else if (yearLevel == 4) {
            // we get the all the sections of fourth year and sort it
            SortedSet<String> sections = new TreeSet<>(fourthYearHMap.keySet());
            // loop to print all sections
            for (String section : sections) {
                System.out.println(section);
            }
        }
    }

    private static void ReturnMenu() {
        boolean validMenu = false;
        while (!validMenu) {
            System.out.print("Return to the (Section) Menu | (Year) level Menu | (Course) Menu | (Exit): ");
            String menu = input.nextLine();

            if (menu.equalsIgnoreCase("Section") || menu.equalsIgnoreCase("Section Menu")) {
                // calls the Section menu
                SectionMenu(year);
                validMenu = true;
            } else if (menu.equalsIgnoreCase("Year") || menu.equalsIgnoreCase(" Year Level Menu")) {
                // calls the Year Menu
                year = YearLevelMenu(course);
                SectionMenu(year);
                validMenu = true;

            } else if (menu.equalsIgnoreCase("Course") || menu.equalsIgnoreCase("Course Menu")) {
                // calls the Course menu
                course = CourseMenu();
                year = YearLevelMenu(course);
                SectionMenu(year);
                validMenu = true;
            } else if (menu.equalsIgnoreCase("Exit")) {
                System.exit(0);
            } else
                System.out.println("Wrong input please try again");
        }
    }

    public static void main(String[] args) {
        course = CourseMenu();
        year = YearLevelMenu(course);
        SectionMenu(year);
        while (true) {
            ReturnMenu();
        }
    }
}
