package mjau;

public class BossEnemy extends Enemy {

	private static final int DAMAGE_MULTIPLIER = 2;

	public BossEnemy() {
		super();
		setType("Boss");
	}

	public BossEnemy(int x, int y, int width, int height, int damage, int health) {
		super("Boss", x, y, width, height, damage, health);
	}

	
	public int getAttackDamage() {
		return getDamage() * DAMAGE_MULTIPLIER;
	}

	public boolean canAttack(Player target) {
		return getHealth() > 0 && intersects(target);
	}

	
	public boolean attack(Player target) {
		if (!canAttack(target)) {
			return false;
		}
		int newHealth = Math.max(0, target.getHealth() - getAttackDamage());
		target.setHealth(newHealth);
		return true;
	}

	@Override
	public String toString() {
		return "Boss" + super.toString() + ", attackDamage=" + getAttackDamage();
	}

	public static void main(String[] args) {
		Player p = new Player("Luka", 0, 0, 10, 10, 100);
		BossEnemy boss = new BossEnemy(5, 5, 20, 20, 15, 200);

		System.out.println(boss);
		System.out.println(boss.attack(p) + " -> HP=" + p.getHealth());  
	}

}