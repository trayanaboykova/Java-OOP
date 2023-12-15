package aquarium;


import static org.junit.Assert.*;

import org.junit.Test;


public class AquariumTests {
    @Test
    public void testValidInitialization() {
        Aquarium aquarium = new Aquarium("MyAquarium", 10);
        assertNotNull(aquarium);
        assertEquals("MyAquarium", aquarium.getName());
        assertEquals(10, aquarium.getCapacity());
        assertEquals(0, aquarium.getCount());
    }

    @Test
    public void testInvalidInitializationWithNullName() {
        try {
            new Aquarium(null, 10);
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testInvalidInitializationWithEmptyName() {
        try {
            new Aquarium("", 10);
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testInvalidInitializationWithNegativeCapacity() {
        try {
            new Aquarium("MyAquarium", -5);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testAddFish() {
        Aquarium aquarium = new Aquarium("MyAquarium", 2);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");

        aquarium.add(fish1);
        assertEquals(1, aquarium.getCount());

        aquarium.add(fish2);
        assertEquals(2, aquarium.getCount());
    }

    @Test
    public void testAddFishToFullAquarium() {
        Aquarium aquarium = new Aquarium("MyAquarium", 1);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");

        aquarium.add(fish1);
        assertEquals(1, aquarium.getCount());

        try {
            aquarium.add(fish2);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testRemoveFish() {
        Aquarium aquarium = new Aquarium("MyAquarium", 2);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");

        aquarium.add(fish1);
        aquarium.add(fish2);
        assertEquals(2, aquarium.getCount());

        aquarium.remove("Fish1");
        assertEquals(1, aquarium.getCount());
    }

    @Test
    public void testRemoveNonExistingFish() {
        Aquarium aquarium = new Aquarium("MyAquarium", 2);
        Fish fish1 = new Fish("Fish1");
        aquarium.add(fish1);
        assertEquals(1, aquarium.getCount());

        try {
            aquarium.remove("Fish2");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testSellFishSetAvailable() {
        // Create an Aquarium
        Aquarium aquarium = new Aquarium("MyAquarium", 2);

        // Create a Fish object and add it to the Aquarium
        Fish fish = new Fish("Fish1");
        aquarium.add(fish);

        // Call the sellFish method, which sets the fish as unavailable
        Fish soldFish = aquarium.sellFish("Fish1");

        // Verify that the fish is marked as unavailable
        assertFalse(soldFish.isAvailable());
    }

    @Test
    public void testSellNonExistingFish() {
        Aquarium aquarium = new Aquarium("MyAquarium", 2);
        Fish fish1 = new Fish("Fish1");

        aquarium.add(fish1);
        assertEquals(1, aquarium.getCount());

        try {
            aquarium.sellFish("Fish2");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception, do nothing
        }
    }

    @Test
    public void testReport() {
        Aquarium aquarium = new Aquarium("MyAquarium", 3);
        Fish fish1 = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");

        aquarium.add(fish1);
        aquarium.add(fish2);

        assertEquals("Fish available at MyAquarium: Fish1, Fish2", aquarium.report());
    }
}

