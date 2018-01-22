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
	private final Map map = new Map();
	private final Ship ship = new Ship(scale);
	
	int[][] grid = map.getMap();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("PirateShipGame");
		drawMap(dimension, scale, pane);
		initShip(pane);
		stage.show();
		startSailing(scene, pane);
	}
	
	// Draws the rectangles and fills them with the appropriate colors
	public void drawMap(int d, int s, Pane p) {
		for (int x = 0; x < d; x++) {
			for (int y = 0; y < d; y++) {
				Rectangle rect = new Rectangle(x * s, y * s, s, s);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				p.getChildren().add(rect);
			}
		}
	}
	
	public void initShip(Pane p) {
		ImageView i = ship.loadShipImage();
		p.getChildren().add(i);
	}
	
	public void drawShip(Pane p) {
		ImageView i = ship.loadShipImage();
		p.getChildren().set(p.getChildren().size() - 1, i);
	}
	
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
				drawShip(p);
			}
			
		});
	}
	
}
