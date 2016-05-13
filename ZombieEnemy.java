public class ZombieEnemy extends Enemy
{
    public ZombieEnemy(World world)
    {
        super(world);
        
        location = new Point(256, 256);
        setVelocity(64);
    }
}