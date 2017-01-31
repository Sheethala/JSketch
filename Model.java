import java.util.Observable;
import java.awt.Color;
// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model extends Observable {
	// the data in the model, just a counter
	private int counter;
	public Boolean line = false;
	public Boolean square = false;
	public Boolean circle = false;
	public Boolean selection = false;
	public float chosenStrokeThickness = 2.0f;
	public Color chosenColour = Color.BLACK;
	public Boolean blue = false;
	public Boolean red = false;
	public Boolean green = false;
	public Boolean violet = false;
	public Boolean yellow = false;
	public Boolean orange = false;
	public Boolean black = true;
	Model() {
		setChanged();
	}

	public int getCounterValue() {
		return counter;
	}

	public void resetColours(String colour){
		if(colour == "blue"){
			chosenColour = Color.BLUE;
			blue = true;
			red = false;
			green = false;
			violet = false;
			yellow = false;
			orange = false;
			black = false;
			selection = false;
		}
		if(colour == "red"){
			chosenColour = Color.RED;
			red = true;
			blue = false;
			green = false;
			violet = false;
			yellow = false;
			orange = false;
			black = false;
			selection = false;
		}
		if(colour == "green"){
			chosenColour = Color.GREEN;
			green = true;
			red = false;
			blue = false;
			violet = false;
			yellow = false;
			orange = false;
			black = false;
			selection = false;
		}
		if(colour == "violet"){
			chosenColour = Color.MAGENTA;
			violet = true;
			red = false;
			green = false;
			blue = false;
			yellow = false;
			orange = false;
			black = false;
			selection = false;
		}
		if(colour == "yellow"){
			chosenColour = Color.YELLOW;
			yellow = true;
			red = false;
			green = false;
			violet = false;
			blue = false;
			orange = false;
			black = false;
			selection = false;
		}
		if(colour == "orange"){
			chosenColour = Color.ORANGE;
			orange = true;
			red = false;
			green = false;
			violet = false;
			yellow = false;
			blue = false;
			black = false;
			selection = false;
		}
	}

	public void setColour(String colour){
		resetColours(colour);
	}

	public void drawLine(){
		line = true;
		square = false;
		circle = false;
		selection = false;
	}

	public void selectionTool(){
		selection = true;
	}

	public void drawSquare(){
		square = true;
		line = false;
		circle = false;
		selection = false;
	}

	public void drawCircle(){
		circle= true;
		line = false;
		square = false;
		selection = false;
	}

	public void incrementCounter() {
		if (counter < 5) {
			counter++;
			setChanged();
			notifyObservers();
		}
	}
}
