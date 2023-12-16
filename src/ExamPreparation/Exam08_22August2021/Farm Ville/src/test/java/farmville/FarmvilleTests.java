package farmville;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private Farm farm;

    @Before
    public void setUp() {
        farm = new Farm("MyFarm", 3);
    }

    @Test
    public void testGetCount() {
        assertEquals(0, farm.getCount());
    }

    @Test
    public void testGetName() {
        assertEquals("MyFarm", farm.getName());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(3, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalToFullFarm() {
        Farm fullFarm = new Farm("FullFarm", 2);
        fullFarm.add(new Animal("Dog", 100));
        fullFarm.add(new Animal("Cat", 100));
        fullFarm.add(new Animal("Horse", 100)); // This should throw an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistingAnimal() {
        farm.add(new Animal("Dog", 100));
        farm.add(new Animal("Cat", 100));
        farm.add(new Animal("Dog", 100)); // Adding an existing animal should throw an exception
    }

    @Test
    public void testAddAnimal() {
        farm.add(new Animal("Dog", 100));
        farm.add(new Animal("Cat", 100));
        assertEquals(2, farm.getCount());
    }

    @Test
    public void testRemoveAnimal() {
        farm.add(new Animal("Dog", 100));
        farm.add(new Animal("Cat", 100));
        assertTrue(farm.remove("Dog"));
        assertFalse(farm.remove("Dog")); // Removing the same animal again should return false
    }

    @Test
    public void testRemoveNonExistingAnimal() {
        assertFalse(farm.remove("Dog")); // Removing a non-existing animal should return false
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCapacity() {
        new Farm("NegativeFarm", -1); // Creating a farm with negative capacity should throw an exception
    }

    @Test(expected = NullPointerException.class)
    public void testNullFarmName() {
        new Farm(null, 3); // Creating a farm with a null name should throw an exception
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyFarmName() {
        new Farm("", 3); // Creating a farm with an empty name should throw an exception
    }
}
