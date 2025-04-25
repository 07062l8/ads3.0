import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(101);
        Random random = new Random();
        String[] colors = {"pink", "purple", "blue", "white", "black", "yellow", "green"};

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Name" + random.nextInt(1000);
            String color = colors[random.nextInt(colors.length)];
            double gpa = 1.0 + random.nextDouble() * 3.0;

            MyTestingClass key = new MyTestingClass(id, name, color);
            Student value = new Student("Student" + i, gpa);
            table.put(key, value);
        }

        table.printNumElements();
    }
}