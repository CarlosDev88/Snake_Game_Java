
package snake_game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;
import javax.swing.JOptionPane;

public class Controller implements KeyListener {
	Coordinate comida;
	Snake snake;
	Orientacion ori;
	boolean fin = false;
	Screen pantalla;
	boolean hayPantalla;

	public Controller(boolean opcion) throws IOException, FileNotFoundException, ClassNotFoundException {
		if (opcion == true) {
			pantalla = new Screen();
			this.cargarPantalla();
			snake = new Snake();
			ori = Orientacion.UP;
			this.crearComida();
		} else {
			snake = new Snake();
			ori = Orientacion.UP;
			this.crearComida();
		}

	}

	public Coordinate getComida() {
		return comida;
	}

	public Snake getSnake() {
		return snake;
	}

	public Screen getPantalla() {
		return pantalla;
	}

	public boolean isHayPantalla() {
		return hayPantalla;
	}

	public void cargarPantalla() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream entrada = new FileInputStream("pantalla.dat");
		ObjectInputStream lector = new ObjectInputStream(entrada);
		pantalla = (Screen) lector.readObject();
		entrada.close();
	}

	public boolean hayColicion(Coordinate serpiente) {

		if ((comida.getX() == serpiente.getX()) && (comida.getY() == serpiente.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colicion(Coordinate serpiente) {
		boolean subfin = false;
		for (Coordinate c : snake.getBody()) {

			if ((c.getX() == serpiente.getX()) && (c.getY() == serpiente.getY())) {
				subfin = true;
			}
		}

		return subfin;
	}

	public boolean colicionMuro(Coordinate serpiente) {
		boolean subfin = false;
		for (Coordinate c : pantalla.getMuro()) {

			if ((c.getX() == serpiente.getX()) && (c.getY() == serpiente.getY())) {
				subfin = true;
			}
		}

		return subfin;
	}

	public boolean hayLimite(Coordinate serpiente) {
		boolean subfin = false;
		if ((serpiente.getX() == 40) || (serpiente.getY() == 40) || (serpiente.getY() < 0) || (serpiente.getX() < 0)) {
			subfin = true;
		}

		return subfin;
	}

	public void ejecutarFrame() {

		int x = snake.getBody().getFirst().getX();
		int y = snake.getBody().getFirst().getY();

		switch (ori) {
		case LEFT: {
			x--;
		}
			break;
		case UP: {
			y--;
		}
			break;
		case RIGHT: {
			x++;
		}
			break;
		case DOWN: {
			y++;
		}
			break;
		}

		Coordinate serpiente = new Coordinate(x, y);

		if (this.hayColicion(serpiente)) {
			crearComida();
		} else {
			snake.getBody().removeLast();
		}

		fin = false;

		if (this.colicionMuro(serpiente)) {
			fin = true;
		}

		if ((this.colicion(serpiente)) || (this.hayLimite(serpiente))) {
			fin = true;
		}
		if (fin == true) {
			JOptionPane.showMessageDialog(null, "te chocaste");
			System.exit(0);
		}
		snake.getBody().addFirst(serpiente);

	}

	public void crearComida() {

		Random r = new Random();
		int cx = r.nextInt(40);
		int cy = r.nextInt(40);
		comida = new Coordinate(cx, cy);
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		char tecla = ke.getKeyChar();

		switch (tecla) {
		case 'a': {
			if (ori != Orientacion.RIGHT) {
				ori = Orientacion.LEFT;
			}
		}
			break;
		case 'w': {
			if (ori != Orientacion.DOWN) {
				ori = Orientacion.UP;
			}
		}
			break;
		case 'd': {
			if (ori != Orientacion.LEFT) {
				ori = Orientacion.RIGHT;
			}
		}
			break;
		case 's': {
			if (ori != Orientacion.UP) {
				ori = Orientacion.DOWN;
			}
		}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {

	}

}
