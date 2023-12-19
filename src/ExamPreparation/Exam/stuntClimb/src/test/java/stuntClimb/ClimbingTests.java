package stuntClimb;


import org.junit.Assert;
import org.junit.Test;

public class ClimbingTests {
    @Test
    public void testSetCapacityAndName() {
        String name = "Mount Everest";
        int capacity = 5;
        Climbing climbing = new Climbing(name, capacity);

        Assert.assertEquals(capacity, climbing.getCapacity());
        Assert.assertEquals(name, climbing.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClimberToFullClimbing() {
        Climbing climbing = new Climbing("Mount Everest", 1);
        climbing.addRockClimber(new RockClimber("John Doe", 10.5));
        climbing.addRockClimber(new RockClimber("Jane Doe", 9.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistingClimber() {
        Climbing climbing = new Climbing("Mount Everest", 2);
        RockClimber climber = new RockClimber("John Doe", 10.5);
        climbing.addRockClimber(climber);
        climbing.addRockClimber(climber);
    }

    @Test
    public void testRemoveExistingClimber() {
        Climbing climbing = new Climbing("Mount Everest", 2);
        RockClimber climber = new RockClimber("John Doe", 10.5);
        climbing.addRockClimber(climber);

        Assert.assertTrue(climbing.removeRockClimber(climber.getName()));
    }

    @Test
    public void testRemoveNonExistingClimber() {
        Climbing climbing = new Climbing("Mount Everest", 2);

        Assert.assertFalse(climbing.removeRockClimber("John Doe"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidCapacity() {
        new Climbing("Mount Everest", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetInvalidName() {
        new Climbing(null, 1);
    }

    @Test
    public void testGetCount() {
        Climbing climbing = new Climbing("Mount Everest", 2);
        RockClimber climber1 = new RockClimber("John Doe", 10.5);
        RockClimber climber2 = new RockClimber("Jane Doe", 9.5);

        climbing.addRockClimber(climber1);
        climbing.addRockClimber(climber2);

        Assert.assertEquals(2, climbing.getCount());
    }

}
