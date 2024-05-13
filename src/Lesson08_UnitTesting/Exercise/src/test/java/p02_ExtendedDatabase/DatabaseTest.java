package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;
    private Person taylor = new Person(1, "Taylor");
    private Person betty = new Person(2, "Betty");
    private Person cassandra = new Person(3, "Cassandra");
    private Person nullPerson;

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(taylor, betty);
        nullPerson = null;
    }

    @Test
    public void testConstructorShouldCreateValidDB() {
        Person[] dbElements = database.getElements();
        assertArrayEquals(dbElements, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithMoreThan16Elements() throws OperationNotSupportedException {
        Person[] elements = new Person[17];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithNoElements() throws OperationNotSupportedException {
        Person[] elements = new Person[0];
        database = new Database(elements);
    }

    @Test
    public void testAddShouldWork() throws OperationNotSupportedException {
        Person[] dbElementsBeforeAdd = database.getElements();
        database.add(cassandra);
        Person[] dbElementsAfterAdd = database.getElements();
        assertEquals(dbElementsBeforeAdd.length + 1, dbElementsAfterAdd.length);
        assertEquals(cassandra, dbElementsAfterAdd[dbElementsAfterAdd.length - 1]);
        assertEquals(dbElementsAfterAdd.length, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenPersonIsNull() throws OperationNotSupportedException {
        database.add(nullPerson);
    }

    @Test
    public void testRemoveShouldWork() throws OperationNotSupportedException {
        Person[] elementsBeforeRemove = database.getElements();
        database.remove();
        Person[] elementsAfterRemove = database.getElements();
        assertEquals(elementsBeforeRemove.length - 1, elementsAfterRemove.length);
        assertEquals(elementsBeforeRemove[elementsBeforeRemove.length - 2], elementsAfterRemove[elementsAfterRemove.length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWhenElementAreNull() throws OperationNotSupportedException {
        Person[] dbElements = database.getElements();
        for (int i = 0; i < dbElements.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWithNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldTrowIfMissing() throws OperationNotSupportedException {
        database.findByUsername("Augustine");
    }

    @Test
    public void testFindByUsernameShouldReturnPerson() throws OperationNotSupportedException {
        Person person = database.findByUsername("Ivan");
        assertEquals(person.getId(), taylor.getId());
        assertEquals(person.getUsername(), taylor.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdShouldThrowWhenEmpty() throws OperationNotSupportedException {
        database.findById(5);
    }

    @Test
    public void testFindByIdShouldReturnPerson() throws OperationNotSupportedException {
        Person person = database.findById(1);
        assertEquals(person.getId(), taylor.getId());
        assertEquals(person.getUsername(), taylor.getUsername());
    }
}
