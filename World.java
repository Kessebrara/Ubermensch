import java.util.ArrayList;

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
            
            for(Actor a: actors)
            a.process(time);
            
            for(Particle p: particles)
            {
                p.process(time);
                
                for(Actor a: actors)
                {
                    if(a.circle.contains(p.location))
                    {
                        a.destroy();
                        p.destroy();
                    }
                }
            }
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
        
        public void removeParticle(Particle particle)
        {
            particles.remove(particle);
        }
        
        public void removeActor(Actor actor)
        {
            actors.remove(actor);
        }
}