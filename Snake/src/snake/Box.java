package snake;

import java.awt.Color;

public class Box {

    public Color color;
    public int x1;
    public int y1;
    
    public Box(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = Color.black;
    }
    public Box(int x1, int y1, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }
}
