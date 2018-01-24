import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller extends Application{

	private Pane pane;
	private final int dimension = 10;
	private final int scale = 50;
	
	private Ship ship;
	private Pirate pirate1;
	private Pirate pirate2;
	private final Map map = new Map();
	private int[][] grid = map.getMap();
	
	public Controller() {
		this.ship = new Ship(scale, map);
		this.pirate1 = new Pirate(scale, map, ship);
		this.pirate2 = new Pirate(scale, map, ship);
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Pirate Ship Game");
		drawMap(dimension, scale, pane);
		drawShip(pane);
		drawPirate(pane, pirate1);
		drawPirate(pane, pirate2);
		stage.show();
		startSailing(scene, pane);
	}
	
	// Draws the rectangles and fills them with the appropriate colors
	public void drawMap(int d, int s, Pane p) {
		map.makeIslands(10); // Argument defines the number of islands to generate
		for (int x = 0; x < d; x++) {
			for (int y = 0; y < d; y++) {
				Rectangle rect = new Rectangle(x * s, y * s, s, s);
				rect.setStroke(Color.BLACK);
				if (grid[x][y] == 1)
					rect.setFill(Color.GREEN);
				else
					rect.setFill(Color.PALETURQUOISE);
				p.getChildren().add(rect);
			}
		}
	}
	
	// Adds the ship image view to the pane
	public void drawShip(Pane p) {
		ImageView i = ship.loadShipImage();
		p.getChildren().add(i);
	}
	
	// Adds the pirate image view to the pane
	public void drawPirate(Pane p, Object pirate) {
		ImageView i = ((Pirate) pirate).loadPirateImage();
		p.getChildren().add(i);
	}
	
	// Handles key events to call methods in the ship class which change the ships position
	public void startSailing(Scene scene, Pane p) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
			}
			
		});
	}
	
}
