import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy extends Actor
{
    public Point location;
    public Point destination;
    
    private double rotation;
    private double velocity;
    
    public Enemy(World world)
    {
        super(world);
        location = new Point(0, 0);
        destination = new Point(location);
        rotation = 0;
        velocity = 96;
        
    }

    @Override
    public void process(double time)
    {
        super.process(time);
        if(location.distance(destination) > 0)
        {
            Circle nextCircle = new Circle(location, circle.getRadius());
            nextCircle.getLocation().translatePolar(destination, velocity * time);
            
            if(world.testMove(this, nextCircle))
            {
                location.translatePolar(destination, velocity * time);
                circle = new Circle(location, circle.getRadius());
            }
        }
    }
    
    public void setDestination(Point p)
    {
        destination = new Point(p);
    }
    
    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }
    
    public void draw(GraphicsContext g)
    {
        g.setFill(Color.RED);
        g.fillOval(location.getX() - circle.getRadius(), location.getY() - circle.getRadius(),
            circle.getRadius()*2, circle.getRadius()*2);
    }
}