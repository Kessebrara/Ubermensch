import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class PlayerController extends Controller
{
    private ArrayList<String> keysPressed = new ArrayList<String>();
    private ArrayList<String> keysReleased = new ArrayList<String>();
    
    private Player player;
    
    private boolean movingLeft, movingDown, movingRight, movingUp;
    World world;
    
    public PlayerController(World world, Player player)    
    {
        super(world);
        this.player = player;
    }
    
    public void keyStroke(KeyEvent e, boolean pressing)
    {        
        String key = e.getCode().toString();
        
        if(pressing && !keysPressed.contains(key))
        keysPressed.add(key);
        
        if(!pressing)
        {
            keysPressed.remove(key);
            
            switch(key)
            {
                case "A":
                movingLeft = false;
                case "D":
                movingRight = false;
                case "S":
                movingDown = false;
                case "W":
                movingUp = false;
            }
        
        }            
    }
    
    public void mousePress(MouseEvent e)
    {
    }
    
    public void handleShortcuts()
    {        
        if(keysPressed.contains("A"))
        {
            movingLeft = true;
            movingRight = false;
        }
        
        if(keysPressed.contains("D"))
        {
            movingLeft = false;
            movingRight = true;
        }
        
        if(keysPressed.contains("S"))
        {
            movingDown = true;
            movingUp = false;
        }
        
        if(keysPressed.contains("W"))
        {
            movingDown = false;
            movingUp = true;
        }
    }
    
    @Override
    public void process(double time)
    {
        handleShortcuts();
        Point destination = new Point(player.location);
        
        if(movingDown)
        destination.translate(0, 1.0);
        if(movingLeft)
        destination.translate(-1.0, 0);   
        if(movingRight)
        destination.translate(1.0, 0);
        if(movingUp)
        destination.translate(0, -1.0);        
        
        player.setDestination(destination);
        
    }
    
    public void getStates(GraphicsContext g)
    {
        String activeStates = new String();
        if(movingDown)
        activeStates+= "MOVING_DOWN ";
        if(movingLeft)
        activeStates+= "MOVING_LEFT ";
        if(movingRight)
        activeStates+= "MOVING_RIGHT ";
        if(movingUp)
        activeStates+= "MOVING_UP ";
        
        g.setFill(Color.WHITE);
        g.fillText(activeStates, 16, 40);
    }
}
    