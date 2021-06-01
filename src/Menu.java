package snake_game;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Menu {
	
	public Menu() throws FileNotFoundException, ClassNotFoundException, IOException {
		String[] options = { "Crear un Tablero", "Jugar Una Partida" };

		int option = JOptionPane.showOptionDialog(null, "Selecciona una de las opciones", "Has click en el boton",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		String movimientos = "Para moverte por el tablero se usan:\n "
				+ "W para moverte  arriba\n"
				+ "S Para moverte  abajo\n"
				+ "A Para moverte a izquierda\n"
				+ "D Para moverte a derecha\n"
				+ "S Para moverte  abajo\n"
				;

		if (option == 0) {
			String message= movimientos + "para crear un tablero mantén presionado\n"
					+ " una de las teclas del movimiento y la barra espaciadora\n"
					+"Una vez te guste el nivel creado lo guardas oprimiendo enter";
			
			JOptionPane.showMessageDialog(null, message);
			new BoardCreator();
		} else {
			String message= movimientos + "si colisionas contra un muro\n"
					+ " o contra uno de los bordes  pierdes el juego";
			
			JOptionPane.showMessageDialog(null, message);
			new BoardSnake();
		}
	}
	

}
