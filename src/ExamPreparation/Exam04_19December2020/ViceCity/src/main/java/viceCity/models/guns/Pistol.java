package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;
    private static final int PISTOL_SHOT = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }
    @Override
    public int fire() {
        if (getBulletsPerBarrel() >= PISTOL_SHOT) {
            setBulletsPerBarrel(getBulletsPerBarrel() - PISTOL_SHOT);
            return PISTOL_SHOT;
        } else {
            int bulletsToReload = BULLETS_PER_BARREL - getBulletsPerBarrel();
            if (getTotalBullets() >= bulletsToReload) {
                setBulletsPerBarrel(BULLETS_PER_BARREL);
                setTotalBullets(getTotalBullets() - bulletsToReload);
                return PISTOL_SHOT;
            } else {
                int remainingBullets = getBulletsPerBarrel() + getTotalBullets();
                setBulletsPerBarrel(remainingBullets);
                setTotalBullets(0);
                return remainingBullets;
            }
        }
    }
}
