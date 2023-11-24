package Course04_InterfacedAndAbstraction.Exercise.P07_CollectionHierarchy;

public class AddCollection extends Collection implements Addable {
    @Override
    public int add(String item) {

        super.items.add(item);

        return super.items.indexOf(item);
    }
}