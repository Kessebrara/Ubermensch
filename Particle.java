import javafx.scene.canvas.GraphicsContext;

public class Particle
{
    Point location;
    double velocity;
    double rotation;
    World world;
    boolean destroying;
    
    public Particle(World world)
    {
        this.world = world;
        world.addParticle(this);
        location = new Point(0, 0);
        velocity = 32;
        rotation = 0;
    }
    
    public void setLocation(Point location)
    {
        this.location = new Point(location);
    }
    
    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
    
    public void process(double time)
    {
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