import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Camera
{
    Point location;
    World world;
    
    public Camera(World world)
    {
        location = new Point(0, 0);
        this.world = world;
    }
    
    public void draw(GraphicsContext g)
    {
        ArrayList<Actor> actors = world.getActors();
        ArrayList<Particle> particles = world.getParticles();
        
        for(Actor a: actors)
        a.draw(g);
        
        for(Particle p: particles)
        p.draw(g);
        
        
    }
}