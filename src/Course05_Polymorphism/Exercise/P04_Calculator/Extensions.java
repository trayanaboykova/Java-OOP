package Course05_Polymorphism.Exercise.P04_Calculator;

public class Extensions {
    public static InputInterpreter buildInterpreter(CalculationEngine engine) {
        return new InputInterpreter(engine);
    }
}
