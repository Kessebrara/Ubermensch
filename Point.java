public class Point
{
    private double x;
    private double y;
    
    public Point()
    {
        this(0, 0);
    }
    
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point p)
    {
        this(p.x, p.y);
    }
    
    public double distance(Point p)
    {
        return Math.sqrt( Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) );
    }
    
    public double angle(double x, double y)
    {
        return Math.atan2(y - this.y, x - this.x);
    }
    
    public double angle(Point p)
    {
        return angle(p.getX(), p.getY());
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setLocation(Point p)
    {
        setLocation(p.x, p.y);
    }
    
    public void translate(double x, double y)
    {
        this.x += x;
        this.y += y;
    }
    
    public void translatePolar(Point p, double distance)
    {
        double angle = angle(p);
        translatePolar(angle, distance);
    }
    
    public void translatePolar(double direction, double distance)
    {
        this.x += distance * Math.cos(direction);
        this.y += distance * Math.sin(direction);
    }
}