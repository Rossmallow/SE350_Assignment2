import java.util.Random;

// A class to handle generation of a map and islands
public class Map {
	
	private int[][] map;
	private int prevX, prevY;
	
	// Returns the current values of the grid
	public int[][] getMap() {
		return map;
	}
	
	// Constructor
	public Map() {
		map = new int[10][10];
	}

	// Updates the position of the pirates in the map
	public void updateMap(int newX, int newY) {
		map[newX][newY] = 2;
		map[prevX][prevY] = 0;
		prevX = newX;
		prevY = newY;
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
