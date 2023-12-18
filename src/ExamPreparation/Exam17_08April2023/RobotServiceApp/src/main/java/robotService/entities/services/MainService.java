package robotService.entities.services;

public class MainService extends BaseService {

    private static final int capacity = 30;

    public MainService(String name) {
        super(name, capacity);
    }
}