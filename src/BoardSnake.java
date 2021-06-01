/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BoardSnake extends JFrame implements Runnable {
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	int tcelda = 20;
	int tablero[][] = new int[WIDTH / tcelda][HEIGHT / tcelda];
	Controller control;
	boolean hayPantalla = false;
	
	BufferedImage bi = new BufferedImage(800, 800, BufferedImage.TYPE_4BYTE_ABGR);
	Graphics gbi = bi.getGraphics();

	public BoardSnake() throws IOException, FileNotFoundException, ClassNotFoundException {
		this.cargarTablero();
		control = new Controller(hayPantalla);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Snake Game");

		this.addKeyListener(control);
		Thread hilo = new Thread(this);
		hilo.start();

	}	

	private void cargarTablero() {
		int opcion = JOptionPane.showConfirmDialog(null, "Quieres jugar con el ultimo tablero creado");

		if (opcion == 0) {
			hayPantalla = true;
		} else {
			hayPantalla = false;
		}
	}

	public void paint(Graphics g) {
		this.pintarFondo(gbi);
		this.pintarCeldas(gbi);
		this.pintarComida(gbi);
		this.pintarserpiente(gbi);
		if (hayPantalla == true) {
			this.pintarPantalla(gbi);
		}
		g.drawImage(bi, 0, 0, 800, 800, this);
	}

	public void pintarFondo(Graphics g) {
		for (int i = 0; i < 50; i++) {
			Color c = new Color(120 + i, 210, 248);
			g.setColor(c);
			g.fillRect(0, tcelda * i, HEIGHT, tcelda);
		}

	}

	public void pintarCeldas(Graphics g) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				g.setColor(Color.white);
				g.drawRect(i * tcelda, j * tcelda, tcelda, tcelda);
			}
		}
	}

	public void pintarComida(Graphics g) {
		Coordinate ccomida = control.getComida();
		g.setColor(Color.MAGENTA);
		g.fillRect(ccomida.getX() * tcelda, ccomida.getY() * tcelda, tcelda, tcelda);

	}

	public void pintarserpiente(Graphics g) {
		for (Coordinate c : control.getSnake().getBody()) {
			g.setColor(Color.yellow);
			g.fillRect(c.getX() * tcelda, c.getY() * tcelda, tcelda, tcelda);
		}
	}

	public void pintarPantalla(Graphics g) {
		for (Coordinate c : control.getPantalla().getMuro()) {
			g.setColor(Color.blue);
			g.fillRect(c.getX() * tcelda, c.getY() * tcelda, tcelda, tcelda);
		}
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				Logger.getLogger(BoardSnake.class.getName()).log(Level.SEVERE, null, ex);
			}
			control.ejecutarFrame();
			repaint();
		}
	}

}
