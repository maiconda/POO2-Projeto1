import enums.CollectibleTypes;

public class Hero extends Character{

    public Collectible item;

    public Hero(String name) {
        super(name, 10, 1);
        this.item = new Collectible();
    }

    public Collectible getItem() {
        return item;
    }
    @Override
    public void attack(Character monster) {
        monster.setLife(monster.getLife() - this.getDamage());
    }

    @Override
    public void defend() {
        this.setLife(getLife() + 10);
    }

    public void useItem(Character monster){
        if(this.item.getCollectibleType() == CollectibleTypes.Weapon){
            this.attack(monster);
            this.item.discard();
        } else if (this.item.getCollectibleType() == CollectibleTypes.Potion){
            this.setLife(this.getLife() + this.item.getStrength());
        }
    }
}
