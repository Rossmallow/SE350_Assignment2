import javafx.application.Application;
import javafx.scene.Scene;
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
		stage.show();
	}
	
	// Draws the rectangles and fills them with the appropriate colors
	public void drawMap(int d, int s, Pane p) {
		for (int x = 0; x < d; x++) {
			for (int y = 0; y < d; y++) {
				Rectangle rect = new Rectangle(x*s, y*s, s, s);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				p.getChildren().add(rect);
			}
		}
	}

}
