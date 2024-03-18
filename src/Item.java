import enums.CollectibleTypes;

public interface Item {

    void take(CollectibleTypes collectibleType, double strength);
    void discard();
}
