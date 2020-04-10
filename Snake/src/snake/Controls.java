package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

    public boolean UP;
    public boolean DOWN;
    public boolean LEFT;
    public boolean RIGHT;
    public boolean RERUN;
    public boolean SHOW_GRIDS;
    public boolean DARK_MODE;
    
    private Properties prop;

    public Controls(Properties prop) {
    	this.prop = prop;
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = true;
        RERUN = false;
        SHOW_GRIDS = false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == 37 && !RIGHT) {
            //LEFT
            LEFT = true;
            RIGHT = false;
            UP = false;
            DOWN = false;
        } else if (ke.getKeyCode() == 38 && !DOWN) {
            //UP
            LEFT = false;
            UP = true;
            RIGHT = false;
            DOWN = false;
        } else if (ke.getKeyCode() == 39 && !LEFT) {
            //RIGHT
            LEFT = false;
            RIGHT = true;
            UP = false;
            DOWN = false;
        } else if (ke.getKeyCode() == 40 && !UP) {
            //DOWN
            LEFT = false;
            RIGHT = false;
            UP = false;
            DOWN = true;
        }else if(ke.getKeyCode() == 82){
            RERUN = true;
        }else if(ke.getKeyCode() == 27){
            System.exit(0);
        }else if(ke.getKeyCode() == 71){
            SHOW_GRIDS = !SHOW_GRIDS;
        }else if(ke.getKeyCode() == 68){
            DARK_MODE = !DARK_MODE;
        }else if(ke.getKeyChar() == 'n' || ke.getKeyChar() == 'N') {
        	System.out.println("NG");
        	prop.startANewGame();
        }
        //System.out.println(ke.getKeyCode());
    }
}
