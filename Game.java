package mjau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	private Player player;
	private Enemy[] enemies = {};
	private List<String> eventLog = new ArrayList<>();

	public Game(Player player) {
		this.player = player;
	}

	public boolean checkCollision(Player p, Enemy e) {
		return p.intersects(e);
	}

	public void decreaseHealth(Player p, Enemy e) {
		int oldHp = p.getHealth();
		int dmg = e.getAttackDamage();
		int newHp = Math.max(0, oldHp - dmg);
		p.setHealth(newHp);
		eventLog.add("HIT: " + p.getName() + " by " + e.getType() + " for " + dmg
				+ " -> HP " + oldHp + " -> " + newHp);
	}

	public void addEnemy(Enemy e) {
		enemies = Arrays.copyOf(enemies, enemies.length + 1);
		enemies[enemies.length - 1] = e;
		eventLog.add("ADD: " + e);
	}

	public List<Enemy> findByType(String query) {
		List<Enemy> result = new ArrayList<>();
		String q = query.trim().toLowerCase();
		for (Enemy e : enemies) {
			if (e.getType().toLowerCase().contains(q)) {
				result.add(e);
			}
		}
		return result;
	}

	public List<Enemy> collidingWithPlayer() {
		List<Enemy> result = new ArrayList<>();
		for (Enemy e : enemies) {
			if (checkCollision(player, e)) {
				result.add(e);
			}
		}
		return result;
	}

	public void resolveCollisions() {
		for (Enemy e : collidingWithPlayer()) {
			decreaseHealth(player, e);
		}
	}


	public static Enemy parseEnemy(String s) {
		String[] parts = s.split(";");
		if (parts.length < 4) {
			throw new IllegalArgumentException("Neispravan format: " + s);
		}
		String type = parts[0].trim();
		String[] pos = parts[1].split(",");
		String[] size = parts[2].toLowerCase().split("x");
		if (pos.length != 2 || size.length != 2) {
			throw new IllegalArgumentException("Neispravan format pozicije ili dimenzija: " + s);
		}
		int damage = Integer.parseInt(parts[3].trim());
		int x = Integer.parseInt(pos[0].trim());
		int y = Integer.parseInt(pos[1].trim());
		int w = Integer.parseInt(size[0].trim());
		int h = Integer.parseInt(size[1].trim());

		if (parts.length > 4) {
			return new Enemy(type, x, y, w, h, damage, Integer.parseInt(parts[4].trim()));
		}
		return new Enemy(type, x, y, w, h, damage);
	}

	public Player getPlayer() {
		return player;
	}

	public Enemy[] getEnemies() {
		return enemies;
	}

	public List<String> getEventLog() {
		return eventLog;
	}

	public static void main(String[] args) {
		Game game = new Game(new Player("Karim Dzankovic", 10, 5, 32, 32, 85));
		ArrayList <Enemy> neprijatelji = new ArrayList <Enemy>();

		neprijatelji.add(new Enemy("Goblin", 43, 77, 16, 16, 20));
		neprijatelji.add(new Enemy("Goblin", 22, 11, 3, 5, 19));
		neprijatelji.add(new Enemy("Goblin", 22, 54, 14, 29, 48));
		neprijatelji.add(new Enemy("Goblin", 19, 60, 75, 88, 21));

		game.addEnemy(new Enemy("Hobgoblin", 30, 5, 16, 16, 10));
		game.addEnemy(parseEnemy("Goblin;12,5;16x16;20"));

		
		game.addEnemy(new MeleeEnemy(30, 5, 16, 16, 10, 50));    
		game.addEnemy(new BossEnemy(35, 20, 20, 20, 15, 200));   
		game.addEnemy(new MeleeEnemy(200, 200, 16, 16, 15, 30)); 

		
		game.addEnemy(parseEnemy("Skeleton;40,30;10x10;5"));      
		game.addEnemy(parseEnemy("Orc;100,100;16x16;25;80"));     

		
		System.out.println("=== Svi neprijatelji ===");
		for (Enemy e : game.getEnemies()) {
			System.out.println(e);
		}

		
		System.out.println("\n=== Neprijatelji koji sadrže \"gob\" ===");
		for (Enemy e : game.findByType("gob")) {
			System.out.println(e);
		}

		System.out.println("\n=== Stanje prije ===");
		System.out.println(game.getPlayer());

		List<Enemy> sudari = game.collidingWithPlayer();
		System.out.println("\n=== Provjera kolizija ===");
		System.out.println("Sudaraju se: " + sudari.size() + " neprijatelja");
		if (sudari.isEmpty()) {
			System.out.println("Nema kolizija.");
		} else {
			for (Enemy e : sudari) {
				System.out.println("Sudar sa: " + e);
			}
		}

		
		game.resolveCollisions();

		
		System.out.println("\n=== Stanje poslije ===");
		System.out.println(game.getPlayer());

		
		System.out.println("\n=== Event log ===");
		for (String line : game.getEventLog()) {
			System.out.println(line);
		}
	}
}