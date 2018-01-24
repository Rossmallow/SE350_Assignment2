import java.util.Random;

public class Map {
	
	private int[][] map;
	
	// Returns the current values of the grid
	public int[][] getMap() {
		return map;
	}
	
	// Constructor
	public Map() {
		map = new int[10][10];
	}
	
	// Randomly generates n islands and sets their value to 1 in the map
	public void makeIslands(int n) {
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			map[rand.nextInt(10)][rand.nextInt(10)] = 1;
		}
	}
}
