package cats;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTests {
    private House house;
    private Cat cat1;
    private Cat cat2;

    @Before
    public void setUp() {
        house = new House("MyHouse", 2);
        cat1 = new Cat("Cat1");
        cat2 = new Cat("Cat2");
    }

    @Test
    public void testGetName() {
        assertEquals("MyHouse", house.getName());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(2, house.getCapacity());
    }

    @Test
    public void testAddCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        assertEquals(2, house.getCount());
    }

    @Test
    public void testAddCatHouseFull() {
        house.addCat(cat1);
        house.addCat(cat2);

        try {
            house.addCat(new Cat("Cat3"));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("House is full!", e.getMessage());
            assertEquals(2, house.getCount()); // Ensure that the count hasn't changed
        }
    }

    @Test
    public void testRemoveCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        house.removeCat("Cat1");

        assertEquals(1, house.getCount());
    }

    @Test
    public void testRemoveCatNotFound() {
        house.addCat(cat1);

        try {
            house.removeCat("Cat2");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Cat with name Cat2 doesn't exist!", e.getMessage());
            assertEquals(1, house.getCount()); // Ensure that the count hasn't changed
        }
    }

    @Test
    public void testCatForSale() {
        house.addCat(cat1);

        Cat soldCat = house.catForSale("Cat1");

        assertFalse(soldCat.isHungry());
    }

    @Test
    public void testCatForSaleNotFound() {
        try {
            house.catForSale("Cat1");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Cat with name Cat1 doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void testStatistics() {
        house.addCat(cat1);
        house.addCat(cat2);

        String expected = "The cat Cat1, Cat2 is in the house MyHouse!";
        assertEquals(expected, house.statistics());
    }

    @Test
    public void testStatisticsNoCats() {
        String expected = "The cat  is in the house MyHouse!";
        assertEquals(expected, house.statistics());
    }
    @Test
    public void testHouseConstructorInvalidName() {
        try {
            House houseWithInvalidName = new House(null, 2);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Invalid name!", e.getMessage());
        }

        try {
            House houseWithEmptyName = new House("", 2);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Invalid name!", e.getMessage());
        }
    }
    @Test
    public void testHouseConstructorInvalidCapacity() {
        try {
            House houseWithNegativeCapacity = new House("MyHouse", -1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid capacity!", e.getMessage());
        }
    }

}
