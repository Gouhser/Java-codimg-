import java.util.ArrayList;

public class StringCollectionDemo {
    public static void main(String[] args) {

        // String Handling
        String name = "Java Programming";

        System.out.println("Original String: " + name);
        System.out.println("Length: " + name.length());
        System.out.println("Upper Case: " + name.toUpperCase());
        System.out.println("Lower Case: " + name.toLowerCase());

        // Java Collection
        ArrayList<String> subjects = new ArrayList<>();

        subjects.add("Java");
        subjects.add("Python");
        subjects.add("C++");

        System.out.println("\nList of Subjects:");
        for (String sub : subjects) {
            System.out.println(sub);
        }
    }
}