package Course04_InterfacedAndAbstraction.Lab.P04_SayHelloExtended;

public class Bulgarian extends BasePerson{
    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}