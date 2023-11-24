package Course04_InterfacedAndAbstraction.Lab.P04_SayHelloExtended;

public class Chinese extends BasePerson {
    public Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}