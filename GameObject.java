package mjau;

public class GameObject {

	private int x;
	private int y;
	private int width;
	private int height;

	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	public GameObject(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public boolean intersects(GameObject other) {
		return this.x < other.x + other.width
				&& this.x + this.width > other.x
				&& this.y < other.y + other.height
				&& this.y + this.height > other.y;
	}

	@Override
	public String toString() {
		return "GameObject @ (" + x + "," + y + ") " + width + "x" + height;
	}

	public static void main(String[] args) {
		GameObject a = new GameObject(0, 0, 10, 10);
		GameObject b = new GameObject(5, 5, 10, 10);
		GameObject c = new GameObject(20, 20, 5, 5);

		System.out.println(a);                 
		System.out.println(a.intersects(b));   
		System.out.println(a.intersects(c));   
	}

}