package Lesson03_Inheritance.Exercise.P06_Animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        String input;

        while (!(input = scanner.nextLine()).equals("Beast!")) {

            String[] animalInfo = scanner.nextLine().split("\\s+");
            String animalName = animalInfo[0];
            int age = Integer.parseInt(animalInfo[1]);
            String gender = animalInfo[2];

            switch (input) {
                case "Cat":
                    try {
                        Cat cat = new Cat(animalName, age, gender);
                        animals.add(cat);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(e);
                    }
                    break;
                case "Tomcat":
                    try {
                        Tomcat tomcat = new Tomcat(animalName, age);
                        animals.add(tomcat);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(e);
                    }
                    break;
                case "Kitten":
                    try {
                        Kitten kitten = new Kitten(animalName, age);
                        animals.add(kitten);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(e);
                    }
                    break;
                case "Dog":
                    try {
                        Dog dog = new Dog(animalName, age, gender);
                        animals.add(dog);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(e);
                    }
                    break;
                case "Frog":
                    try {
                        Frog frog = new Frog(animalName, age, gender);
                        animals.add(frog);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(e);
                    }
                    break;
            }
        }

        animals.forEach(System.out::println);
    }

    private static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
