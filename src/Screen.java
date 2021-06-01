
package snake_game;

import java.io.Serializable;
import java.util.ArrayList;

public class Screen implements Serializable {
	ArrayList<Coordinate> muro = new ArrayList<Coordinate>();

	public ArrayList<Coordinate> getMuro() {
		return muro;
	}

	public void setMuro(ArrayList<Coordinate> muro) {
		this.muro = muro;
	}
}
