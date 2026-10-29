package mjau;

public class MeleeEnemy extends Enemy {

	public MeleeEnemy() {
		super();
		setType("Melee");
	}

	public MeleeEnemy(int x, int y, int width, int height, int damage, int health) {
		super("Melee", x, y, width, height, damage, health);
	}

	
	public boolean canAttack(Player target) {
		return getHealth() > 0 && intersects(target);
	}

	
	public boolean attack(Player target) {
		if (!canAttack(target)) {
			return false;
		}
		int newHealth = Math.max(0, target.getHealth() - getDamage());
		target.setHealth(newHealth);
		return true;
	}

	@Override
	public String toString() {
		return "Melee" + super.toString();
	}

	public static void main(String[] args) {
		Player p = new Player("marko", 0, 0, 10, 10, 100);
		MeleeEnemy near = new MeleeEnemy(5, 5, 10, 10, 15, 50);
		MeleeEnemy far = new MeleeEnemy(50, 50, 10, 10, 15, 50);

		System.out.println(near.attack(p) + " -> HP=" + p.getHealth());  // true -> HP=85
		System.out.println(far.attack(p) + " -> HP=" + p.getHealth());   // false -> HP=85
	}

}