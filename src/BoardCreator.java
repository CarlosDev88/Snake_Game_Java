
package snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class BoardCreator extends JFrame implements Runnable {
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	int tcelda = 20;

	int tablero[][] = new int[WIDTH / tcelda][HEIGHT / tcelda];
	CreatorController creadorcontrol;

	BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	Graphics gbi = bi.getGraphics();

	public BoardCreator() {
		this.setTitle("Snake level creator");
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		creadorcontrol = new CreatorController();
		this.addKeyListener(creadorcontrol);
		Thread hilo = new Thread(this);
		hilo.start();

	}

	public void paint(Graphics g) {
		this.Fondo(gbi);
		this.Celda(gbi);
		this.serpiente(gbi);
		this.Pantalla(gbi);
		g.drawImage(bi, 0, 0, WIDTH, HEIGHT, this);
	}

	public void Fondo(Graphics g) {
		for (int i = 0; i < 50; i++) {
			Color c = new Color(120 + i, 210, 248);
			g.setColor(c);
			g.fillRect(0, this.tcelda * i, this.HEIGHT, this.tcelda);
		}

	}

	public void Pantalla(Graphics g) {
		for (Coordinate c : creadorcontrol.getMuro().getMuro()) {
			g.setColor(Color.blue);
			g.fillRect(c.getX() * this.tcelda, c.getY() * this.tcelda, this.tcelda, this.tcelda);
		}
	}

	public void Celda(Graphics g) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				g.setColor(Color.white);
				g.drawRect(i * this.tcelda, j * this.tcelda, this.tcelda, this.tcelda);
			}
		}
	}

	public void serpiente(Graphics g) {
		for (Coordinate c : creadorcontrol.getSnake().getBody()) {
			g.setColor(Color.yellow);
			g.fillRect(c.getX() * this.tcelda, c.getY() * this.tcelda, this.tcelda, this.tcelda);
		}
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				Logger.getLogger(BoardCreator.class.getName()).log(Level.SEVERE, null, ex);
			}
			creadorcontrol.ejecutarFrame();
			repaint();
		}
	}

}
