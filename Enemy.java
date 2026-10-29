package mjau;

public class Enemy extends GameObject {

	private String type;
	private int damage;
	private int health;

	public Enemy() {
		super();
		this.type = "default";
		this.damage = 0;
		this.health = 1;
	}

	// Novi konstruktor: health je podrazumijevano 1
	public Enemy(String type, int x, int y, int width, int height, int damage) {
		this(type, x, y, width, height, damage, 1);
	}

	public Enemy(String type, int x, int y, int width, int height, int damage, int health) {
		super(x, y, width, height);
		this.type = type;
		this.damage = damage;
		this.health = health;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null || type.isEmpty()) {
			System.out.println("Ponovo unesite tip: ");
		} else {
			this.type = type;
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	// Stvarna šteta pri napadu; BossEnemy je nadjačava
	public int getAttackDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Enemy [" + type + "] (" + getX() + "," + getY() + ") "
				+ getWidth() + "x" + getHeight() + ", damage=" + damage + ", health=" + health;
	}

}