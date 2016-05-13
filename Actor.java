import javafx.scene.canvas.GraphicsContext;

public class Actor
{
    Point location;
    World world;
    Circle circle;
    
    public Actor(World world)
    {
        this.world = world;
        location = new Point(0, 0);
        circle = new Circle(location, 16);
        
        world.addActor(this);
    }
    
    public boolean contains(Point point)
    {
        return location.distance(point) <= circle.radius;
    }
    
    public void process(double time)
    {
    }
    
    public void draw(GraphicsContext g)
    {
    }
    
    public void destroy()
    {
        world.removeActor(this);
    }
    
}
