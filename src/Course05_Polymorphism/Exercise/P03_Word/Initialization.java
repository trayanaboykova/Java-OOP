package Course05_Polymorphism.Exercise.P03_Word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {

        CommandInterface commandInterface = new CommandImpl(text);
        commandInterface.init();

        return commandInterface;
    }

}
