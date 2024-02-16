package Lesson04_InterfacedAndAbstraction.Exercise.P07_CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();

        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();

        MyListImpl myList = new MyListImpl();

        String[] words = scanner.nextLine().split("\\s+");
        int numOfRemoveOperations = Integer.parseInt(scanner.nextLine());

        addOperation(words, addCollection);
        addOperation(words, addRemoveCollection);
        addOperation(words, myList);

        removeOperation(numOfRemoveOperations, addRemoveCollection);
        removeOperation(numOfRemoveOperations, myList);

    }

    private static void removeOperation(int numOfRemoveOperations, AddRemovable addRemovable) {

        while (numOfRemoveOperations-- > 0) {
            System.out.print(addRemovable.remove() + " ");
        }
        System.out.println();
    }

    private static void addOperation(String[] words, Addable addable) {

        for (String word : words) {
            System.out.print(addable.add(word) + " ");
        }
        System.out.println();
    }
}
