import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.stage.WindowEvent;
import java.util.Random;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.input.MouseEvent;


public class ClientRunner extends Application
{
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    Random random = new Random();
    Canvas canvas = new Canvas(WIDTH, HEIGHT);
    
    World world = new World();
    Camera camera = new Camera(world);
    
    static long lastNanoTime = System.nanoTime();
    int actors;
    
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        
        stage.setTitle("Incredible Next Gen Title");
        
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<String>();
        
        // INITIATION
        
        world.addActor(new Player(world));
        world.addController(new PlayerController(world, world.getPlayer()));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                world.getPlayerController().keyStroke(e, true);
                
                String key = e.getCode().toString();
                if(key == "SPACE")
                {
                    ZombieEnemy enemy = new ZombieEnemy(world);
                    world.addActor(enemy);
                    world.addController(new ZombieController(world, world.getPlayer(), enemy));
                    actors++;
                }
                
                if(key == "Q")
                {
                    ArrayList<Actor> actors = world.getActors();
                    for(Actor a: actors)
                    if(a instanceof Player)
                    {
                        a.destroySelf();
                    }
                }
            }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                world.getPlayerController().keyStroke(e, false);                
            }
        });
        scene.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {               
                Bullet bullet = new Bullet(world);
                bullet.setLocation(world.getPlayer().location);
                bullet.setRotation(world.getPlayer().location.angle(new Point(e.getX(), e.getY())));
            }
            
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
            }
        });
        
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        Font font = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        g.setFont(font);
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);
        
        LongValue lastNanoTime = new LongValue(System.nanoTime());
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                
                double fps = 1.0 / elapsedTime;
                

                // handle input
                
                // game logic
                
                world.process(elapsedTime);
                
                // collision detection
                
                // render game
                g.setFill(Color.BLACK);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                
                camera.draw(g);
                
                // render UI
                
                g.setFill(Color.WHITE);
                g.fillText("Frames Per Second = "+fps, 16, 16);
                g.fillText("Actors = "+actors, 16, 40);
                ArrayList<Actor> actors = world.getActors();
                
                g.fillText("Number actors = "+world.getNumActors(), 16, 88);
                
            }
        }.start();

        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent e)
            {
                System.exit(1);
            }
        });
    }
}