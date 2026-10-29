package mjau;

public class Player extends GameObject {
	
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	
	public Player() {
		this.name = "default";
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.health = 1;
	}
	
	public Player(String name, int x, int y, int width, int height, int health) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.isEmpty()) {
			System.out.println("Ponovo unesite ime: ");
		}else if(name.charAt(0) >= 'a' && name.charAt(0) <= 'z') {
			System.out.println("Svaka rijec mora poceti velikim slovom. ");
		
		}else {
			name.trim();
			this.name = name;
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if(health > 0 && health <= 100)
		this.health = health;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "] @  (" + x + ", " + y + ") " + width + " X " + height
				+ ", health=" + health + "]";
	}
	
		private static String normalizeName(String raw) {
			String[] words = raw.trim().split("\\s+");
			StringBuilder sb = new StringBuilder();
			for (String w : words) {
				if (sb.length() > 0) {
					sb.append(" ");
				}
				sb.append(Character.toUpperCase(w.charAt(0)));
				sb.append(w.substring(1).toLowerCase());
			}
			return sb.toString();
		}
	
	
	
	
	
	
	

}
