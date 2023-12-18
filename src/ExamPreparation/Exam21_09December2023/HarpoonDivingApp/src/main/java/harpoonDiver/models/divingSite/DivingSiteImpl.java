package harpoonDiver.models.divingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static harpoonDiver.common.ExceptionMessages.*;

public class DivingSiteImpl implements DivingSite {
    private String name;
    private Collection<String> seaCreatures;

    public DivingSiteImpl(String name) {
        setName(name);
        this.seaCreatures = new ArrayList<>();
    }

    @Override
    public Collection<String> getSeaCreatures() {
        return seaCreatures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()){
            throw new NullPointerException(SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


}
