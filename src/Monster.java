public class Monster extends Character {

    public Monster(String name, int damage, int level) {
        super(name, damage, level);
    }
    @Override
    public void defend() {
        this.setLife(getLife() + 5 * (((this.getLevel()) - 1) * 0.1  * 5));
    }
}