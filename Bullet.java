import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Particle
{
    public Bullet(World world)
    {
        super(world);
        rotation = 0;
        velocity = 256;
    }
    
    @Override
    public void process(double time)
    {
        location.translatePolar(rotation, time * velocity);
        
    }
    
    @Override
    public void draw(GraphicsContext g)
    {
        g.setFill(Color.ORANGE);
        g.fillRect(location.getX() - 2, location.getY() - 2, 4, 4);
    }
}