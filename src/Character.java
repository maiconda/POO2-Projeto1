abstract class Character {
    private final String name;
    private int damage;
    private Double life;
    private int level;

    public Character(String name, int damage, int level) {
        this.name = name;
        this.damage = damage;
        this.life = 100.0;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public Double getLife() {
        return life;
    }

    public int getLevel() {
        return level;
    }

    public void setLife(Double newLife) {
        this.life = newLife;
    }

    public void upLevel() {
        this.level += 1;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }

    public void attack(Character character) {
        character.setLife(character.getLife() - this.getDamage());
    }

    public abstract void defend();
}
