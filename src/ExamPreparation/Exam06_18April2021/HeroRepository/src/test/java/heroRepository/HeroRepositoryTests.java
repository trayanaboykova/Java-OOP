package heroRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
    }

    @Test
    public void testConstructor() {
        assertNotNull(this.heroRepository);
        assertEquals(0, this.heroRepository.getCount());
    }

    @Test
    public void testCreateHeroSuccessfully() {
        Hero hero = new Hero("Superman", 100);
        String result = this.heroRepository.create(hero);

        assertEquals(1, this.heroRepository.getCount());
        assertEquals("Successfully added hero Superman with level 100", result);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHeroWithNull() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroWithExistingName() {
        Hero hero1 = new Hero("Batman", 90);
        Hero hero2 = new Hero("Batman", 95);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
    }

    @Test
    public void testRemoveHeroSuccessfully() {
        Hero hero = new Hero("Wonder Woman", 80);
        this.heroRepository.create(hero);

        assertTrue(this.heroRepository.remove("Wonder Woman"));
        assertEquals(0, this.heroRepository.getCount());
    }

    @Test
    public void testRemoveHeroWithNonExistingName() {
        assertFalse(this.heroRepository.remove("Spider-Man"));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroWithNullName() {
        this.heroRepository.remove(null);
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        Hero hero1 = new Hero("Thor", 95);
        Hero hero2 = new Hero("Hulk", 90);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);

        Hero highestLevelHero = this.heroRepository.getHeroWithHighestLevel();
        assertNotNull(highestLevelHero);
        assertEquals("Thor", highestLevelHero.getName());
    }

    @Test
    public void testGetHeroWithHighestLevelWithEmptyRepository() {
        assertNull(this.heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHero() {
        Hero hero = new Hero("Black Widow", 85);
        this.heroRepository.create(hero);

        Hero foundHero = this.heroRepository.getHero("Black Widow");
        assertNotNull(foundHero);
        assertEquals("Black Widow", foundHero.getName());
    }

    @Test
    public void testGetHeroWithNonExistingName() {
        assertNull(this.heroRepository.getHero("Iron Man"));
    }

    @Test
    public void testGetHeroes() {
        Hero hero1 = new Hero("Captain America", 88);
        Hero hero2 = new Hero("Iron Man", 92);

        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);

        Collection<Hero> heroes = this.heroRepository.getHeroes();
        assertNotNull(heroes);
        assertEquals(2, heroes.size());
    }
}
