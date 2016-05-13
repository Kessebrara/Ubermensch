public class EnemyController extends Controller
{
    public Player player;
    public Enemy enemy;
    
    public EnemyController(World world, Player player, Enemy enemy)
    {
        super(world);
        this.player = player;
        this.enemy = enemy;
        
    }
    
    @Override
    public void process(double time)
    {
        Point destination = new Point(player.location);
        
        enemy.setDestination(destination);
    }
    
    
}