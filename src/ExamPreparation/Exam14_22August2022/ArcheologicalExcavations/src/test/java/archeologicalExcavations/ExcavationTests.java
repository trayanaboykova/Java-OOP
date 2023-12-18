package archeologicalExcavations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ExcavationTests {
    private Excavation excavation;
    private Archaeologist archaeologist;

    @Before
    public void setUp() {
        excavation = new Excavation("TestExcavation", 10);
        archaeologist = new Archaeologist("Archaeologist1", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWithNull() {
        new Excavation(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityWithNegative() {
        new Excavation("TestExcavation", -5);
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(0, excavation.getCount());
        excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1, excavation.getCount());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("TestExcavation", excavation.getName());
    }

    @Test
    public void testGetCapacity() {
        Assert.assertEquals(10, excavation.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistMoreThanCapacity() {
        final int archaeologistsNumberOverCapacity = excavation.getCapacity() + 1;
        for (int archaeologistIndex = 0; archaeologistIndex < archaeologistsNumberOverCapacity; archaeologistIndex++) {
            excavation.addArchaeologist(new Archaeologist("Archaeologist" + archaeologistIndex, 100));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistAlreadyExist() {
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void testRemoveArchaeologist() {
        Assert.assertFalse(excavation.removeArchaeologist("Archaeologist1"));
        excavation.addArchaeologist(archaeologist);
        Assert.assertTrue(excavation.removeArchaeologist("Archaeologist1"));
    }
}
