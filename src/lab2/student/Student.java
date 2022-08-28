package lab2.student;

import java.util.Date;

public class Student {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final Date birthday;
    private final String address;
    private final String phone;
    private final String faculty;
    private final int course;
    private final int group;

    public Student(
            int id,
            String firstName,
            String lastName,
            String patronymic,
            Date birthday,
            String address,
            String phone,
            String faculty,
            int course,
            int group
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.course = course;
        this.group = group;
        this.faculty = faculty;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course=" + course +
                ", group=" + group +
                '}';
    }
}
