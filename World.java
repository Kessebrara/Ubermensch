import java.util.ArrayList;
import java.util.Iterator;

public class World
{
        ArrayList<Actor> actors = new ArrayList<Actor>();
        ArrayList<Controller> controllers = new ArrayList<Controller>();
        ArrayList<Particle> particles = new ArrayList<Particle>();
        
        public World()
        {
        }
        
        public void process(double time)
        {            
            for(Controller c: controllers)
            c.process(time);
            
            Iterator<Actor> actorIt = actors.iterator();
            while(actorIt.hasNext())
            {
                Actor actor = actorIt.next();
                actor.process(time);
                
                if(actor.isDestroyingSelf())
                actorIt.remove();
            }
            
            for(Particle p: particles)
            {
                p.process(time);
            }
            
            
            removeActors();
            removeParticles();
        }
        
        public boolean containsActor(Actor actor)
        {
            return actors.contains(actor);
        }
        
        public boolean containsController(Controller controller)
        {
            return controllers.contains(controller);
        }
        
        public void addController(Controller controller)
        {
            if(!controllers.contains(controller))
            controllers.add(controller);
        }
        
        public void addActor(Actor actor)
        {
            if(!actors.contains(actor))
            actors.add(actor);
        }
        
        public boolean testMove(Actor actor, Circle circle)
        {
            for(Actor a: actors)
            {
                if(actor == a)
                break;
                
                if(a.circle.intersects(circle))
                return false;
            }
            
            return true;
        }
        
        public void addParticle(Particle particle)
        {
            if(!particles.contains(particle))
            particles.add(particle);
        }
        
        public ArrayList<Actor> getActors()
        {
            return actors;
        }
        
        public ArrayList<Particle> getParticles()
        {
            return particles;
        }
        
        public void removeParticles()
        {
            Iterator<Particle> iterator = particles.iterator();
            while(iterator.hasNext())
            {
                Particle particle = iterator.next();
                if(particle.destroying)
                {
                    iterator.remove();
                    break;
                }
            }
        }
        
        public void removeActors()
        {
            Iterator<Actor> iterator = actors.iterator();
            while(iterator.hasNext())
            {
                Actor actor = iterator.next();
                if(actor.isDestroyingSelf())
                {
                    iterator.remove();
                    break;
                }
            }
        }
        
        public int getNumActors()
        {
            return actors.size();
        }
        
        public Player getPlayer()
        {
            for(Actor a: actors)
            if(a instanceof Player)
            return (Player)a;
            
            return null;
        }
        
        public PlayerController getPlayerController()
        {
            for(Controller c: controllers)
            if(c instanceof PlayerController)
            return (PlayerController)c;
            
            return null;
        }
}