import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pirate implements Observer {

	private Point shipLocation;
	private Point location;
	private int scale;
	private Map map;
	private Image img = new Image("file:src/pirateShip.png", 50, 50, true, true);
	private ImageView imgv = new ImageView (img);
	private Ship ship;
	
	// Constructor
	public Pirate(int scale, Map map, Ship ship) {
		Random rand = new Random();
		location = new Point(rand.nextInt(8) + 1, rand.nextInt(8) + 1);
		this.scale = scale;
		this.map = map;
		this.ship = ship;
		shipLocation = this.ship.getShipLocation();
	}
	
	// Gets called every time the ship moves
	@Override
	public void update(Observable ship, Object arg) {
		if (ship instanceof Ship) {
			shipLocation = ((Ship)ship).getShipLocation();
			navigate();
		}
	}
	
	// Returns the location of the pirate
	public Point getPirateLocation() {
		return location;
	}
	
	// Loads the pirate image at the specified location
	public ImageView loadPirateImage() {
		imgv.setX(location.x * scale);
		imgv.setY(location.y * scale);
		return imgv;
	}
	
	// Navigates the map in order to follow the ship
	private void navigate() {
		int x = location.x - shipLocation.x;
		int y = location.y - shipLocation.y;
		
		if (x > 0 && map.getMap()[location.x - 1][location.y] == 0)
			goWest();
		else if (x < 0 && map.getMap()[location.x + 1][location.y] == 0)
			goEast();
		else if (y > 0 && map.getMap()[location.x][location.y - 1] == 0)
			goNorth();
		else if (y < 0 && map.getMap()[location.x][location.y + 1] == 0)
			goSouth();
	}
	
	// Decrements the pirate's y coordinate by 1
	private void goNorth() {
		int x = location.x;
		int y = location.y - 1;
		moveTo (new Point(x, y));
	}
	
	// Increments the pirate's y coordinate by 1
	private void goSouth() {
		int x = location.x;
		int y = location.y + 1;
		moveTo (new Point(x, y));
	}
	
	// Increments the pirate's x coordinate by 1
	private void goEast() {
		int x = location.x + 1;
		int y = location.y;
		moveTo (new Point(x, y));
	}
	
	// Decrements the pirate's x coordinate by 1
	private void goWest() {
		int x = location.x - 1;
		int y = location.y;
		moveTo (new Point(x, y));
	}
	
	// Moves the pirate to the specified position if it is not out of bounds or an island
	private void moveTo (Point p) {
		if (p.x >= 0 && p.x <= 9 && p.y >= 0 && p.y <= 9 && map.getMap()[p.x][p.y] == 0) {
			location = p;
			imgv.setX(location.getX() * scale);
			imgv.setY(location.getY() * scale);
		}
	}
}
