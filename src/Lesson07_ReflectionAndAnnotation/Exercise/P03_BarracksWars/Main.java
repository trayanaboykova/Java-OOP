package Lesson06_ReflectionAndAnnotation.Exercise.P03_BarracksWars;

public class Main {

    public static void main(String[] args) {

        Repository repository = new UnitRepository();

        UnitFactory unitFactory = new UnitFactoryImpl();

        CommandInterpreter commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);

        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
