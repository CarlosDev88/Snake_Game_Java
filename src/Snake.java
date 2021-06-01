
package snake_game;

import java.util.LinkedList;

public class Snake {
    LinkedList<Coordinate> body = new LinkedList<Coordinate>();

    public Snake() {
        Coordinate sc = new Coordinate(20,20);
        body.addFirst(sc);
    }

    public LinkedList<Coordinate> getBody() {
        return body;
    }

    public void setBody(LinkedList<Coordinate> body) {
        this.body = body;
    }
    
    
}
