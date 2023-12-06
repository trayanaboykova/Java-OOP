package Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.commands;

import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.annotations.Inject;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.CommandInterpreter;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Executable;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Repository;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMAND_PATH = "barracksWars.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {

        String className = parseCommandToClassName(commandName);

        Executable command = null;

        try {
            Class clazz = Class.forName(COMMAND_PATH + className);
            Constructor<Command> constructor = clazz.getDeclaredConstructor(String[].class/*, Repository.class, UnitFactory.class*/);
            constructor.setAccessible(true);
            command = constructor.newInstance(new Object[]{data}/*, repository, unitFactory*/);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    if (field.getType().equals(Repository.class)) {
                        field.setAccessible(true);
                        field.set(command, repository);
                    } else if (field.getType().equals(UnitFactory.class)) {
                        field.setAccessible(true);
                        field.set(command, unitFactory);
                    }
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        return command;

        /*switch (commandName) {
            case "add":
                return new AddCommand(data, repository, unitFactory);
            case "report":
                return new ReportCommand(data, repository, unitFactory);
            case "fight":
                return new FightCommand(data, repository, unitFactory);
            case "retire":
                return new RetireCommand(data, repository, unitFactory);
            default:
                throw new RuntimeException("Invalid command!");
        }*/
    }

    private String parseCommandToClassName(String commandName) {
        return commandName.substring(0, 1).toUpperCase().concat(commandName.substring(1)).concat("Command");
    }
}
