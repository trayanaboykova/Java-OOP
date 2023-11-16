package Course02_Encapsulation.Exercise.P01_ClassBoxDataValidation;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        Box box;

        try {
            box = new Box(length, width, height);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf("Surface Area - %.2f\n", box.calculateSurfaceArea());
        System.out.printf("Lateral Surface Area - %.2f\n", box.calculateLateralSurfaceArea());
        System.out.printf("Volume - %.2f\n", box.calculateVolume());
    }
}
}
