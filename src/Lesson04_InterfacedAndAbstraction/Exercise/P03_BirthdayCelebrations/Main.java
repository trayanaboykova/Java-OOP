package Lesson04_InterfacedAndAbstraction.Exercise.P03_BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Birthable> birthableList = new ArrayList<>();

        String input;
        while (!(input = scanner.nextLine()).equals("End")) {

            String[] info = input.split("\\s+");
            String type = info[0];
            String name = info[1];

            Birthable birthable;

            switch (type) {
                case "Citizen":
                    int age = Integer.parseInt(info[2]);
                    String citizenId = info[3];
                    String citizenBirthDate = info[4];
                    birthable = new Citizen(name, age, citizenId, citizenBirthDate);
                    birthableList.add(birthable);
                    break;
                case "Pet":
                    String petBirthDate = info[2];
                    birthable = new Pet(name, petBirthDate);
                    birthableList.add(birthable);
                    break;
            }
        }

        String searchedDate = scanner.nextLine();

        System.out.println(birthableList.stream()
                .map(Birthable::getBirthDate)
                .filter(p -> p.endsWith(searchedDate))
                .collect(Collectors.toList()).size() == 0
                ? "<no output>"
                : String.format("%s", birthableList.stream()
                .map(Birthable::getBirthDate)
                .filter(e -> e.endsWith(searchedDate))
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()))));

    }
}
