package lab2;

import lab2.student.Student;

import java.util.*;
import java.util.function.Predicate;

public class Lab2 {
    public static void main(String[] args) {
        new Lab2().run();
    }

    void run() {
        List<Student> students = generateStudents();

        showStudentsByFaculty(students);
        showStudentsByYear(students);
        showStudentsByGroup(students);
    }

    String[] firstNames = {"Sacha", "Ferris", "Lance", "Reese", "Kasper", "Scott", "Logan", "Piper", "Camilla", "Oliver"};
    String[] lastNames = {"Sacha", "Ferris", "Lance", "Reese", "Kasper", "Scott", "Logan", "Piper", "Camilla", "Oliver"};
    String[] patronymics = {"Sacha", "Ferris", "Lance", "Reese", "Kasper", "Scott", "Logan", "Piper", "Camilla", "Oliver"};
    Date[] birthdays = {new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date()};
    String[] address = {"addrs1", "addrs2", "addrs3", "addrs4", "addrs5", "addrs6", "addrs7", "addrs8", "addrs9", "addrs10"};
    String[] phone = {"phone1", "phone2", "phone3", "phone4", "phone5", "phone6", "phone7", "phone8", "phone9", "phone10"};
    String[] faculties = {"faculty1", "faculty1", "faculty1", "faculty1", "faculty1", "faculty2", "faculty2", "faculty2", "faculty3", "faculty3"};
    int[] courses = {1, 2, 3, 4, 1, 2, 3, 4, 1, 2};
    int[] group = {101, 201, 301, 401, 101, 201, 301, 401, 101, 201};

    List<Student> generateStudents() {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, firstNames[i], lastNames[i], patronymics[i], birthdays[i],
                    address[i], phone[i], faculties[i], courses[i], group[i]));
        }

        return students;
    }

    void showStudentsByFaculty(List<Student> students) {
        System.out.println("Enter faculty name:");
        String faculty = new Scanner(System.in).nextLine();

        Student[] filteredStudents = filterStudents(students, s -> s.getFaculty().equals(faculty));

        displayStudents(filteredStudents);
    }

    void showStudentsByYear(List<Student> students) {
        System.out.println("Enter year:");
        int year = new Scanner(System.in).nextInt();

        GregorianCalendar startDate = new GregorianCalendar();
        startDate.set(year, Calendar.JANUARY, 1);

        Student[] filteredStudents = filterStudents(students, s -> startDate.getTime().before(s.getBirthday()));

        displayStudents(filteredStudents);
    }

    void showStudentsByGroup(List<Student> students) {
        System.out.println("Enter group:");
        int group = new Scanner(System.in).nextInt();

        Student[] filteredStudents = filterStudents(students, s -> s.getGroup() == group);

        displayStudents(filteredStudents);
    }

    Student[] filterStudents(List<Student> students, Predicate<Student> compare) {
        return students.stream().filter(compare).toArray(Student[]::new);
    }

    void displayStudents(Student[] students) {
        if (students.length == 0) {
            System.out.println("No students!");
            return;
        }

        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
