package Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces;

public interface CommandInterpreter {
    Executable interpretCommand(String[] data, String commandName);
}
