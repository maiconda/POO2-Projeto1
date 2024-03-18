import enums.CollectibleTypes;

import java.util.Random;

public class Collectible implements Item{

    private CollectibleTypes collectibleType;
    private Double strength;

    Collectible() {
        this.collectibleType = CollectibleTypes.None;
        this.strength = 0.0;
    }
    public Double getStrength() {
        return strength;
    }

    public CollectibleTypes getCollectibleType() {
        return collectibleType;
    }

    @Override
    public void take(CollectibleTypes collectibleType, double strength) {
        this.collectibleType = collectibleType;
        this.strength = strength;
    }

    @Override
    public void discard() {
        this.collectibleType = CollectibleTypes.None;
        this.strength = 0.0;
    }
}
