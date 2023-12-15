package blueOrigin;

import blueOrigin.Astronaut;
import blueOrigin.Spaceship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut astronaut;

    @Before
    public void setUp() {
        spaceship = new Spaceship("Apollo", 2);
        astronaut = new Astronaut("John", 90.0);
    }

    @Test
    public void testGetCountShouldReturnZeroWhenSpaceshipIsCreated() {
        assertEquals(0, spaceship.getCount());
    }

    @Test
    public void testGetNameShouldReturnSpaceshipName() {
        assertEquals("Apollo", spaceship.getName());
    }

    @Test
    public void testGetCapacityShouldReturnSpaceshipCapacity() {
        assertEquals(2, spaceship.getCapacity());
    }

    @Test
    public void testAddAstronautShouldIncreaseCount() {
        spaceship.add(astronaut);
        assertEquals(1, spaceship.getCount());
    }


    @Test
    public void testAddDuplicateAstronautShouldThrowException() {
        spaceship.add(astronaut);

        try {
            spaceship.add(astronaut);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testRemoveAstronautShouldDecreaseCount() {
        spaceship.add(astronaut);
        spaceship.remove("John");

        assertEquals(0, spaceship.getCount());
    }

    @Test
    public void testRemoveNonExistentAstronautShouldReturnFalse() {
        spaceship.add(astronaut);
        boolean removed = spaceship.remove("Jane");

        assertFalse(removed);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeCapacityShouldThrowException() {
        new Spaceship("TestSpaceship", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullNameShouldThrowException() {
        new Spaceship(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithEmptyNameShouldThrowException() {
        new Spaceship("", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithBlankNameShouldThrowException() {
        new Spaceship("  ", 100);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautToFullSpaceshipShouldThrowException() {
        Spaceship spaceship = new Spaceship("Test", 2);
        spaceship.add(new Astronaut("John", 90.0));
        spaceship.add(new Astronaut("Jane", 85.0));

        spaceship.add(new Astronaut("Jim", 88.0)); // This should throw IllegalArgumentException
    }
}
