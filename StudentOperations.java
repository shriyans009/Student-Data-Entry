import java.util.*;

class StudentOperations {
    private ArrayList<Student> students;

    public StudentOperations() {
        this.students = new ArrayList<>();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student Added Successfully!");
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            student.display();
        }
    }

    // Search Student by PRN
    public void searchByPRN(int prn) {
        try {
            for (Student student : students) {
                if (student.getPRN() == prn) {
                    System.out.println("Student Found:");
                    student.display();
                    return;
                }
            }
            throw new StudentNotFoundException("Student with PRN " + prn + " not found.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Search Student by Name
    public void searchByName(String name) {
        try {
            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(name)) {
                    System.out.println("Student Found:");
                    student.display();
                    return;
                }
            }
            throw new StudentNotFoundException("Student with name '" + name + "' not found.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Search Student by Position (Index)
    public void searchByPosition(int position) {
        try {
            if (position < 0 || position >= students.size()) {
                throw new InvalidInputException("Invalid position: " + position);
            }
            System.out.println("Student at Position " + position + ":");
            students.get(position).display();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update Student Details
    public void updateStudent(int prn) {
        Scanner scan = new Scanner(System.in);
        boolean found = false;

        for (Student student : students) {
            if (student.getPRN() == prn) {
                found = true;
                System.out.println("Enter new Name (leave blank to keep current): ");
                String name = scan.nextLine();
                if (!name.isEmpty()) student.setName(name);

                System.out.println("Enter new Dept (leave blank to keep current): ");
                String dept = scan.nextLine();
                if (!dept.isEmpty()) student.setDept(dept);

                System.out.println("Enter new Batch (leave blank to keep current): ");
                String batch = scan.nextLine();
                if (!batch.isEmpty()) student.setBatch(batch);

                System.out.println("Enter new CGPA (or -1 to keep current): ");
                try {
                    float cgpa = Float.parseFloat(scan.nextLine());
                    if (cgpa != -1) student.setCGPA(cgpa);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid CGPA input.");
                }

                System.out.println("Student details updated successfully.");
                return;
            }
        }

        if (!found) {
            try {
                throw new StudentNotFoundException("Student with PRN " + prn + " not found.");
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Delete Student
    public void deleteStudent(int prn) {
        Iterator<Student> iterator = students.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getPRN() == prn) {
                iterator.remove();
                found = true;
                System.out.println("Student with PRN " + prn + " deleted successfully.");
                return;
            }
        }

        if (!found) {
            try {
                throw new StudentNotFoundException("Student with PRN " + prn + " not found.");
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
