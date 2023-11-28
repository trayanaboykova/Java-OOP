package Course05_Polymorphism.Exercise.P04_Calculator;

import java.util.Deque;

public class MrOperation implements Operation {

    private Deque<Integer> memory;

    public MrOperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public int getResult() {
        return memory.pop();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}

