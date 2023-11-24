package Course04_InterfacedAndAbstraction.Lab.P06_Ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String driverName = scanner.nextLine();
        System.out.println(new Ferrari(driverName));

    }
}
