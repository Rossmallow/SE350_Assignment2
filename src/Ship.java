import java.awt.*;
import java.util.Observable;

import javafx.scene.image.*;
import javafx.scene.image.Image;

public class Ship extends Observable {

	private Point location;
	private Image img = new Image("file:src/ship.png", 50, 50, true, true);
	private ImageView imgv = new ImageView (img);
	private int scale;
	private Map map;
	
	// Constructor
	public Ship(int scale, Map map) {
		location = new Point(0, 0);
		this.scale = scale;
		this.map = map;
	}
	
	// Returns the location of the ship
	public Point getShipLocation() {
		return location;
	}
	
	// Loads the ship image at the specified location
	public ImageView loadShipImage() {
		imgv.setX(location.x*scale);
		imgv.setY(location.y*scale);
		return imgv;
	}
	
	// Decrements the ship's y coordinate by 1
	public void goNorth() {
		int x = location.x;
		int y = location.y - 1;
		moveTo (new Point(x, y));
	}
	
	// Increments the ship's y coordinate by 1
	public void goSouth() {
		int x = location.x;
		int y = location.y + 1;
		moveTo (new Point(x, y));
	}
	
	// Increments the ship's x coordinate by 1
	public void goEast() {
		int x = location.x + 1;
		int y = location.y;
		moveTo (new Point(x, y));
	}
	
	// Decrements the ship's x coordinate by 1
	public void goWest() {
		int x = location.x - 1;
		int y = location.y;
		moveTo (new Point(x, y));
	}
	
	// Moves the ship to the specified position if it is not out of bounds or an island
	private void moveTo (Point p) {
		if (p.x >= 0 && p.x <= 9 && p.y >= 0 && p.y <= 9 && map.getMap()[p.x][p.y] == 0) {
			location = p;
			imgv.setX(location.getX() * scale);
			imgv.setY(location.getY() * scale);
			setChanged();
			notifyObservers();
		}
	}
}
