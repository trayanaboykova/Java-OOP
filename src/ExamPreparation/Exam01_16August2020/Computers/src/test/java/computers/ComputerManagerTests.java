package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private Computer computerOne;
    private Computer computerTwo;

    @Before
    public void setUp(){
        computerManager = new ComputerManager();
        computerOne = new Computer("Apple", "MacBook Pro", 4500);
        computerTwo = new Computer("Apple", "MacBook Air", 3000);
        computerManager.addComputer(computerOne);
        computerManager.addComputer(computerTwo);
    }

    @Test
    public void testCountShouldWork() {
        int expectedCount = 2;
        assertEquals(expectedCount, computerManager.getCount());
    }

    @Test
    public void testGetComputersAsAListShouldWork(){
        List<Computer> expected = List.of(computerOne, computerTwo);
        assertEquals(expected, computerManager.getComputers());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComputerThatAlreadyExist() {
        computerManager.addComputer(computerOne);
    }

    @Test
    public void testRemoveComputerShouldWork() {
        assertEquals(computerOne, computerManager.removeComputer("Apple", "MacBook Pro"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveComputerShouldNotWork() {
        assertEquals(computerOne, computerManager.removeComputer("Test", "Test"));
    }

    @Test
    public void testGetComputersByManufacturerShouldWork(){
        List<Computer> expectedList = List.of(computerOne, computerTwo);
        assertEquals(expectedList, computerManager.getComputersByManufacturer("Apple"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateNullValueShouldWork() {
        List<Computer> expectedList = List.of(computerOne, computerTwo);
        assertEquals(expectedList, computerManager.getComputersByManufacturer(null));
    }
}