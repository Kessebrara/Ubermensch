public class Controller
{
    public Controller(World world)
    {
        world.addController(this);
    }
    
    public void process(double time)
    {
    }
}