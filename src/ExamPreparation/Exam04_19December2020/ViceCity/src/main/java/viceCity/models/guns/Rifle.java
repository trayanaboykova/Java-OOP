package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;
    private static final int RIFLE_SHOT = 5;
    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }
    @Override
    public int fire() {
        if (getBulletsPerBarrel() >= RIFLE_SHOT) {
            setBulletsPerBarrel(getBulletsPerBarrel() - RIFLE_SHOT);
            return RIFLE_SHOT;
        } else {
            int bulletsToReload = BULLETS_PER_BARREL - getBulletsPerBarrel();
            if (getTotalBullets() >= bulletsToReload) {
                setBulletsPerBarrel(BULLETS_PER_BARREL);
                setTotalBullets(getTotalBullets() - bulletsToReload);
                return RIFLE_SHOT;
            } else {
                int remainingBullets = getBulletsPerBarrel() + getTotalBullets();
                setBulletsPerBarrel(remainingBullets);
                setTotalBullets(0);
                return remainingBullets;
            }
        }
    }
}
