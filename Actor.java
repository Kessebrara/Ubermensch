import javafx.scene.canvas.GraphicsContext;

public class Actor
{
    Point location;
    World world;
    Circle circle;
    boolean destroying = false;
    double age;
    
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
        age+= time;
        if(age > 5)
        destroySelf();
    }
    
    public void draw(GraphicsContext g)
    {
    }
    
    public void destroySelf()
    {
        destroying = true;
    }
    
    public boolean isDestroyingSelf()
    {
        return destroying;
    }
}
