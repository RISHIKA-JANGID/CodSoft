import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> registeredStudents = new ArrayList<>();

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    boolean isSlotAvailable() {
        return registeredStudents.size() < capacity;
    }

    void registerStudent(Student student) {
        if (isSlotAvailable() && !registeredStudents.contains(student)) {
            registeredStudents.add(student);
        }
    }

    void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    int availableSlots() {
        return capacity - registeredStudents.size();
    }

    void displayCourseInfo() {
        System.out.println(code + ": " + title + " (" + availableSlots() + " slots available)");
        System.out.println("Schedule: " + schedule);
        System.out.println("Description: " + description);
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.registerStudent(this);
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
        }
    }

    void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println("- " + course.code + ": " + course.title);
            }
        }
    }
}

public class Main {

    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of CS", 3, "Mon 10-12"));
        courses.add(new Course("MA101", "Calculus I", "Differential Calculus", 2, "Tue 9-11"));
        courses.add(new Course("PH101", "Physics I", "Mechanics", 2, "Wed 8-10"));

        System.out.println("Welcome to the Course Registration System!");

        while (true) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register Student for a Course");
            System.out.println("3. Drop Student from a Course");
            System.out.println("4. View Student's Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            course.displayCourseInfo();
            System.out.println();
        }
    }

    static Student findOrCreateStudent(String id, String name) {
        for (Student s : students) {
            if (s.id.equals(id)) {
                return s;
            }
        }
        Student newStudent = new Student(id, name);
        students.add(newStudent);
        return newStudent;
    }

    static Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = findOrCreateStudent(id, name);

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course != null) {
            if (course.isSlotAvailable()) {
                student.registerCourse(course);
                System.out.println("Registered successfully!");
            } else {
                System.out.println("No available slots in this course.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);

        if (student != null) {
            System.out.print("Enter Course Code to drop: ");
            String courseCode = scanner.nextLine();
            Course course = findCourseByCode(courseCode);

            if (course != null && student.registeredCourses.contains(course)) {
                student.dropCourse(course);
                System.out.println("Dropped successfully!");
            } else {
                System.out.println("You are not registered in this course.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    static void viewRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = findStudentById(id);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.id.equals(id)) {
                return s;
            }
        }
        return null;
    }
}
