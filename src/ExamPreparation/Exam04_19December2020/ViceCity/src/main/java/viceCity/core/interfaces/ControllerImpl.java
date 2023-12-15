package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;


public class ControllerImpl implements Controller {
    private Player mainPlayer;
    private Collection<Player> civilPlayers;
    private GunRepository models;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new ArrayList<>();
        this.models = new GunRepository();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        this.civilPlayers.add(new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        switch (type) {
            case "Pistol":
                this.models.add(new Pistol(name));
                break;
            case "Rifle":
                this.models.add(new Rifle(name));
                break;
            default:
                return String.format(GUN_TYPE_INVALID);
        }

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.models.getModels().isEmpty()) {
            return String.format(GUN_QUEUE_IS_EMPTY);
        }
        Gun gun = this.models.getModels().iterator().next();

        if ("Vercetti".equals(name)) {
            this.mainPlayer.getGunRepository().add(gun);
            this.models.remove(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), this.mainPlayer.getName());
        } else {
            Player player = this.civilPlayers.stream()
                    .filter(e -> e.getName().equals(name))
                    .findFirst()
                    .orElse(null);

            if (player != null) {
                player.getGunRepository().add(gun);
                this.models.remove(gun);
                return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
            } else {
                return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
            }
        }
    }

    @Override
    public String fight() {

        int initialSizeOfCivilPlayers = this.civilPlayers.size();

        this.neighbourhood.action(this.mainPlayer, this.civilPlayers);

        this.civilPlayers = this.civilPlayers
                .stream()
                .filter(Player::isAlive)
                .collect(Collectors.toList());

        if (this.mainPlayer.isAlive() && this.civilPlayers.size() == initialSizeOfCivilPlayers) {
            return String.format(FIGHT_HOT_HAPPENED);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FIGHT_HAPPENED)).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints())).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, initialSizeOfCivilPlayers - this.civilPlayers.size())).append(System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size()));

        return sb.toString().trim();
    }
}
