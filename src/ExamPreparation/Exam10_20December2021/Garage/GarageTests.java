package garage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class GarageTests {
    private Garage garage;

    @Before
    public void setUp() {
        garage = new Garage();
    }

    @Test
    public void testGarageInitialization() {
        // Check if the Garage constructor initializes the 'cars' list properly
        List<Car> cars = garage.getCars();
        assertNotNull(cars);
        assertEquals(0, cars.size());
    }

    @Test
    public void testAddCarToGarage() {
        // Test adding a car to the garage
        Car car = new Car("Toyota", 200,  30000);
        garage.addCar(car);
        List<Car> cars = garage.getCars();
        assertEquals(1, cars.size());
        assertTrue(cars.contains(car));
    }




    @Test
    public void testGetTheMostExpensiveCar() {
        // Test getting the most expensive car in the garage
        Car car1 = new Car("Toyota", 200, 30000);
        Car car2 = new Car("Honda", 150, 25000);
        Car car3 = new Car("Ford", 170, 28000);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        assertNotNull(mostExpensiveCar);
        assertEquals(car1, mostExpensiveCar);
    }

    @Test
    public void testFindAllCarsByBrand() {
        // Test finding cars by brand
        Car car1 = new Car("Toyota", 200, 30000);
        Car car2 = new Car("Toyota", 150, 28000);
        Car car3 = new Car("Honda", 170, 25000);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        List<Car> toyotaCars = garage.findAllCarsByBrand("Toyota");
        assertEquals(2, toyotaCars.size());
        assertTrue(toyotaCars.contains(car1));
        assertTrue(toyotaCars.contains(car2));
    }
}
