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
		int i = 0;
		int r1 = rand.nextInt(10);
		int r2 = rand.nextInt(10);
		while (i < n) {
			if (map[r1][r2] == 0) {
				map[r1][r2] = 1;
				i++;
			}
			r1 = rand.nextInt(10);
			r2 = rand.nextInt(10);
		}
	}
}
