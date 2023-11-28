package Course05_Polymorphism.Exercise.P04_Calculator;

public interface Operation {
    void addOperand(int operand);
    int getResult();
    boolean isCompleted();
}
