package snake_game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CreatorController implements KeyListener {
	Coordinate comida;
	Snake snake;
	Orientacion ori;
	Orientacion accion;
	private boolean fin = false;
	private boolean pintar = false;
	Screen muro;

	public CreatorController() {

		snake = new Snake();
		ori = Orientacion.UP;
		muro = new Screen();
	}

	public Coordinate getComida() {
		return comida;
	}

	public Snake getSnake() {
		return snake;
	}

	public Screen getMuro() {
		return muro;
	}

	public void guardarPantalla() throws FileNotFoundException, IOException {
		FileOutputStream salida = new FileOutputStream("pantalla.dat");
		ObjectOutputStream objeto = new ObjectOutputStream(salida);
		objeto.writeObject(muro);
		salida.close();
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

		if (accion == Orientacion.SPACE) {
			this.crearMuro(snake.getBody().getFirst());
		}

		Coordinate serpiente = new Coordinate(x, y);
		snake.getBody().removeLast();

		fin = false;
		if ((this.colicion(serpiente)) || (this.hayLimite(serpiente))) {
			fin = true;
		}
		if (fin == true) {
			JOptionPane.showMessageDialog(null, "te chocaste");
			System.exit(0);
		}
		snake.getBody().addFirst(serpiente);

	}

	public void crearMuro(Coordinate serpiente) {
		muro.getMuro().add(serpiente);
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
		case ' ': {
			accion = Orientacion.SPACE;
			pintar = true;
		}
			break;
		case '\n': {
			try {
				this.guardarPantalla();
				JOptionPane.showMessageDialog(null, "se ha gurdado el tablero que has dibujado");
				System.exit(0);
			} catch (IOException ex) {
				Logger.getLogger(CreatorController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		char tecla = ke.getKeyChar();

		switch (tecla) {
		case ' ': {
			accion = Orientacion.NOTHING;
			pintar = false;
		}
			break;
		}

	}

	private boolean colicion(Coordinate coordinate) {
		boolean subfin = false;
		for (Coordinate c : snake.getBody()) {

			if ((c.getX() == coordinate.getX()) && (c.getY() == coordinate.getY())) {
				subfin = true;
			}
		}

		return subfin;
	}

	private boolean hayLimite(Coordinate coordinate) {
		boolean subfin = false;
		if ((coordinate.getX() == 40) || (coordinate.getY() == 40) || (coordinate.getY() < 0)
				|| (coordinate.getX() < 0)) {
			subfin = true;
		}

		return subfin;
	}
}
