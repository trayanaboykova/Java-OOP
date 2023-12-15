package aquarium.entities.decorations;

public abstract class BaseDecoration implements Decoration {
    private int comfort;
    private double price;

    protected BaseDecoration(int comfort, double price) {
        setComfort(comfort);
        setPrice(price);
    }

    @Override
    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
