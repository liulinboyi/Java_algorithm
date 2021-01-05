import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
//        if (this.score < o.score) return -1;
//        else if (this.score > o.score) return 1;
//        else return 0;
        return this.score - o.score;
    }

    @Override
    public boolean equals(Object o) {
        // 如果是它本身
        if (this == o) return true;
        // 如果是null
        if (o == null) return false;
        // 如果不是同一个class
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.name.equals(student.name);
        // 不区分大小写比较
        // return this.name.toLowerCase().equals(student.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
