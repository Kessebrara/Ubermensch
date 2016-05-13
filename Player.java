import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class Player extends Actor
{
    public Point[] lastLocation = new Point[32];
    public Point destination;
    
    private boolean moving;
    boolean tickTock;
    private double rotation;
    private double velocity;
    
    public Player(World world)
    {
        super(world);
        location = new Point(400, 300);
        destination = new Point(location);
        for(int i = 0; i < lastLocation.length; i++)
        lastLocation[i] = new Point(location);
        rotation = Math.toRadians(0);
        velocity = 192;
    }
    
    @Override
    public void process(double time)
    {        
        super.process(time);
        for(int i = lastLocation.length - 1; i > 0; i--)
        lastLocation[i] = new Point(lastLocation[i-1]);
        
        lastLocation[0] = new Point(location);
        
        
        if(location.distance(destination) > 0)
        location.translatePolar(destination, velocity * time);
        
        destination = new Point(location);
    }
    
    public void draw(GraphicsContext g)
    {                
        g.setFill(Color.BLUE);    
        for(int i = 0; i < lastLocation.length; i++)
        {
            g.fillOval(lastLocation[i].getX() - 8, lastLocation[i].getY() - 8, 16, 16);
            g.setGlobalAlpha(1.0 / i);    
        }
        
        g.setGlobalAlpha(1);
        g.fillOval(location.getX() - 8, location.getY() - 8, 16, 16);
    }
    
    public void setDestination(Point p)
    {
        destination = new Point(p);
    }
    
    public void drawDebug(GraphicsContext g)
    {
        draw(g);
        
        Font font = Font.font( "Arial", FontWeight.BOLD, 12 );
        g.setFont(font);
        
        for(int i = 0; i < 360; i+= 15)
        {
            g.fillText(""+i, location.getX() + 128 * Math.cos(Math.toRadians(i)),
            location.getY() + 128 * Math.sin(Math.toRadians(i)));
        }
    }
}