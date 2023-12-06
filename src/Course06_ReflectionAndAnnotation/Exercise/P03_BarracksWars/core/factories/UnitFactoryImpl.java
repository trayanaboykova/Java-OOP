package Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.core.factories;

import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.Unit;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.interfaces.UnitFactory;
import Course06_ReflectionAndAnnotation.Exercise.P03_BarracksWars.units.*;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {

        //With reflection
		/*try {
			Class unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor = unitClass.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
				 InvocationTargetException e) {
			System.out.println(e.getMessage());
		}*/

        switch (unitType) {
            case "Archer":
                return new Archer();
            case "Pikeman":
                return new Pikeman();
            case "Swordsman":
                return new Swordsman();
            case "Horseman":
                return new Horseman();
            case "Gunner":
                return new Gunner();
            default:
                throw new IllegalArgumentException("No such unit type!");
        }

    }
}
