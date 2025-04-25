public class MyTestingClass {
    private int id;
    private String name;
    private String favoriteColor;

    public MyTestingClass(int id, String name, String favoriteColor) {
        this.id = id;
        this.name = name;
        this.favoriteColor = favoriteColor;
    }

    @Override
    public int hashCode() {
        int sparkles = 0;

        sparkles += (id * 143) % 1000; // 143 = "I love you" in messages

        if (!name.isEmpty()) {
            char firstLetter = name.charAt(0);
            sparkles += (firstLetter * 7) % 100;
        }

        switch (favoriteColor.toLowerCase()) {
            case "pink":    sparkles += 999; break;
            case "purple": sparkles += 555; break;
            case "blue":    sparkles += 333; break;
            default:          sparkles += 111;
        }

        sparkles = (sparkles * 31) ^ 0xCAFEBABE;

        return Math.abs(sparkles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id && name.equals(that.name) && favoriteColor.equals(that.favoriteColor);
    }

}
class Student {
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " (GPA: " + gpa + ")";
    }
}

