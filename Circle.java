public class Circle
{
    Point location;
    double radius;
    
    public Circle()
    {
        this(new Point(0, 0), 1);
    }
    
    public Circle(Point location, double radius)
    {
        this.location = new Point(location);
        this.radius = radius;
    }
    
    public boolean contains(Point point)
    {
        return location.distance(point) < radius;
    }
    
    public boolean intersects(Circle circle)
    {
        return location.distance(circle.getLocation()) < radius + circle.getRadius();
    }
    
    public Point getLocation()
    {
        return location;
    }
    
    public double getRadius()
    {
        return radius;
    }
}