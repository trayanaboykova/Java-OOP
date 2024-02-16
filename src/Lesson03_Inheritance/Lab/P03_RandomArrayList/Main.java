package Lesson03_Inheritance.Lab.P03_RandomArrayList;

public class Main {
    public static void main(String[] args) {

        RandomArrayList<Integer> list = new RandomArrayList<>();

        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println(list.getRandomElement());
    }
}
